package com.restwebservices.prac.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.restwebservices.prac.bean.RestBean;

@RestController
public class RestPracController {

	@Autowired
	MessageSource messageSource;
	
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
	
	@GetMapping("/internationalization/v1")
	public String internationalizationV1(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
		return messageSource.getMessage("good.morning.message",null,  locale) ;
	}
	
	@GetMapping("/internationalization/v2")
	public String internationalizationV2() {
		return messageSource.getMessage("good.morning.message",null,  LocaleContextHolder.getLocale()) ;
	}
	
}
