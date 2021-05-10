package com.lifencoding.entity;

public class CategoryVO {
	private int categoryId;
	private String categoryName;
	private String categoryEn;
	private int parent;
	private int postCount;

	public CategoryVO() {
		categoryId = 0;
		categoryName = null;
		categoryEn = null;
		parent = 0;
		postCount = -1;
	}

	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryEn() {
		return categoryEn;
	}
	public void setCategoryEn(String categoryEn) {
		this.categoryEn = categoryEn;
	}

	public int getParent() {
		return parent;
	}

	public void setParent(int parent) {
		this.parent = parent;
	}

	public int getPostCount() {
		return postCount;
	}
	public void setPostCount(int postCount) {
		this.postCount = postCount;
	}

}
