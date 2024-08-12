package com.drivewise.car.customexception;

public class BookingNotFoundException  extends RuntimeException {

	public BookingNotFoundException(String msg) {
		super(msg);
}
}