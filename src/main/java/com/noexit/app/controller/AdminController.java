package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.noexit.app.model.Admin;
import com.noexit.app.model.AdminDashboard;
import com.noexit.app.service.AdminService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminService service;

	// 로그인 폼
	@GetMapping("/login")
	public String loginForm() {
		return "admin/adminLoginForm";
	}

	// 로그인 처리
	@PostMapping("/login")
	public String login(Admin admin, HttpSession session, Model model) {

		Admin dto = service.login(admin);

		if (dto == null) {
			model.addAttribute("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
			return "admin/adminLoginForm";
		}

		session.setAttribute("loginAdmin", dto);
		session.setAttribute("role", "ADMIN");
		
		return "redirect:/";
	}

	// 로그아웃
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		//세션 삭제
		
		return "redirect:/admin/login";
	}

	@GetMapping("/main")
	public String adminMain(HttpSession session, Model model) {
		
			// 서비스 호출
			AdminDashboard status = service.getDashboardStats();
			
			System.out.println("데이터 확인: " + status);
			// status 적재
			model.addAttribute("status", status);
		
		// 뷰 리턴
		return "admin/main";
	}
	
	@GetMapping("/cafelist")
	public String adminUserList() {
		
		return "admin/list";
	}
	
	
}
