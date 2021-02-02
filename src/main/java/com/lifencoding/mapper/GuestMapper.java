package com.lifencoding.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.lifencoding.entity.GuestVO;

@Mapper
public interface GuestMapper {
	public GuestVO select(GuestVO guestVO);

	public int getCount(GuestVO guestVO);

	public void insert(GuestVO guestVO);
}
