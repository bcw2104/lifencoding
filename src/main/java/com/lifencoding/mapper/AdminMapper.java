package com.lifencoding.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lifencoding.entity.AdminVO;

@Mapper
public interface AdminMapper {
	public AdminVO select();
	public void modify(AdminVO adminVO);
}
