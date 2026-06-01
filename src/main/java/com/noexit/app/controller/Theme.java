package com.noexit.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/theme/*")
public class Theme
{
	@GetMapping("list")
	public String themeList()
	{
		return "theme/themelist";
	}
}
