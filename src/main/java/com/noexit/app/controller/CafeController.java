package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cafe")
public class CafeController {

	//카페 등록
	@GetMapping("/enroll")
	public String enrollForm() {
		return "cafe/cafeEnrollForm";
	}
	//카페 등록
	@PostMapping("/enroll")
	public String enroll() {
		return "redirect:/theme/list";
	}

}
