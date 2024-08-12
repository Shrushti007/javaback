package com.drivewise.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivewise.car.entity.User;

public interface UserRepo extends JpaRepository<User, Integer>{

	 User findByUserName(String name);
}
