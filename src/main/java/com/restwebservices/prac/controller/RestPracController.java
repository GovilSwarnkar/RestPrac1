package com.restwebservices.prac.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.restwebservices.prac.bean.RestBean;

@RestController
public class RestPracController {

	@GetMapping("/string")
	public String getStringValue() {
		return "Hi, I am string value";
	}
	
	@GetMapping("/json")
	public RestBean getJsonValue() {
		return new RestBean("Json Johnson");
	}
	
	@GetMapping("/path-variable/{name}")
	public RestBean getJsonValue(@PathVariable String name) {
		return new RestBean(name);
	}
}
