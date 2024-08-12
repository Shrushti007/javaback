package com.drivewise.car.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.drivewise.car.customexception.VintageCarNotFoundException;
import com.drivewise.car.dao.VintageCarRepo;
import com.drivewise.car.entity.Cars;
import com.drivewise.car.entity.VintageCar;

@Service
public class VintageCarService  implements CurdService<VintageCar,Integer>  {
	
	@Autowired
	private VintageCarRepo vintageCarRepo;

	@Override
	public VintageCar create(VintageCar vintagecar) {
		return vintageCarRepo.save(vintagecar);
	}

	@Override
	public List<VintageCar> fetchAll() {
		return vintageCarRepo.findAll();
	}

	@Override
	public VintageCar fetchById(Integer id) {
		return vintageCarRepo.findById(id).orElseThrow(() -> new VintageCarNotFoundException("Invalid id"));
	}

	@Override
	public VintageCar update(VintageCar updatedVintageCar, VintageCar existingVintageCar) {
		existingVintageCar.setPrice(updatedVintageCar.getPrice());
		existingVintageCar.setYear(updatedVintageCar.getYear());
		return vintageCarRepo.save(existingVintageCar);
	}

	@Override
	public String delete(VintageCar vintagecar) {
		vintageCarRepo.delete(vintagecar);
		return vintagecar.getVintageCarId() + "deleted";
	}

	public VintageCar fetchCarByMake(String make) {
		return vintageCarRepo.findByMake(make);
	}

}
