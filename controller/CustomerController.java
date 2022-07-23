package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.dao.ITutorialDao;
import com.app.service.ITopicService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	//dep : service layer i/f
	@Autowired
	private ITopicService service;
	//dep : tut dao i/f
	@Autowired
	private ITutorialDao tutDao;
	
	public CustomerController() {
		System.out.println("in ctor of "+getClass());
	}
	//add req handling method : to forward clnt to topics.jsp
	@GetMapping("/topics")
	public String getAllTopics(Model map)
	{
		System.out.println("in get all topics");
		map.addAttribute("topics", service.getAllTopics());
		return "/customer/topics";//AVN : /WEB-INF/views/customer/topics.jsp
	}
	
	//add req handling method for getting tuts by selected topic
	@GetMapping("/tutorials")
	public String getTutorialsByTopic(Model map,@RequestParam long topicId)
	{
		System.out.println("in get tuts "+topicId);
		map.addAttribute("tut_names", tutDao.getTutorialNamesByTopic(topicId));
		return "/customer/tutorials";			//AVN : /WEB-INF/views/customer/tutorials.jsp
	}
	

}
