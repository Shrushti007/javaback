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
import com.drivewise.car.dto.ErrorResponse;
import com.drivewise.car.entity.Cars;
import com.drivewise.car.entity.VintageCar;
import com.drivewise.car.service.VintageCarService;

@RestController
@RequestMapping("/vintagecar")

public class VintageCarController {

	@Autowired
	private VintageCarService vintageCarService;
	
	@PostMapping("/add")
	public ResponseEntity<?> registerCars(@RequestBody VintageCar vintagecar) {
		VintageCar createdCar = vintageCarService.create(vintagecar);
		return new ResponseEntity<>(createdCar, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<?> getAllCars() {
		try {
			return new ResponseEntity<>(vintageCarService.fetchAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>
			(new ErrorResponse("Car Fetching is failed", e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}

	}
	@GetMapping("/getcar/{vintageCarId}")
	public ResponseEntity<?> getUserById(@PathVariable("vintageCarId") Integer id) {
		try {
			return  ResponseEntity.ok(vintageCarService.fetchById(id));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce=
		    new ErrorResponse("Car Fetching is failed", e.getMessage());
			return new ResponseEntity<>
			(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/update/{vintageCarId}")
	public ResponseEntity<?> updatecarById(@PathVariable("vintageCarId") Integer id,
			@RequestBody VintageCar updateCar) {
		try {
			VintageCar existingCar=vintageCarService.fetchById(id);
			
			return  ResponseEntity.ok(vintageCarService.update(updateCar, existingCar));
			
		} catch (Exception e) {
			
			ErrorResponse errorResponce= new ErrorResponse("Car updation is failed", e.getMessage());
			return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("/delete/{vintageCarId}")
	public ResponseEntity<?>deleteCarById(@PathVariable ("vintageCarId") Integer id)
	{ try {
		VintageCar existingCar=vintageCarService.fetchById(id);
		return ResponseEntity.ok(vintageCarService.delete(existingCar));
				
	} catch (Exception e) {

		ErrorResponse errorResponce= new ErrorResponse("Car deletion is failead", e.getMessage());
		return new ResponseEntity<>(errorResponce,HttpStatus.BAD_REQUEST);
		
	}
		
	}
	@GetMapping("/getbyName/{name}")
	public ResponseEntity<?> getCarByName(@PathVariable String make){
		try {
			ResponseEntity res = null;
			VintageCar foundUser = vintageCarService.fetchCarByMake(make);
			if(foundUser != null) {
				return res.ok(foundUser);
			
			}else {
				throw new CarNotFoundException("Invalid make..");
			}
		
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorResponse("fetching cars by name failed.", e.getMessage()),HttpStatus.BAD_REQUEST);
		}
	
}
}
