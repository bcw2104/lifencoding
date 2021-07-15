package com.lifencoding.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lifencoding.entity.CategoryVO;
import com.lifencoding.entity.PostVO;
import com.lifencoding.serviceImpl.PostService;
import com.lifencoding.serviceImpl.SubCategoryService;
import com.lifencoding.util.GlobalValues;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping("/create")
	public String create(Model model) throws Exception {
		postService.deleteImgFile(0);
		model.addAttribute("content", GlobalValues.postCreate);
		return "forward:/";
	}

	@GetMapping("{postId}/edit")
	public String edit(@PathVariable("postId") String _postId, Model model) throws Exception {
		postService.deleteImgFile(0);

		PostVO postVO = new PostVO();
		postVO.setPostId(Integer.parseInt(_postId));
		postVO = postService.get(postVO);

		model.addAttribute("currentPost", postVO);
		model.addAttribute("content", GlobalValues.postModify);

		return "forward:/";
	}

	@PostMapping("/add.do")
	public String add(@RequestParam("categoryId") String _categoryId,
						@RequestParam("title") String title,
						@RequestParam("content") String content,
						@RequestParam MultipartFile thumbnail,Model model,HttpServletResponse response) throws Exception {

		PostVO postVO = new PostVO();
		CategoryVO categoryVO = new CategoryVO();

		int postId = postService.getNextPostId();

		postService.changeUploadDir(postId);
		if(!thumbnail.isEmpty()) {
			String extension = FilenameUtils.getExtension(thumbnail.getOriginalFilename());

			if(extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")
					|| extension.equals("bmp") || extension.equals("svg")){
				String fileName = "thumbnail."+ extension;
				postService.uploadImg(postId, "thumbnail", fileName, thumbnail);
				postVO.setThumbnail(fileName);
			}
		}

		int categoryId = Integer.parseInt(_categoryId);
		postVO.setPostId(postId);
		postVO.setPostTitle(title);
		postVO.setPostContent(content.replace("post"+File.separator+"temp", "post"+File.separator+postId));
		postVO.setCategoryId(categoryId);

		postService.add(postVO);

		categoryVO.setCategoryId(categoryId);
		categoryVO = subCategoryService.get(categoryVO);

		return "redirect:/"+categoryVO.getCategoryEn()+"/"+postVO.getPostId();

	}

	@PostMapping("/{postId}/modify.do")
	public String modify(@RequestParam("categoryId") String _categoryId, @PathVariable("postId") String _postId,
			@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam(name = "thumbnailCk", required = false) String thumbnailCk,
			@RequestParam MultipartFile thumbnail, Model model, HttpServletResponse response) throws Exception {

		PostVO postVO = new PostVO();
		CategoryVO categoryVO = new CategoryVO();

		int postId = Integer.parseInt(_postId);
		postVO.setPostId(postId);
		postVO = postService.get(postVO);

		if (thumbnailCk == null) {
			if (!thumbnail.isEmpty()) {
				postService.deleteThumbnail(postId);

				String extension = FilenameUtils.getExtension(thumbnail.getOriginalFilename());

				if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png")
						|| extension.equals("bmp") || extension.equals("svg")) {
					String fileName = "thumbnail." + extension;
					postService.uploadImg(postId, "thumbnail", fileName, thumbnail);
					postVO.setThumbnail(fileName);
				}
			}
		} else {
			postService.deleteThumbnail(postId);
			postVO.setThumbnail("");
		}

		int categoryId = Integer.parseInt(_categoryId);
		postVO.setPostId(postId);
		postVO.setPostTitle(title);
		postVO.setPostContent(content.replace("post" + File.separator + "temp", "post" + File.separator + postId));
		postVO.setCategoryId(categoryId);

		postService.modify(postVO);

		categoryVO.setCategoryId(categoryId);
		categoryVO = subCategoryService.get(categoryVO);

		return "redirect:/" + categoryVO.getCategoryEn() + "/" + postVO.getPostId();
	}

	@GetMapping("/{postId}/delete.do")
	public String delete(@PathVariable("postId") String _postId) throws Exception {

		PostVO postVO = new PostVO();
		CategoryVO categoryVO = new CategoryVO();

		int postId = Integer.parseInt(_postId);
		postService.deleteImgFile(postId);

		postVO.setPostId(postId);
		postVO = postService.get(postVO);

		postService.delete(postVO);

		categoryVO.setCategoryId(postVO.getCategoryId());
		categoryVO = subCategoryService.get(categoryVO);

		return "redirect:/" + categoryVO.getCategoryEn();
	}

	@SuppressWarnings("unchecked")
	@PostMapping("uploadImgTemp.do")
	public void uploadImg(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload)
			throws Exception {

		int postId = 0;
		if (request.getHeader("Referer").endsWith("edit")) {
			postId = Integer.parseInt(request.getHeader("Referer").split("/")[4]);
		}
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String extension = FilenameUtils.getExtension(upload.getOriginalFilename());

		if (extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png") || extension.equals("bmp")
				|| extension.equals("svg")) {
			String uuid = UUID.randomUUID().toString();
			String fileName = uuid + "." + extension;
			String url = postService.uploadImg(postId, "inner", fileName, upload);

			JSONObject object = new JSONObject();

			object.put("uploaded", 1);
			object.put("fileName", fileName);
			object.put("url", url);

			response.getWriter().write(object.toJSONString());
		} else {
			response.getWriter().write("<script>alert('사진의 확장자는 svg,bmp,png,jpg,jpeg만 가능합니다.')</script>");
		}

	}

}
