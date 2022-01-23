package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Hello {
	
	@RequestMapping(value="/hello", method= {RequestMethod.GET, RequestMethod.POST})
	public String hello() {
		
		System.out.println("Hello 안녕");
		return"/WEB-INF/views/hello.jsp";
	}

}
