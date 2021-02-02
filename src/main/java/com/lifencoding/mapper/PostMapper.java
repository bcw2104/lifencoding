package com.lifencoding.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.lifencoding.entity.PostVO;

@Mapper
public interface PostMapper {

	public int getSequenceNum();

	public int getCount(PostVO postVO);

	public ArrayList<PostVO> select(PostVO postVO);

	public ArrayList<PostVO> selectNear(PostVO postVO);

	public ArrayList<PostVO> selectHot();

	public void addHits(int postId);

	public void insert(PostVO postVO);

	public void modify(PostVO postVO);

	public void delete(PostVO postVO);
}
