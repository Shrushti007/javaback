package com.drivewise.car.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.drivewise.car.customexception.CarNotFoundException;
import com.drivewise.car.customexception.UserNotFoundException;
import com.drivewise.car.dto.ErrorResponse;
import com.drivewise.car.entity.Cars;
import com.drivewise.car.entity.User;
import com.drivewise.car.service.CarService;
import com.drivewise.car.service.UserService;

@RestController
@RequestMapping("/car")

public class CarController {

	@Autowired
	private CarService carService;

	@PostMapping("/add")
	public ResponseEntity<?> registerCars(@RequestBody Cars cars) {
		Cars createdCar = carService.create(cars);
		return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllCars() {
		try {
			return new ResponseEntity<>(carService.fetchAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>
			(new ErrorResponse("Car Fetching is failed", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/getcar/{carId}")
	public ResponseEntity<?> getUserById(@PathVariable("carId") Integer id) {
		try {
			return  ResponseEntity.ok(carService.fetchById(id));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce=
		    new ErrorResponse("Car Fetching is failed", e.getMessage());
			return new ResponseEntity<>
			(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{carId}")
	public ResponseEntity<?> updatecarById(@PathVariable("carId") Integer id,
			@RequestBody Cars updateCar) {
		try {
			Cars existingCar=carService.fetchById(id);
			
			return  ResponseEntity.ok(carService.update(updateCar, existingCar));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce= new ErrorResponse("Car updation is failed", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/delete/{carId}")
	public ResponseEntity<?>deleteCarById(@PathVariable ("carId") Integer id)
	{ try {
		Cars existingCar=carService.fetchById(id);
		return ResponseEntity.ok(carService.delete(existingCar));
				
	} catch (Exception e) {

		ErrorResponse errorResponce= new ErrorResponse("Car deletion is failead", e.getMessage());
		return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		
	}
		
	}
	@GetMapping("/getbyName/{name}")
	public ResponseEntity<?> getCarByName(@PathVariable String brandName){
		try {
			ResponseEntity res = null;
			Cars foundUser = carService.fetchCarByBrandName(brandName);
			if(foundUser != null) {
				return res.ok(foundUser);
			
			}else {
				throw new CarNotFoundException("Invalid brandname..");
			}
		
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse("fetching cars by brandname failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
		}
		
		
	}
}
