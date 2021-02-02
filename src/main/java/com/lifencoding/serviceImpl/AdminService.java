package com.lifencoding.serviceImpl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lifencoding.entity.AdminVO;
import com.lifencoding.mapper.AdminMapper;
import com.lifencoding.util.AuthTools;
import com.lifencoding.util.FileTools;
import com.lifencoding.util.MailTools;

@Service
public class AdminService{
	@Autowired
	private AuthTools authTools;
	@Autowired
	private MailTools mailTools;
	@Autowired
	private FileTools fileTools;
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
		String path = fileTools.findProfileImgPath();

		if(path == null) {
			path = fileTools.findDefaultProfileImg();
		}

		return path;
	}

	public void changeProfileImg(MultipartFile mfile) throws Exception {
		File file = fileTools.findProfileImg();

		if(file != null) {
			fileTools.fileRemover(file);
		}
		fileTools.createProfileImg(mfile);
	}

}
