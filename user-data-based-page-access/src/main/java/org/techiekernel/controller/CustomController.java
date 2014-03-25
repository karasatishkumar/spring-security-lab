package org.techiekernel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.techiekernel.service.user.CustomAuthenticationProvider;
import org.techiekernel.service.user.CustomUserDetailsInMemoryService;
import org.techiekernel.spring.user.CustomUserDetails;
import org.techiekernel.spring.user.CustomUserDetailsImpl;

@Controller
public class CustomController {

	@Autowired
	private CustomUserDetailsInMemoryService pudsim;

	@Autowired
	private CustomAuthenticationProvider pap;

	@RequestMapping(value = "/login")
	public ModelAndView slash() {
		System.out.println("coming to login page.");
		ModelAndView result = new ModelAndView("login");

		return result;
	}

	@RequestMapping(value = "/index")
	public ModelAndView index() {
		
		System.out.println("coming to the index page");

		ModelAndView result = new ModelAndView("index");

		// Anyone logged in?
		CustomUserDetails user = CustomAuthenticationProvider.getLoggedInUser();

		if (user == null) {
			result.addObject("loggedIn", "No one is logged in");
		} else {
			result.addObject("loggedIn", user.getUsername() + " is logged in");
		}

		result.addObject("users", this.pudsim.getUsers());

		return result;

	}

	@RequestMapping(value = "/delete/{id}")
	public String delete(@PathVariable(value = "id") String id) {

		this.pudsim.deleteUser(Long.parseLong(id));

		return "redirect:/";

	}

	@RequestMapping(value = "/create")
	@SuppressWarnings("AssignmentToMethodParameter")
	public ModelAndView add(@RequestParam(value = "name") String name,
			@RequestParam(value = "password") String password) {

		name = StringUtils.replace(name, " ", "");
		password = StringUtils.replace(password, " ", "");

		String errorMsg = "";

		if (name.length() == 0) {
			errorMsg += "Name is empty ";
		}

		if (password.length() == 0) {
			errorMsg += "Password is empty ";
		}

		if (errorMsg.isEmpty()) {
			this.pudsim.upsertUser(new CustomUserDetailsImpl(name, password));
		}

		ModelAndView result = new ModelAndView("create");

		result.addObject("errorMsg", errorMsg);
		result.addObject("username", name);

		return result;

	}

	@RequestMapping(value = "/accessDenied")
	public ModelAndView accessDenied() {

		ModelAndView result = new ModelAndView("accessDenied");

		return result;

	}

	@RequestMapping(value = "/invalidSession")
	public ModelAndView invalidSession() {

		ModelAndView result = new ModelAndView("invalidSession");

		result.addObject("noUser", (SecurityContextHolder.getContext()
				.getAuthentication() == null));

		return result;

	}

}
