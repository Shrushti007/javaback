package com.drivewise.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivewise.car.customexception.UserNotFoundException;
import com.drivewise.car.dao.UserRepo;
import com.drivewise.car.entity.User;
@Service

public class UserService implements CurdService<User,Integer>{

	@Autowired
	private UserRepo userRepo;

	@Override
	public User create(User user) {
		return userRepo.save(user);
	}

	@Override
	public List<User> fetchAll() {
		
		return userRepo.findAll();
	}

	@Override
	public User fetchById(Integer id) {
		return userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Invalid id"));
	}

	@Override
	public User update(User updatedUser, User existingUser) {
		existingUser.setUserName(updatedUser.getUserName());
		existingUser.setPassword(updatedUser.getPassword());
		return userRepo.save(existingUser);
	}

	@Override
	public String delete(User user) {
		userRepo.delete(user);
		return user.getId() + "deleted";
	}
	
	public User fetchUserByUserName(String name) {
		return userRepo.findByUserName(name);
	}
}
