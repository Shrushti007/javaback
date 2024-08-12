package com.drivewise.car.entity;

import java.util.Arrays;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="booking")
public class Booking {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bookingId;
	private Date startDate;
	private Date endDate;
	private String city;
	@Lob
	private byte[] licenseImage;
	private long licenseNo;
	@ManyToOne
	@JoinColumn(name ="userid")
	private User user;
	
	@ManyToOne
	@JoinColumn(name ="carid")
	private Cars cars;
	
	public Booking() {
		// TODO Auto-generated constructor stub
	}

	public Booking(int bookingId, Date startDate, Date endDate, String city, byte[] licenseImage, long licenseNo,
			User user, Cars cars) {
		super();
		this.bookingId = bookingId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.city = city;
		this.licenseImage = licenseImage;
		this.licenseNo = licenseNo;
		this.user = user;
		this.cars = cars;
	}

	public int getBookingId() {
		return bookingId;
	}

	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public byte[] getLicenseImage() {
		return licenseImage;
	}

	public void setLicenseImage(byte[] licenseImage) {
		this.licenseImage = licenseImage;
	}

	public long getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(long licenseNo) {
		this.licenseNo = licenseNo;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Cars getCars() {
		return cars;
	}

	public void setCars(Cars cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", startDate=" + startDate + ", endDate=" + endDate + ", city="
				+ city + ", licenseImage=" + Arrays.toString(licenseImage) + ", licenseNo=" + licenseNo + ", user="
				+ user + ", cars=" + cars + "]";
	}

	


}
