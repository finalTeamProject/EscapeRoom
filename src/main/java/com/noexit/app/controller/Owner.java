package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.noexit.app.common.AuthUtil;
import com.noexit.app.model.Manager;
import com.noexit.app.model.Manner;
import com.noexit.app.model.ThemeDTO;
import com.noexit.app.model.User;
import com.noexit.app.service.AttendanceService;
import com.noexit.app.service.CafeService;
import com.noexit.app.service.CommonService;
import com.noexit.app.service.GenreService;
import com.noexit.app.service.ManagerService;
import com.noexit.app.service.ThemeService;
import com.noexit.app.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/owner/*")
public class Owner {

	private final CafeService cafeService;
	private final ThemeService themeService;
	private final GenreService genreService;
	private final CommonService commonService;
	private final ManagerService managerService;
	private final UserService userService;
	private final AttendanceService attendanceService;


	@GetMapping("/res/open")
	public String openReserv() {
		return "owner/openRes";
	}

	@GetMapping("/res/list")
	public String resList() {
		return "owner/resList";
	}

	// 테마 등록 폼
	@GetMapping("/theme/enroll")
	public String enrollForm(HttpSession session, Model model) {	
		
		String redirect = AuthUtil.checkOwner(session); 
		if (redirect != null) 
			return redirect;

		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("cafeList",   cafeService.selectByUserId(loginUser.getUserId()));
		model.addAttribute("genreList",  genreService.getGenreList());
		model.addAttribute("commonList", commonService.getCommonList());
		return "theme/themeEnrollForm";
	}

	// 테마 등록 처리
	@PostMapping("/theme/enroll")
	public String enroll(ThemeDTO dto, HttpSession session, Model model) {
		
		String redirect = AuthUtil.checkOwner(session); 
		if (redirect != null) return redirect;

		try {
			themeService.themeInsert(dto);
		} catch (Exception e) {
			log.info("themeInsert : ", e);

			User loginUser = (User) session.getAttribute("loginUser");
			model.addAttribute("errorMessage", "테마 등록 중 오류가 발생했습니다.");
			model.addAttribute("cafeList",   cafeService.selectByUserId(loginUser.getUserId()));
			model.addAttribute("genreList",  genreService.getGenreList());
			model.addAttribute("commonList", commonService.getCommonList());
			return "theme/themeEnrollForm";
		}
		return "redirect:/theme/list";
	}


	// 출석체크
	@GetMapping("/attendance")
	public String attendance(HttpSession session) {
		String redirect = AuthUtil.checkStaff(session); if (redirect != null) return redirect;
		return "owner/attendance";
	}

	// 노쇼 처리 (출석체크 페이지에서 호출)
	@PostMapping("/attendance/noshow")
	public String noshow(@RequestParam(name = "userId") Long userId
	                   , HttpSession session
	                   , RedirectAttributes ra) {
		String redirect = AuthUtil.checkStaff(session); 
		if (redirect != null) 
			return redirect;

		try {
			Manner result = attendanceService.noshow(userId);
			ra.addFlashAttribute("resultMessage", "노쇼 처리 완료. 매너온도: " + result.getNewTemp());
		} catch (Exception e) {
			log.info("noshow : ", e);
			ra.addFlashAttribute("resultMessage", "노쇼 처리 실패");
		}
		return "redirect:/owner/attendance";
	}


	// 매니저 목록
	@GetMapping("/manager")
	public String manager(HttpSession session, Model model) {
		String redirect = AuthUtil.checkOwner(session); 
		if (redirect != null) 
		return redirect;

		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("managerList", managerService.selectActiveByOwnerUserId(loginUser.getUserId()));
		return "owner/manager";
	}

	// 매니저 임명 폼
	@GetMapping("/manager/enroll")
	public String managerEnrollForm(HttpSession session, Model model) {
		
		String redirect = AuthUtil.checkOwner(session); if (redirect != null) return redirect;

		User loginUser = (User) session.getAttribute("loginUser");
		model.addAttribute("cafeList", cafeService.selectByUserId(loginUser.getUserId()));
		return "owner/managerEnrollForm";
	}

	// 매니저 임명 처리
	@PostMapping("/manager/enroll")
	public String managerEnroll(@RequestParam(name = "loginId") String loginId
	                          , @RequestParam(name = "cafeId")  Long cafeId
	                          , HttpSession session
	                          , Model model) {
		String redirect = AuthUtil.checkOwner(session); 
		if (redirect != null) 
			return redirect;

		User loginUser = (User) session.getAttribute("loginUser");
		User target = userService.selectByLoginId(loginId);

		if (target == null) {
			model.addAttribute("errorMessage", "해당 아이디의 회원이 없습니다.");
			model.addAttribute("cafeList", cafeService.selectByUserId(loginUser.getUserId()));
			return "owner/managerEnrollForm";
		}

		Manager manager = new Manager();
		manager.setCafeId(cafeId);
		manager.setUserId(target.getUserId());
		try {
			managerService.enroll(manager);
		} catch (Exception e) {
			log.info("managerEnroll : ", e);
		}
		return "redirect:/owner/manager";
	}

	// 매니저 해제
	@PostMapping("/manager/deact")
	public String managerDeact(@RequestParam(name = "cafeId") Long cafeId
	                         , @RequestParam(name = "userId") Long userId
	                         , HttpSession session) {
		String redirect = AuthUtil.checkOwner(session); 
		if (redirect != null) 
			return redirect;

		Manager manager = new Manager();
		manager.setCafeId(cafeId);
		manager.setUserId(userId);
		try {
			managerService.deact(manager);
		} catch (Exception e) {
			log.info("managerDeact : ", e);
		}
		return "redirect:/owner/manager";
	}
}
