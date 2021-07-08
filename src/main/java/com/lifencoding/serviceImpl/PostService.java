package com.lifencoding.serviceImpl;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lifencoding.entity.PostVO;
import com.lifencoding.mapper.PostMapper;
import com.lifencoding.service.ContentServiceImpl;
import com.lifencoding.util.FTPManager;
import com.lifencoding.util.FileTools;

@Service
public class PostService implements ContentServiceImpl<PostVO>{

	@Autowired
	private FTPManager manager;
	@Autowired
	private PostMapper postMapper;

	public int[] getPageRange(String p,int count,int size) {
		int [] range = new int[2];

		if (p != null) {
			try {
				range[0] = (Integer.parseInt(p)-1)*(size+1)+1;
			}
			catch(NumberFormatException e) {
				range[0] = 1;
			}

			if(range[0] < 1 || range[0] > count)
				range[0] = 1;
			range[1] = range[0]+size;
		}
		else {
			range[0]=1;
			range[1] = range[0]+size;
		}

		return range;
	}

	public String makePostThumbnail(String postContent){
		String str = postContent.replaceAll("\\<.*?\\>", "");
		if(str.length() > 400) {
			str.substring(0, 400);
		}

		return str;
	}

	public ArrayList<PostVO> makeAllPostThumbnail(ArrayList<PostVO> list){
		for(PostVO ele : list) {
			ele.setPostContent(makePostThumbnail(ele.getPostContent()));
		}

		return list;
	}

	public int getPostCount(PostVO postVO) {
		return postMapper.getCount(postVO);
	}

	public int getNextPostId() {
		return postMapper.getSequenceNum();
	}

	@Override
	public ArrayList<PostVO> getList(PostVO postVO) {
		return postMapper.select(postVO);
	}


	@Override
	public PostVO get(PostVO postVO) {
		ArrayList<PostVO> list = postMapper.select(postVO);

		if(list.isEmpty()) {
			return null;
		}
		else {
			return list.get(0);
		}
	}

	public ArrayList<PostVO> getNear(PostVO postVO) {
		return postMapper.selectNear(postVO);
	}

	public ArrayList<PostVO> getHotList() {
		return postMapper.selectHot();
	}

	public void addHits(int postId) {
		postMapper.addHits(postId);
	}

	@Override
	public void add(PostVO postVO) {
		postMapper.insert(postVO);
	}

	@Override
	public void modify(PostVO postVO) {
		postMapper.modify(postVO);

	}

	@Override
	public void delete(PostVO postVO) {
		postMapper.delete(postVO);
	}

	public String uploadImg(int postId,String type,String fileName,MultipartFile mfile) throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String dir = fileTools.getPostDirPath();

		if(postId == 0) {
			dir+= File.separator + "temp" + File.separator + type;
		}
		else {
			dir+= File.separator + postId + File.separator + type;
		}

		String reply = fileTools.createPostTempFile(dir, fileName, mfile);

		manager.disconnect(client);

		return reply;
	}

	public boolean changeUploadDir(int postId) throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String path= fileTools.getPostDirPath();

		boolean reply = fileTools.rename(path+File.separator+"temp",path+File.separator+postId);

		manager.disconnect(client);

		return reply;
	}

	public void deleteThumbnail(int postId) throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String dir= fileTools.getPostDirPath()+File.separator+postId;
		String fileName = "thumbnail";

		fileTools.remove(dir,fileName);

		manager.disconnect(client);
	}
	public void deleteImgFile(int postId) throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String dir = fileTools.getPostDirPath();

		fileTools.remove(dir,String.valueOf(postId));

		manager.disconnect(client);
	}

}
