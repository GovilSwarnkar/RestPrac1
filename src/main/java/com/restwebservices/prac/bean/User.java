package com.restwebservices.prac.bean;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	private Integer id;
	private String name;
	private LocalDateTime birthDate;

	public User(String name, LocalDateTime birthDate) {
		super();
		this.name = name;
		this.birthDate = birthDate;
	}

}
