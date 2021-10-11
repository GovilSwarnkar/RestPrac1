package com.restwebservices.prac.dao.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.restwebservices.prac.bean.User;

@Component
public class UserDaoService {

	private static List<User> users = new ArrayList<User>();

	private static int userCount = 4;

	static {
		users.add(new User(1, "Shiv", LocalDateTime.now()));
		users.add(new User(2, "Ram", LocalDateTime.now()));
		users.add(new User(3, "Jagan", LocalDateTime.now()));
		users.add(new User(4, "Raghav", LocalDateTime.now()));
	}

	public List<User> findAll() {
		return users;
	}

	public User saveUser(User user) {
		if (user.getId() == null) {
			user.setId(++userCount);
		}
		users.add(user);
		return user;
	}

	public User findById(Integer id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}
	
}
