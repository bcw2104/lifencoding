package com.lifencoding.serviceImpl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifencoding.entity.GuestVO;
import com.lifencoding.mapper.GuestMapper;

@Service
public class GuestService {

	@Autowired
	private GuestMapper guestMapper;

	public int getTotalVisit() {
		GuestVO guestVO = new GuestVO();

		guestVO.setVisitDate(null);

		return guestMapper.getCount(guestVO);
	}

	@SuppressWarnings("deprecation")
	public int getTodayVisit() {
		GuestVO guestVO = new GuestVO();
		Timestamp today = new Timestamp(System.currentTimeMillis());
		today.setHours(0);
		today.setMinutes(0);
		today.setSeconds(0);
		today.setNanos(0);

		guestVO.setVisitDate(today);

		return guestMapper.getCount(guestVO);
	}

	public void add(GuestVO guestVO) {
		guestMapper.insert(guestVO);
	}
}
