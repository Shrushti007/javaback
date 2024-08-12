package com.drivewise.car.customexception;

public class CarNotFoundException extends RuntimeException {

	public CarNotFoundException(String msg) {
		super(msg);
}

}
