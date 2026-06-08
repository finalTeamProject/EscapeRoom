package com.noexit.app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.noexit.app.service.MyReservationService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor

public class MyPageReserv {
	
	private final MyReservationService service;
	
	@GetMapping("/reservations")
	public String reservations(@RequestParam(name="tab", defaultValue="1") int currentTab
			, @RequestParam(name="page", defaultValue="1") int currentPage
			, HttpSession session
			, Model model) {
	
			// 세션에서 userId 받아오기 
			//long userId = (long)session.getAttribute("userId");
			long userId = 5L;
	
		try {
			int size = 10;
			
			Map<String, Object> pageData = service.getReservationPageData(userId, currentTab, currentPage, size);

			model.addAllAttributes(pageData);

			
		} catch (Exception e) {
			log.error("reservationsList: ",e);
		}
	
		model.addAttribute("tab", currentTab);
		model.addAttribute("page", currentPage);
		
	
		return "mypage/reservations";
	}

	@PostMapping("/reservations/cancel")
	@ResponseBody
	//-- Map을 JSON으로 변환해서 반환 문자열 반환해서 뷰리졸버로 보내는게 아니라(JSP 화면을 보내는게 아니라서)
	//  데이터 자체를 응답으로 보낼 때 사용 
	public Map<String, Object> cancel(@RequestParam(name="reservationId") long reservationId
			, HttpSession session) {

		// 세션에서 userId 받아오기 
		//long userId = (long)session.getAttribute("userId");
			long userId = 5L;
			
			Map<String, Object> result = new HashMap<>();
			
		try {
			
			
			service.cancelReservation(reservationId, userId);
			result.put("success", true);
			result.put("message", "예약이 성공적으로 취소되었습니다.");
			
			
		} catch (Exception e) {
			result.put("success", false);
			result.put("message", e.getMessage());
			
			log.error("cancel: ",e);
		}
		
		return result;
	}
}


