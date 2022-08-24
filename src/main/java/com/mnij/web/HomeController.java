package com.mnij.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//홈 화면
	@GetMapping("index")
	public String home() {
		return "index";
	}
}
