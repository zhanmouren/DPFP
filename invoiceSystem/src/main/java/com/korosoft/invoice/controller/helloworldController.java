package com.korosoft.invoice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloworldController {

	@RequestMapping("/hello")
	public String say() {
		return "hello world!";
	}

}
