package com.drivewise.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drivewise.car.customexception.CarNotFoundException;
import com.drivewise.car.customexception.UserNotFoundException;
import com.drivewise.car.dao.CarRepo;
import com.drivewise.car.entity.Cars;
import com.drivewise.car.entity.User;



@Service
public class CarService implements CurdService<Cars,Integer> {
	
	@Autowired
	private CarRepo carRepo; 

	@Override
	public Cars create(Cars cars) {
		
		return carRepo.save(cars);
	}

	@Override
	public List<Cars> fetchAll() {
		return carRepo.findAll();
	}

	@Override
	public Cars fetchById(Integer id) {
		return carRepo.findById(id).orElseThrow(() -> new CarNotFoundException("Invalid id"));
	}

	@Override
	public Cars update(Cars updatedCar, Cars existingCar) {
		existingCar.setPricePerDay(updatedCar.getPricePerDay());
		existingCar.setStatus(updatedCar.getStatus());
		return carRepo.save(existingCar);
	}

	@Override
	public String delete(Cars cars) {
		carRepo.delete(cars);
		return cars.getCarId() + "deleted";
	}

	public Cars fetchCarByBrandName(String brandName) {
		return carRepo.findBybrandName(brandName);
	}
}
