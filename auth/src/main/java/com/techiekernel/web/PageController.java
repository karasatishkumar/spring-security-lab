package com.techiekernel.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping ("/techiekernel/welcome.html")
	public String welcome() {
		return "welcome";
	}

	@RequestMapping ("/techiekernel/signin.html")
	public String signin() {
		return "signin";
	}

}
