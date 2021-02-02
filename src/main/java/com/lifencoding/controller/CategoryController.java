package com.lifencoding.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifencoding.entity.CategoryVO;
import com.lifencoding.serviceImpl.CategoryService;
import com.lifencoding.serviceImpl.SubCategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubCategoryService subCategoryService;

	@PostMapping("/add.do")
	public String add(@RequestParam("type")String type,
							@RequestParam(name = "parent",required = false) String parent,
							@RequestParam(name = "categoryName",required = false) String categoryName,
							@RequestParam(name = "categoryEn",required=false) String categoryEn) throws IOException {
		CategoryVO categoryVO = new CategoryVO();

		if(categoryName != null)
			categoryVO.setCategoryName(categoryName);
		if(categoryEn != null)
			categoryVO.setCategoryEn(categoryEn);
		if(parent != null)
			categoryVO.setParent(Integer.parseInt(parent));


		if(type.equals("main")) {
			categoryService.add(categoryVO);
		}
		else {
			subCategoryService.add(categoryVO);
		}

		return "redirect:/";
	}

	@PostMapping("/modify.do")
	public String modify(@RequestParam("type")String type,
							@RequestParam(name = "categoryId") String categoryId,
							@RequestParam(name = "categoryName",required = false) String categoryName,
							@RequestParam(name = "categoryEn",required=false) String categoryEn) throws IOException {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setCategoryId(Integer.parseInt(categoryId));

		if(categoryName != null)
			categoryVO.setCategoryName(categoryName);
		if(categoryEn != null)
			categoryVO.setCategoryEn(categoryEn);

		if(type.equals("main")) {
			categoryService.modify(categoryVO);
		}
		else {
			subCategoryService.modify(categoryVO);
		}

		return "redirect:/";
	}

	@PostMapping("/delete.do")
	public String delete(@RequestParam("type")String type,
							@RequestParam(name = "categoryId") String categoryId) throws IOException {
		CategoryVO categoryVO = new CategoryVO();

		categoryVO.setCategoryId(Integer.parseInt(categoryId));

		if(type.equals("main")) {
			categoryService.delete(categoryVO);
		}
		else {
			subCategoryService.delete(categoryVO);
		}

		return "redirect:/";
	}

	@GetMapping("/check.do")
	@ResponseBody
	public String check(@RequestParam("type")String type,@RequestParam(name = "categoryName",required = false) String categoryName,
							@RequestParam(name = "categoryEn",required=false) String categoryEn) throws IOException {
		CategoryVO categoryVO = new CategoryVO();

		if(categoryName != null)
			categoryVO.setCategoryName(categoryName);
		if(categoryEn != null)
			categoryVO.setCategoryEn(categoryEn);

		if(type.equals("main")) {
			categoryVO = categoryService.get(categoryVO);
		}
		else {
			categoryVO = subCategoryService.get(categoryVO);
		}

		return categoryVO == null ? "true" : "false";

	}

}
