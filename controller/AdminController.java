package com.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")

public class AdminController {
	
	public AdminController() {
		System.out.println("in ctor of " + getClass());
	}

//add request handling method to forward admin to status page
	@GetMapping("/status")
	public String showAdminStatus() {
		System.out.println("in admin status ");
		return "/admin/status";
	}
}
