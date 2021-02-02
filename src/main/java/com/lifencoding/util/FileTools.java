package com.lifencoding.util;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

import com.lifencoding.fillter.ImageFileFilter;

public class FileTools {
	private String realRootPath;
	private String contextPath;
	private String profilePath;
	private String defaultProfilePath;

	public FileTools() {
		realRootPath = File.separator+"home"+File.separator+"bcw2104"+File.separator+"git"+File.separator+".metadata"+File.separator+
				".plugins"+File.separator+"org.eclipse.wst.server.core"+File.separator+"tmp0"+File.separator+"wtpwebapps"+File.separator+"lifencoding";
		contextPath = File.separator+"resources"+File.separator+"upload"+File.separator+"files";
		profilePath = File.separator+"profile";
		defaultProfilePath =  File.separator+"profile"+File.separator+"default"+File.separator+"profile.svg";
	}

	public boolean renameFile(File file,String newPath) {
		if(file.exists())
			return file.renameTo(new File(newPath));
		else
			return true;
	}

	public String makePostUploadPath(int postId) {
		if(postId == 0) {
			return contextPath+File.separator+"post"+File.separator+"temp";
		}
		else {
			return contextPath+File.separator+"post"+File.separator+postId;
		}
	}

	public File[] findImgFiles(File file) {
		if (file.exists() && file.isDirectory()) {
			return file.listFiles(new ImageFileFilter());
		}

		return null;
	}

	public File[] findFiles(File file) {
		if (file.exists() && file.isDirectory()) {
			return file.listFiles();
		}

		return null;
	}

	public String findProfileImgPath() {
		String path = contextPath+profilePath;
		File file = new File(realRootPath+path);

		File[] files = findImgFiles(file);
		if(files.length>0) {
			file = files[0];
			path += File.separator + file.getName();
		}
		else {
			path = null;
		}

		return path;
	}

	public File findProfileImg() {
		File file = new File(realRootPath+findProfileImgPath());

		return file;
	}

	public String findDefaultProfileImg() {
		String path = contextPath+defaultProfilePath;
		File file = new File(realRootPath+path);

		if(!(file.exists() && file.isFile())) {
			path = null;
		}

		return path;
	}

	public void createFile(File file, MultipartFile multipartFile) throws Exception {
		if (!file.exists()) {
			file.createNewFile();
		}
		multipartFile.transferTo(file);
	}

	public void createProfileImg(MultipartFile multipartFile) throws Exception {
		File file = new File(realRootPath+contextPath+profilePath+File.separator+multipartFile.getOriginalFilename());
		createFile(file,multipartFile);
	}

	public String createContentFile(int postId, String type,String fileName,MultipartFile multipartFile) throws Exception {
		String dirpath = makePostUploadPath(postId)+File.separator+type;
		String path = dirpath+File.separator+fileName;

		File file = new File(realRootPath+dirpath);
		if(!file.exists()) {
			file.mkdirs();
		}

		file = new File(realRootPath+path);

		createFile(file,multipartFile);

		return path;
	}

	public File getContentImgDir(int postId,String type) throws Exception {

		String dirpath = makePostUploadPath(postId)+ (type!=null ? File.separator+type : "");

		File file = new File(realRootPath+dirpath);

		return file;
	}

	private void clearDirectory(File[] files) {

		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				clearDirectory(files[i].listFiles());
			}
			files[i].delete();
		}
	}

	public void fileRemover(File file) {

		if (file.exists()) {
			if (file.isDirectory()) {
				clearDirectory(file.listFiles());
			}
			file.delete();
		}
	}
}
