package com.ch.mybatis.controller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ch.mybatis.model.Member;
import com.ch.mybatis.model.MemberPhoto;
import com.ch.mybatis.service.MemberService;
@Controller
public class MemberController {
	@Autowired
	private MemberService ms;
	@RequestMapping("joinForm")
	public String joinForm() {
		return "joinForm";
	}
	@RequestMapping("joinForm2")
	public String joinForm2() {
		return "joinForm2";
	}
//	@RequestMapping("idChk")
//	public String idChk(String id, Model model) {
//		String msg = "";
//		Member member = ms.select(id);
//		if (member == null) msg = "사용 가능한 아이디 입니다";
//		else msg = "이미 사용중이니 다른 아이디를 사요하세요";
//		model.addAttribute("msg", msg);
//		return "idChk";
//	}
	@RequestMapping(value="idChk", produces = "text/html;charset=utf-8")
	@ResponseBody   // jsp로 가지말고 바로 본문을 전달
	public String idChk(String id) {
		String msg = "";
		Member member = ms.select(id);
		if (member == null) msg = "쭈아 그 아이디야";
		else msg ="딴 아이디써";
		return msg;
	}
	@RequestMapping("join")
	public String join(Member member, Model model, HttpSession session) throws IOException {
		int result = 0;
		// member는 화면에서 입력한 데이터이고 memebr2는 DB에서 id로 읽은 데이터
		Member member2 = ms.select(member.getId());
		if (member2 == null) {
			String fileName = member.getFile().getOriginalFilename();
			member.setFileName(fileName);
			String real = 
				session.getServletContext().getRealPath("/resources/upload");
			FileOutputStream fos = new FileOutputStream(
					new File(real+"/"+fileName));
			fos.write(member.getFile().getBytes());
			fos.close();
			result = ms.insert(member);
		} else result = -1;  // 이미 있으니 입력하지마
		model.addAttribute("result", result);
		return "join";
	}
	@RequestMapping("join2")
	public String join2(Member member, Model model, HttpSession session,
			MultipartHttpServletRequest mhr) throws IOException {
		int result = 0;
		// member는 화면에서 입력한 데이터이고 memebr2는 DB에서 id로 읽은 데이터
		Member member2 = ms.select(member.getId());
		if (member2 == null) {
			// 여러개 파일명을 한번에 받아온다
			List<MultipartFile> list = mhr.getFiles("file");
			// 사진을 여러개 가진 list, 하나씩 photo에저장하고 그것을 photos에 add
			List<MemberPhoto> photos = new ArrayList<MemberPhoto>();
			String real = 
				session.getServletContext().getRealPath("/resources/upload");
			// list의 사진을 하니씩 뽑아서 photos으로 저장
			for (MultipartFile mf : list) {
				MemberPhoto mp = new MemberPhoto();
				String fileName = mf.getOriginalFilename();
				mp.setFileName(fileName);
				mp.setId(member.getId());
				// photos 그림 하나짜리 mp가 여러개 저장
				photos.add(mp);
				// 파일 업로드
				FileOutputStream fos = new FileOutputStream(
						new File(real+"/"+fileName));
				fos.write(mf.getBytes());
				fos.close();
				member.setFileName(fileName);
			}				
			result = ms.insert(member);
			if (result > 0) ms.insertPhoto(photos);
		} else result = -1;  // 이미 있으니 입력하지마
		model.addAttribute("result", result);
		return "join";
	}
	@RequestMapping("loginForm")
	public String loginForm() {
		return "loginForm";
	}
	@RequestMapping("login")
	public String login(Member member, Model model, HttpSession session) {
		int result = 0; // 암호가 다름
		Member member2 = ms.select(member.getId());
		if (member2 == null || member2.getDel().equals("y")) 
			result = -1; // 없는 ID 
		else if (member2.getPassword().equals(member.getPassword())) {
			result = 1; // 성공 id와 password가 일치
			session.setAttribute("id", member.getId());
		} 
		model.addAttribute("result", result);
		return "login";
	}
	@RequestMapping("view")
	public String view(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		Member member = ms.select(id);
		model.addAttribute("member", member);
		return "view";
	}
	@RequestMapping("view2")
	public String view2(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		Member member = ms.select(id);
		List<MemberPhoto> list = ms.listPhoto(id);
		model.addAttribute("member", member);
		model.addAttribute("list",list);
//		for(MemberPhoto mp: list) {
//			System.out.println(mp.getFileName());
//		}
		return "view2";
	}
	@RequestMapping("updateForm")
	public String updateForm(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		Member member = ms.select(id);
		model.addAttribute("member", member);
		return "updateForm";
	}
	@RequestMapping("update")
	public String update(Member member, Model model, HttpSession session) throws IOException {
		int result = 0;
//		fileName는 null일수도 있고 값을 가지고 올 수도 있다
		String fileName = member.getFile().getOriginalFilename();
		if (fileName != null && !fileName.equals("")) {			
			member.setFileName(fileName);
			String real = 
				session.getServletContext().getRealPath("/resources/upload");
			FileOutputStream fos = new FileOutputStream(
					new File(real+"/"+fileName));
			fos.write(member.getFile().getBytes());
			fos.close();			
		} 
		result = ms.update(member);
		model.addAttribute("result", result);
		return "update";
	}
	@RequestMapping("delete")
	public String delete(Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		int result = ms.delete(id);
		if (result > 0) session.invalidate();
		model.addAttribute("result", result);
		return "delete";
	}
	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "logout";
	}
	@RequestMapping("main")
	public String main(Member member, Model model, HttpSession session) {
		String id = (String)session.getAttribute("id");
		if (id != null && !id.equals("")) {
			member = ms.select(id);
			model.addAttribute("member", member);
		}
		return "main";
	}
}