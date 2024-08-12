package com.drivewise.car.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drivewise.car.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{


}
