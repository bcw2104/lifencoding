package com.lifencoding.service;

import java.util.ArrayList;

public interface ContentServiceImpl <T>{
	public ArrayList<T> getList(T entity);

	public T get(T entity);

	public void add(T entity);

	public void modify(T entity);

	public void delete(T entity);
}
