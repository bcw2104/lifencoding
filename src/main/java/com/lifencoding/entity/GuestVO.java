package com.lifencoding.entity;

import java.sql.Timestamp;

public class GuestVO {
	private int guestId;
	private String guestIp;
	private Timestamp visitDate;

	public GuestVO() {
		guestId = 0;
		guestIp = null;
		visitDate = new Timestamp(System.currentTimeMillis());
	}

	public int getGuestId() {
		return guestId;
	}
	public void setGuestId(int guestId) {
		this.guestId = guestId;
	}
	public String getGuestIp() {
		return guestIp;
	}
	public void setGuestIp(String guestIp) {
		this.guestIp = guestIp;
	}
	public Timestamp getVisitDate() {
		return visitDate;
	}
	public void setVisitDate(Timestamp visitDate) {
		this.visitDate = visitDate;
	}


}
