package com.restwebservices.prac.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restwebservices.prac.bean.User;
import com.restwebservices.prac.dao.service.UserDaoService;
import com.restwebservices.prac.exception.UserNotFoundException;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retrieveAllUser() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public User retreiveUser(@PathVariable Integer id) {
		User user =   userDaoService.findById(id);
		if(user == null) {
			throw new UserNotFoundException("id -> " + id);
		}
		return user;
	}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		userDaoService.saveUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
																			 .path("/{id}")
																			 .buildAndExpand(user.getId())
																			 .toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable Integer id) {
		User user = userDaoService.deleteById(id);
		if(user == null) {
			throw new UserNotFoundException("id -> " + id);
		}
	}
	
}