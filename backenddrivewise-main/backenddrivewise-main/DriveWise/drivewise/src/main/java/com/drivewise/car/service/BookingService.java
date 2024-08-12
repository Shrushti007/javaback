package com.drivewise.car.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.drivewise.car.customexception.BookingNotFoundException;
import com.drivewise.car.dao.BookingRepo;
import com.drivewise.car.entity.Booking;
@Service
public class BookingService implements CurdService<Booking,Integer>{

	@Value("${image.upload.dir}")
    private String uploadDir;

	@Autowired
	private BookingRepo bookingRepo;
	@Override
	public Booking create(Booking booking) {	
		return bookingRepo.save(booking);
	}

	@Override
	public List<Booking> fetchAll() {
		return bookingRepo.findAll();
	}

	@Override
	public Booking fetchById(Integer id) {
		return bookingRepo.findById(id).orElseThrow(() -> new BookingNotFoundException("Invalid id"));
	}

	@Override
	public Booking update(Booking updatedBooking, Booking existingBooking) {
		existingBooking.setCity(updatedBooking.getCity());
		return bookingRepo.save(existingBooking);
	}

	@Override
	public String delete(Booking booking) {
		bookingRepo.delete(booking);
		return booking.getBookingId() + "deleted";
	}
	
	 public Booking saveBooking(Booking booking, MultipartFile file) throws IOException {
	        if (!file.isEmpty()) {
	            booking.setLicenseImage(file.getBytes());
	        }
	        return bookingRepo.save(booking);
	    }

	    public Optional<Booking> getBookingById(int bookingId) {
	        return bookingRepo.findById(bookingId);
	    }

	    public void saveFile(MultipartFile file, String filename) throws IOException {
	        File targetFile = new File(uploadDir, filename);
	        try (FileOutputStream fos = new FileOutputStream(targetFile)) {
	            fos.write(file.getBytes());
	        }
	    }
}
