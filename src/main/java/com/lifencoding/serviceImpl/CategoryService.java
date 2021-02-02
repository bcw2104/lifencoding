package com.lifencoding.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifencoding.entity.CategoryVO;
import com.lifencoding.mapper.CategoryMapper;
import com.lifencoding.service.ContentServiceImpl;

@Service
public class CategoryService implements ContentServiceImpl<CategoryVO> {

	@Autowired
	private CategoryMapper categoryMapper;

	public ArrayList<CategoryVO> getList(){
		return getList(new CategoryVO());
	}

	public ArrayList<CategoryVO> getList(CategoryVO categoryVO){
		return categoryMapper.select(categoryVO);
	}

	public CategoryVO get(CategoryVO categoryVO){
		ArrayList<CategoryVO> list = categoryMapper.select(categoryVO);

		if(list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public void add(CategoryVO categoryVO) {
		categoryMapper.insert(categoryVO);
	}

	public void modify(CategoryVO categoryVO) {
		categoryMapper.modify(categoryVO);
	}

	public void delete(CategoryVO categoryVO) {
		categoryMapper.delete(categoryVO);
	}


}
