package com.restwebservices.prac.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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

import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class UserController {

	@Autowired
	UserDaoService userDaoService;

	@GetMapping("/users")
	public List<User> retrieveAllUser() {
		return userDaoService.findAll();
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> retreiveUser(@PathVariable Integer id) {
		User user =   userDaoService.findById(id);
		if(user == null) {
			throw new UserNotFoundException("id -> " + id);
		}
		
		EntityModel<User> model = EntityModel.of(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUser());
		model.add(linkTo.withRel("all-users"));
		
		return model;
	}
	
	@PostMapping("/users")
	@ApiOperation(value = "Endpoint to create new user", notes = "This endpoint is added to createUser in controlled and automated way")
	@ApiResponses(value = {@ApiResponse(responseCode = "201",  description  = "User created successfully."),
			@ApiResponse(responseCode = "500",  description  = "Internal server error."),
			@ApiResponse(responseCode = "400",  description  = "Bad Request. Input invalid."),
			@ApiResponse(responseCode = "401",  description  = "The requested completed successfully."),
			@ApiResponse(responseCode = "401",  description  = "Not authenticated. User session invalid."),
			@ApiResponse(responseCode = "403",  description  = "Not authorized."),
			@ApiResponse(responseCode = "200",  description  = "Request Accepted.")})
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