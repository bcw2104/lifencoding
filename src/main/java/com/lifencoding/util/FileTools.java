package com.lifencoding.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.web.multipart.MultipartFile;

public class FileTools {
	private String link;
	private String root;
	private String profileDirPath;
	private String postDirPath;

	FTPClient client = null;

	public FileTools(FTPClient client) {
		this.link = GlobalValues.link;

		this.root = "/www";
		this.profileDirPath = "/www/resources/user/profile";
		this.postDirPath = "/www/resources/upload/files/post";

		this.client = client;
	}

	public String getProfileDirPath() {
		return profileDirPath;
	}

	public String getPostDirPath() {
		return postDirPath;
	}

	public boolean rename(String oldPath, String newPath) throws IOException {
		boolean reply = client.rename(oldPath, newPath);

		return reply;
	}

	public FTPFile getFile(String dir, String fileName) throws IOException {
		FTPFile file = null;

		FTPFile[] files = client.listFiles(dir);

		for (FTPFile curFile : files) {
			if (curFile.getName().equals(fileName)) {
				file = curFile;
				break;
			}
		}
		return file;
	}

	public void createDirectorys(String path) throws Exception {
		String dirList[] = path.substring(1).split(File.separator);
		String curPath = "";

		for (String dir : dirList) {
			curPath += File.separator + dir;

			if (!client.changeWorkingDirectory(curPath)) {
				client.makeDirectory(curPath);
			}
		}
	}

	public void createProfileImg(MultipartFile multipartFile,String fileName) throws Exception {
		String path = profileDirPath + File.separator + fileName;

		createDirectorys(profileDirPath);
		client.storeFile(path, multipartFile.getInputStream());
	}

	public String createPostTempFile(String dir, String fileName, MultipartFile multipartFile)
			throws Exception {

		String path = dir + File.separator + fileName;

		createDirectorys(dir);
		client.storeFile(path, multipartFile.getInputStream());

		return link + path.replaceFirst(root, "");
	}

	private void removeDirectory(String path) throws IOException {

		FTPFile[] subFiles = client.listFiles(path);

		if (subFiles != null && subFiles.length > 0) {
			for (FTPFile curFile : subFiles) {
				String currentFileName = curFile.getName();

				if (currentFileName.equals(".") || currentFileName.equals("..")) {
					continue;
				}

				String filePath = path + File.separator + currentFileName;

				if (curFile.isDirectory()) {
					removeDirectory(filePath);
				} else {
					client.deleteFile(filePath);
				}
			}
		}
		client.removeDirectory(path);
	}

	public void remove(String dir, String fileName) throws IOException {
		FTPFile file = null;

		file = getFile(dir, fileName);

		if (file != null) {
			if (file.isDirectory()) {
				removeDirectory(dir + File.separator + file.getName());
			} else {
				client.deleteFile(dir + File.separator + file.getName());
			}
		}

	}
}
