package com.drivewise.car.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.drivewise.car.customexception.BookingNotFoundException;
import com.drivewise.car.entity.Booking;
import com.drivewise.car.service.BookingService;

@CrossOrigin("*")
@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/save")
    public ResponseEntity<String> saveBooking(
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr,
            @RequestParam("city") String city,
            @RequestParam("licenseNo") long licenseNo,
            @RequestParam("userId") int userId,
            @RequestParam("carId") int carId,
            @RequestParam("file") MultipartFile file) {

        try {
            // Convert string dates to LocalDate and then to java.sql.Date
            LocalDate startDateLocal = LocalDate.parse(startDateStr);
            LocalDate endDateLocal = LocalDate.parse(endDateStr);
            Date startDate = Date.valueOf(startDateLocal);
            Date endDate = Date.valueOf(endDateLocal);

            // Create and set up Booking object
            Booking booking = new Booking();
            booking.setStartDate(startDate);
            booking.setEndDate(endDate);
            booking.setCity(city);
            booking.setLicenseNo(licenseNo);
            // Note: Set User and Cars entities here if applicable

            // Save the booking and image
            bookingService.saveBooking(booking, file);

            return ResponseEntity.ok("Booking saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to save booking.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid date format.");
        }
    }

    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Integer bookingId) {
        try {
            Booking booking = bookingService.fetchById(bookingId);
            return ResponseEntity.ok(booking);
        } catch (BookingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.fetchAll();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<String> updateBooking(
            @PathVariable Integer bookingId,
            @RequestParam("city") String city) {

        try {
            Booking existingBooking = bookingService.fetchById(bookingId);
            existingBooking.setCity(city);
            bookingService.update(existingBooking, existingBooking);
            return ResponseEntity.ok("Booking updated successfully.");
        } catch (BookingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
        }
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable Integer bookingId) {
        try {
            Booking booking = bookingService.fetchById(bookingId);
            bookingService.delete(booking);
            return ResponseEntity.ok("Booking deleted successfully.");
        } catch (BookingNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Booking not found.");
        }
    }

    @GetMapping("/{bookingId}/license-image")
    public ResponseEntity<byte[]> getLicenseImage(@PathVariable int bookingId) {
        Optional<Booking> bookingOpt = bookingService.getBookingById(bookingId);
        if (bookingOpt.isPresent()) {
            byte[] image = bookingOpt.get().getLicenseImage();
            if (image != null && image.length > 0) {
                return ResponseEntity.ok()
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=license-image.jpg")
                        .body(image);
            } else {
                return ResponseEntity.noContent().build(); // Return 204 No Content
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
