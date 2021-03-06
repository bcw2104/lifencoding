package com.lifencoding.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.lifencoding.entity.AdminVO;
import com.lifencoding.entity.CategoryVO;
import com.lifencoding.entity.PostVO;
import com.lifencoding.serviceImpl.AdminService;
import com.lifencoding.serviceImpl.CategoryService;
import com.lifencoding.serviceImpl.GuestService;
import com.lifencoding.serviceImpl.PostService;
import com.lifencoding.serviceImpl.SubCategoryService;
import com.lifencoding.util.GlobalValues;

@Controller
public class HomeController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private PostService postService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubCategoryService subCategoryService;
	@Autowired
	private GuestService guestService;

	@SuppressWarnings("unchecked")
	@GetMapping("/")
	public String home(HttpServletRequest request, Model model,
						@RequestParam(name = "search", required = false) String search,
						@RequestParam(name = "p", required = false) String p) {

		int [] range;

		ArrayList<CategoryVO> categoryList = categoryService.getList();
		ArrayList<CategoryVO> subCategoryList = subCategoryService.getList();

		AdminVO adminInfo = adminService.getAdminInfo();
		adminInfo.setAdminId(null);
		adminInfo.setAdminPw(null);

		if(request.getAttribute("content") == null) {
			PostVO postVO = new PostVO();

			if(search==null) {

				int count = postService.getPostCount(postVO);
				range = postService.getPageRange(p,count, 4);

				postVO.setStart(range[0]);
				postVO.setEnd(range[1]);

				ArrayList<PostVO> postList = postService.makeAllPostThumbnail(postService.getList(postVO),300);
				ArrayList<PostVO> hotPostList = postService.makeAllPostThumbnail(postService.getHotList(),300);

				int totalVisit = guestService.getTotalVisit();
				int todayVisit = guestService.getTodayVisit();

				model.addAttribute("totalVisit",totalVisit);
				model.addAttribute("todayVisit", todayVisit);
				model.addAttribute("postCount", count);
				model.addAttribute("postList",postList);
				model.addAttribute("hotPostList",hotPostList);
			}
			else {
				postVO.setPostTitle(search);

				int count = postService.getPostCount(postVO);

				range = postService.getPageRange(p,count,9);

				postVO.setStart(range[0]);
				postVO.setEnd(range[1]);
				ArrayList<PostVO> postList = postService.getList(postVO);

				if(!postList.isEmpty()) {
					model.addAttribute("postList", postList);
					model.addAttribute("nearPost", postService.getNear(postList.get(0)));
					request.setAttribute("currentPost", postList.get(0));
					request.setAttribute("postTumbnail", postService.makePostThumbnail(postList.get(0).getPostContent(),160));
				}
				model.addAttribute("postCount", count);
				model.addAttribute("content", GlobalValues.postView);
			}
		}

		if(request.getAttribute("currentPost") != null && request.getSession().getAttribute("guest") != null) {
			PostVO postVO = (PostVO) request.getAttribute("currentPost");
			int postId = postVO.getPostId();
			boolean flag = true;
			ArrayList<Integer> visitList = (ArrayList<Integer>) request.getSession().getAttribute("visit");
			if(visitList.size() > 0) {
				for(int visit : visitList) {
					if(visit == postId) {
						flag = false;
						break;
					}
				}
			}
			if(flag) {
				postService.addHits(postId);
				postVO.setHits(postVO.getHits()+1);
				visitList.add(postId);
			}
		}
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("subCategoryList", subCategoryList);
		model.addAttribute("adminInfo",adminInfo);

		return "frame";
	}

	@GetMapping("/{categoryEn}")
	public String category(@PathVariable String categoryEn, @RequestParam(name = "p", required = false) String p,
			Model model, HttpServletResponse response) throws IOException {

		int [] range;

		PostVO postVO = new PostVO();
		CategoryVO categoryVO = new CategoryVO();
		CategoryVO subCategoryVO = new CategoryVO();
		subCategoryVO.setCategoryEn(categoryEn);

		subCategoryVO = subCategoryService.get(subCategoryVO);
		if (subCategoryVO != null) {
			categoryVO.setCategoryId(subCategoryVO.getParent());
			categoryVO = categoryService.get(categoryVO);

			postVO.setCategoryId(subCategoryVO.getCategoryId());

			int count = postService.getPostCount(postVO);

			range = postService.getPageRange(p,count, 9);

			postVO.setStart(range[0]);
			postVO.setEnd(range[1]);

			ArrayList<PostVO> postList = postService.getList(postVO);

			if(!postList.isEmpty()) {
				model.addAttribute("postList", postList);
				model.addAttribute("nearPost", postService.getNear(postList.get(0)));
				model.addAttribute("currentPost", postList.get(0));
				model.addAttribute("postTumbnail", postService.makePostThumbnail(postList.get(0).getPostContent(),160));
			}
			model.addAttribute("postCount", count);
			model.addAttribute("content", GlobalValues.postView);
			model.addAttribute("currentCategory", categoryVO);
			model.addAttribute("currentSubCategory", subCategoryVO);
		} else {
			response.sendError(404);
		}
		return "forward:/";
	}

	@GetMapping("/{categoryEn}/{postId}")
	public String post(@PathVariable String categoryEn, @PathVariable(required=false) String postId,
				@RequestParam(name = "p", required = false) String p, Model model,
				HttpServletResponse response) throws IOException, NumberFormatException {

		int [] range;

		CategoryVO categoryVO = new CategoryVO();
		CategoryVO subCategoryVO = new CategoryVO();
		PostVO postVO = new PostVO();
		subCategoryVO.setCategoryEn(categoryEn);

		postVO.setPostId(Integer.parseInt(postId));
		postVO = postService.get(postVO);
		subCategoryVO = subCategoryService.get(subCategoryVO);
		if (subCategoryVO != null && postVO != null && postVO.getCategoryId() == subCategoryVO.getCategoryId()) {
			categoryVO.setCategoryId(subCategoryVO.getParent());
			categoryVO = categoryService.get(categoryVO);

			PostVO temp = new PostVO();
			temp.setCategoryId(subCategoryVO.getCategoryId());
			int count = postService.getPostCount(temp);

			range = postService.getPageRange(p,count, 9);

			temp.setStart(range[0]);
			temp.setEnd(range[1]);

			ArrayList<PostVO> postList = postService.getList(temp);

			if(!postList.isEmpty()) {
				model.addAttribute("postList", postList);
			}

			model.addAttribute("nearPost", postService.getNear(postVO));
			model.addAttribute("postCount", count);
			model.addAttribute("content", GlobalValues.postView);
			model.addAttribute("currentPost", postVO);
			model.addAttribute("postTumbnail", postService.makePostThumbnail(postVO.getPostContent(),160));
			model.addAttribute("currentCategory", categoryVO);
			model.addAttribute("currentSubCategory", subCategoryVO);
		} else {
			response.sendError(404);
		}
		return "forward:/";
	}
}
