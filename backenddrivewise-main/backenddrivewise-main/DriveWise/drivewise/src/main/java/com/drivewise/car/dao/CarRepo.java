package com.drivewise.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivewise.car.entity.Cars;
import com.drivewise.car.entity.User;



public interface CarRepo extends JpaRepository<Cars, Integer>{
	 Cars findBybrandName(String brandName);

}
