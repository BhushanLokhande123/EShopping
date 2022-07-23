package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.pojos.Role;
import com.app.pojos.User;
import com.app.service.IUserService;

@Controller
@RequestMapping("/user") 		//for segragation purpose...
public class UserController {
	// dependency : service layer i/f...for login verifiction...
	@Autowired
	private IUserService userService;

	public UserController() {
		System.out.println("in ctor of " + getClass());
	}

	// add req handling method to show login form...
	@GetMapping("/login")
	public String showLoginForm() {
		System.out.println("in show login form");
		return "/user/login";   // AVN : /WEB-INF/views/user/login.jsp
	}

	// add req handling method for processing login form...
	// entry in Handler Mapping bean...
	// key : /user/login + method=POST +.....
	// value : com.app.controller.UserController.processLoginForm
	@PostMapping("/login")
	public String processLoginForm(@RequestParam String email, @RequestParam("pass") String pwd, 
			Model map,HttpSession session)
	// recommended : MATCH req param names to method arg names for easy binding...
	// SC : String pwd=request.getParameter("pass");
	{
		System.out.println("in process login form " + email + " " + pwd + " " + map);
		try {
			// invoke service layer method...
			User user = userService.authenticateUser(email, pwd);
			//as we are redirecting it to new request through client browser so its under session scope...
			session.setAttribute("mesg",
					"Login Successful, Hello , " + user.getName() + " , logged in under " + user.getRole() + " role");
			// => valid login --proceed to authorization...
			if (user.getRole() == Role.CUSTOMER)
				return "redirect:/customer/topics"; 		//this new equest goes to controller...
			//SC : response.sendRedirect(response.encodeRedirectURL("/customer/topics");
			//WC :temp resp pkt : SC 302 | location : /customer/topics ... | body : empty
			//Clnt browser send a new req : method = GET , http://host:port/day17_boot/customer/topics
			// => admin login success...
			return "redirect:/admin/status";
		} catch (RuntimeException e) {
			//to identify error in which part...
			System.out.println("err in " + getClass() + " exc " + e);
			//if wrong inputs then show msg there only...
			map.addAttribute("mesg", "Invalid Login , Please retry !!!!!!!!!!!!!");
			return "/user/login";// AVN : /WEB-INF/views/user/login.jsp
		}
		
	}

}
