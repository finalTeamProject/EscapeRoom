package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cafe")
public class CafeController {

	@GetMapping("/enroll")
	public String enrollForm() {
		return "cafe/cafeEnrollForm";
	}

}
