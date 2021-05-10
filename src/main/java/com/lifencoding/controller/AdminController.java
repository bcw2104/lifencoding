package com.lifencoding.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.lifencoding.entity.AdminVO;
import com.lifencoding.serviceImpl.AdminService;
import com.lifencoding.util.PagePath;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminService adminService;

	@PostMapping("/check.do")
	@ResponseBody
	public String check(HttpServletRequest request)throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(new BufferedReader(new InputStreamReader(request.getInputStream())));

		String id = (String) object.get("id");
		String pw = (String) object.get("pw");

		if(adminService.loginCheck(id, pw)) {
			return "true";
		}else {
			return "false";
		}
	}

	@PostMapping("/login.do")
	@ResponseBody
	public String login(HttpServletRequest request)throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(new BufferedReader(new InputStreamReader(request.getInputStream())));

		String id = (String) object.get("id");
		String pw = (String) object.get("pw");

		if(adminService.loginCheck(id, pw)) {
			HttpSession session = request.getSession();
			session.removeAttribute("guest");
			session.removeAttribute("visit");
			session.setAttribute("admin", true);
			session.setMaxInactiveInterval(3600);

			return "true";
		}else {
			return "false";
		}
	}

	@GetMapping("/logout.do")
	public String logout(HttpSession session)throws Exception {
		session.invalidate();

		return "redirect:/";
	}

	@PostMapping("/find.do")
	@ResponseBody
	public String find(@RequestParam("email")String receivedEmail, Model model)throws Exception {
		AdminVO adminVO = adminService.getAdminInfo();

		String email = adminVO.getAdminEmail();
		String id = adminVO.getAdminId();
		String pw = adminService.changeAdminPwRandom();

		if(receivedEmail.equals(email)) {

			adminService.sendMail(email, id, pw);

			return "true";
		}
		else {
			return "false";
		}
	}

	@GetMapping("/info")
	public String info(Model model)throws Exception {
		AdminVO adminVO = adminService.getAdminInfo();
		model.addAttribute("admin", adminVO);
		model.addAttribute("content", PagePath.adminInfo);

		return "forward:/";
	}

	@GetMapping("/profileImg.do")
	@ResponseBody
	public String profileImg(HttpServletResponse response)throws Exception {
		String path = adminService.getProfileImgPath();

		return path;
	}

	@PostMapping("/changeImg.do")
	@ResponseBody
	public void changeImg(MultipartHttpServletRequest filelist)throws Exception {

	    MultipartFile mfile = filelist.getFile("newImg");

	    adminService.changeProfileImg(mfile);
	}

	@PostMapping("/modify.do")
	@ResponseBody
	public void modify(HttpServletRequest request)throws Exception {
		JSONParser parser = new JSONParser();
		JSONObject object = (JSONObject) parser.parse(new BufferedReader(new InputStreamReader(request.getInputStream())));

		AdminVO adminVO = new AdminVO();

		adminVO.setAdminId((String) object.get("id"));

		if(object.containsKey("pw")) {
			adminVO.setAdminPw((String) object.get("pw"));
		}
		if(object.containsKey("email")) {
			adminVO.setAdminEmail((String) object.get("email"));
		}
		if(object.containsKey("nickname")) {
			adminVO.setAdminNickname((String) object.get("nickname"));
		}
		if(object.containsKey("comment")) {
			adminVO.setAdminComment((String) object.get("comment"));
		}

		adminService.changeAdminInfo(adminVO);
	}

	@GetMapping("/code.do")
	@ResponseBody
	public void code(HttpServletRequest request,@RequestParam("email") String email)throws Exception {

		String code = adminService.sendMail(email, null, null);

		request.getSession().setAttribute("emailCode", code);
	}

	@GetMapping("/confirm.do")
	@ResponseBody
	public String confirm(@RequestParam("code") String code,
							HttpServletRequest request)throws Exception {

		if(request.getSession().getAttribute("emailCode") != null) {
			if(code.equals(request.getSession().getAttribute("emailCode").toString())) {
				request.getSession().removeAttribute("emailCode");
				return "true";
			}else {
				return "false";
			}
		}else {
			return "false";
		}
	}
}
