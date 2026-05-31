package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owner")
public class Owner {
	
	
	@GetMapping("/res/open")
	public String openReserv() {
	
		return "owner/openRes";
		
	}
	
}
