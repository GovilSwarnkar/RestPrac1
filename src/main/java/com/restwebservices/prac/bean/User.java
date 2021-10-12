package com.restwebservices.prac.bean;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Integer id;
	
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	
	@Past(message = "Birthdate should be in the past")
	private LocalDateTime birthDate;

	public User(String name, LocalDateTime birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

}
