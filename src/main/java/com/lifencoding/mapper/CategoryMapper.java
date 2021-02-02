package com.lifencoding.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.lifencoding.entity.CategoryVO;

@Mapper
public interface CategoryMapper {
	public ArrayList<CategoryVO> select(CategoryVO categoryVO);
	public void insert(CategoryVO categoryVO);
	public void modify(CategoryVO categoryVO);
	public void delete(CategoryVO categoryVO);
}
