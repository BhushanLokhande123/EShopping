package com.app.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	 
	public HomeController() {
		System.out.println("in ctor of "+getClass());
	}
	
	 @GetMapping("/")
	 public String showHomePage(Model map)
	 {
		 System.out.println("in home page");
		 map.addAttribute("ts", new Date());
		 return "/index";
	 }
	 
}
