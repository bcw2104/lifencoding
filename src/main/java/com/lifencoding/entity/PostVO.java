package com.lifencoding.entity;

import java.sql.Timestamp;

public class PostVO {
	private int postId;
	private String postTitle;
	private String postContent;
	private Timestamp postDate;
	private int postComment;
	private int categoryId;
	private String categoryEn;
	private String categoryName;
	private int start;
	private int end;
	private String thumbnail;
	private int hits;

	public PostVO() {
		postId = 0;
		postTitle = null;
		postContent = null;
		postDate = new Timestamp(System.currentTimeMillis());
		postComment = -1;
		categoryId = 0;
		categoryEn = null;
		categoryName = null;
		start = 0;
		end = 0;
		thumbnail="";
		hits = 0;
	}

	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public Timestamp getPostDate() {
		return postDate;
	}
	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}
	public int getPostComment() {
		return postComment;
	}
	public void setPostComment(int postComment) {
		this.postComment = postComment;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryEn() {
		return categoryEn;
	}

	public void setCategoryEn(String categoryEn) {
		this.categoryEn = categoryEn;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}


}
