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
		AdminVO adminVO = adminMapper.select();

		if(adminVO.getAdminId().equals(id) && adminVO.getAdminPw().equals(authTools.convertValuetoHash(pw)))
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

	public void changeProfileImg(MultipartFile mfile,String fileName) throws Exception {
		FTPClient client = manager.connect();
		FileTools fileTools = new FileTools(client);

		String oldFileName = adminMapper.select().getAdminImg();

		String dir = fileTools.getProfileDirPath();

		fileTools.remove(dir, oldFileName);

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminImg(fileName);
		adminMapper.modify(adminVO);

		fileTools.createProfileImg(mfile,fileName);

		manager.disconnect(client);
	}

}
