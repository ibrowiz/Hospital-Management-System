package org.calminfotech.system.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/system")
public class SystemController {

	@RequestMapping(value = { "", "/index" }, method = RequestMethod.GET)
	public String index() {
		return "redirect:/";
	}

}
