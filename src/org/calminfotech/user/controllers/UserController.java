package org.calminfotech.user.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/user")
public class UserController {

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String indexAction(){
		return "redirect:/";
	}

}
