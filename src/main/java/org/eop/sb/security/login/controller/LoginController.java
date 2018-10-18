package org.eop.sb.security.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lixinjie
 * @since 2018-10-18
 */
@Controller
@RequestMapping("")
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login/login";
	}
}
