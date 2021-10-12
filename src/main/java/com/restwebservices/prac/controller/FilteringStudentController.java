package com.restwebservices.prac.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.restwebservices.prac.bean.Student;

@RestController
public class FilteringStudentController {

	@GetMapping("/student/one")
	public MappingJacksonValue getOneStudent() {
		Student student  =  new Student("name", "email", "password");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email", "password");
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter("studnetBeanFilterId", filter);
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(student);
		mappingJacksonValue.setFilters(filterProvider );
		
		return mappingJacksonValue;
	}
	
	@GetMapping("/student/all")
	public MappingJacksonValue getAllStudent() {
		List<Student> students =  Arrays.asList(new Student("name1", "email1", "password1"), 
				new Student("name2", "email2", "password2"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("email") ;
		FilterProvider filtersProvider = new SimpleFilterProvider().addFilter("studnetBeanFilterId", filter );
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(students);
		mappingJacksonValue.setFilters(filtersProvider);
		
		return mappingJacksonValue;
	}
}
