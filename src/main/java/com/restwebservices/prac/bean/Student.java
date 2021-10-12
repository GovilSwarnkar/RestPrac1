package com.restwebservices.prac.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@JsonIgnoreProperties(value= {"email", "password"})  //JsonIgnoreProperties and JsonIgnore perform static filtering
@JsonFilter("studnetBeanFilterId")
public class Student {

	private String name;
	
	//@JsonIgnore
	private String email;
	
	//@JsonIgnore
	private String password;

}
