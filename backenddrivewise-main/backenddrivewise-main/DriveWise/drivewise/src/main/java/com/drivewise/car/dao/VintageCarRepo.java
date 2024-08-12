package com.drivewise.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivewise.car.entity.User;
import com.drivewise.car.entity.VintageCar;

public interface VintageCarRepo extends JpaRepository<VintageCar, Integer>{

	 VintageCar findByMake(String make);
}
