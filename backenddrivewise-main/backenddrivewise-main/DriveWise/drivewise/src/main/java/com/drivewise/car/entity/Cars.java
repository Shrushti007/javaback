package com.drivewise.car.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="car")
public class Cars {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int carId;
	private String brandName;
	private String model;
	private double pricePerDay;
	private String color;
	private String licensePlate;
	private String status;
	private String carType;
	@ManyToOne
	@JoinColumn(name ="userid")
	private User User;
	
	 @OneToMany(mappedBy = "cars",cascade = CascadeType.ALL)
	 @JsonIgnore
	private List<Booking> bookingList = new ArrayList<Booking>();
	 
	public Cars() {
		// TODO Auto-generated constructor stub
	}

	public Cars(int carId, String brandName, String model, double pricePerDay, String color, String licensePlate,
			String status, String carType, com.drivewise.car.entity.User user) {
		super();
		this.carId = carId;
		this.brandName = brandName;
		this.model = model;
		this.pricePerDay = pricePerDay;
		this.color = color;
		this.licensePlate = licensePlate;
		this.status = status;
		this.carType = carType;
		User = user;
	}

	public int getCarId() {
		return carId;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public String getbrandName() {
		return brandName;
	}

	public void setbrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	@Override
	public String toString() {
		return "Cars [carId=" + carId + ", brandName=" + brandName + ", model=" + model + ", pricePerDay=" + pricePerDay
				+ ", color=" + color + ", licensePlate=" + licensePlate + ", status=" + status + ", carType=" + carType
				+ ", User=" + User + "]";
	}
	
	
	
	
	
	
	
}
