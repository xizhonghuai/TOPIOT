/**
 * 
 */
package com.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author XIZHONGHUAI
 *
 */
@Controller
public class PageManger {

	/**
	 * @return
	 */
	@RequestMapping("/")
	public String login() {
		return "login";

	}

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping("home")
	public String home() {
		return "home";

	}

	/**
	 * @return
	 */
	@RequestMapping("newService")
	public String addService() {
		return "newService";

	}
	
	
	
	@RequestMapping("color")
	public String color() {
		return "col";

	}

}
