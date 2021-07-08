package com.lifencoding.serviceImpl;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lifencoding.entity.AdminVO;
import com.lifencoding.mapper.AdminMapper;
import com.lifencoding.util.AuthTools;
import com.lifencoding.util.FTPManager;
import com.lifencoding.util.FileTools;
import com.lifencoding.util.MailTools;

@Service
public class AdminService{
	@Autowired
	private AuthTools authTools;
	@Autowired
	private MailTools mailTools;
	@Autowired
	private FTPManager manager;
	@Autowired
	private AdminMapper adminMapper;

	public boolean loginCheck(String id, String pw) throws Exception{
		AdminVO adminVo = adminMapper.select();

		if(adminVo.getAdminId().equals(id) && adminVo.getAdminPw().equals(authTools.convertValuetoHash(pw)))
			return true;
		else
			return false;
	}

	public AdminVO getAdminInfo() {
		return adminMapper.select();
	}

	public void changeAdminInfo(AdminVO adminVO) throws Exception {
		if(adminVO.getAdminPw() != null) {
			adminVO.setAdminPw(authTools.convertValuetoHash(adminVO.getAdminPw()));
		}
		adminMapper.modify(adminVO);
	}

	public String changeAdminPwRandom() throws Exception {
		String randomStr = authTools.makeRandomString(10);
		AdminVO adminVO = new AdminVO();
		adminVO.setAdminPw(randomStr);

		changeAdminInfo(adminVO);

		return randomStr;
	}

	public String sendMail(String email,String id,String pw) throws Exception {
		if(pw != null) {
			mailTools.sendAdminInfoMail(email, id, pw);
			return null;
		}
		else {
			String code = authTools.makeRandomString(8);
			mailTools.sendConfirmMail(email,code);

			return code;
		}
	}

	public String getProfileImgPath() throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String path = fileTools.getProfileImgPath();

		manager.disconnect(client);

		return path;
	}

	public void changeProfileImg(MultipartFile mfile,String fileName) throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String dir = fileTools.getProfileDirPath();

		String old = fileTools.getFirstFileName(dir);

		if(old != null) {
			fileTools.remove(dir, old);
		}

		fileTools.createProfileImg(mfile,fileName);

		manager.disconnect(client);
	}

}
