package com.noexit.app.common;

import jakarta.servlet.http.HttpSession;

public class AuthUtil {

	//사장님만 통과, 비로그인=로그인페이지, 일반회원=메인
	public static String checkOwner(HttpSession session) {
		
		String role = (String)session.getAttribute("role");
		
		if (role == null)          
			return "redirect:/user/login";
		
		if (!"OWNER".equals(role)) 			
			return "redirect:/theme/list";
		
		return null;
	}

	//사장 + 매니저 통과 
	public static String checkStaff(HttpSession session) {
		
		String role = (String)session.getAttribute("role");
		
		if (role == null) 		
			return "redirect:/user/login";
		
		if (!"OWNER".equals(role) && !"MANAGER".equals(role)) 			
			return "redirect:/theme/list";
		
		return null;
	}
}
