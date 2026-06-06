package com.noexit.app.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.noexit.app.common.PaginateUtil;
import com.noexit.app.model.MyReservationDTO;
import com.noexit.app.service.MyReservationService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor

public class MyPageReserv {
	
	private final MyReservationService service;
	private final PaginateUtil paginateUtil;
	
@GetMapping("/reservations")
	public String reservations(@RequestParam(name="tab", defaultValue="1") int currentTab
			, @RequestParam(name="page", defaultValue="1") int currentPage
			, HttpSession session
			, Model model) {
	
			// 세션에서 userId 받아오기 
			//long userId = (long)session.getAttribute("userId");
			long userId = 1L;
	
		try {
			
			int size = 10;
			int totalPage = 0 ;
			int dataCount = 0;
			
			// 탭 별 전체 페이지 수
			if(currentTab==1) {
				dataCount = service.bookedCount(userId);
			}else if(currentTab==2) {
				dataCount = service.doneCount(userId);
			}else if(currentTab==3) {
				dataCount = service.canceledCount(userId);
			}
			
			
			if(dataCount!=0)
				totalPage = paginateUtil.pageCount(dataCount, size);
			
			// 리스트에 출력할 데이터 가져오기
			int offset = (currentPage - 1) * size;
			if(offset<0)
				offset = 0;
			
			Map<String, Object> map = new HashMap<>();
			map.put("offset", offset);
			map.put("size", size);
			map.put("userId", userId);
			
			// 탭 별 목록 조회
			List<MyReservationDTO> list = new ArrayList<>();
			
			if(currentTab==1) {
				list = service.bookedList(map);
				model.addAttribute("list", list);
			}else if(currentTab==2) {
				list = service.doneList(map);
				model.addAttribute("list", list);
			}else if(currentTab==3) {
				list = service.canceledList(map);
				model.addAttribute("list", list);
			}

			// 페이징 url 구성
			String listUrl = "/reservations?tab="+currentTab;
			String paging = paginateUtil.paging(currentPage, totalPage, listUrl);
			
			model.addAttribute("bookedCount", service.bookedCount(userId));
			model.addAttribute("tab", currentTab);
			model.addAttribute("page", currentPage);
			model.addAttribute("dataCount", dataCount);
			model.addAttribute("size", size);
			model.addAttribute("totalPage", totalPage);
			model.addAttribute("paging", paging);
			
			
			
		} catch (Exception e) {
			log.error("reservationsList: ",e);
		}
	
	
		return "mypage/reservations";
	}
		
}


