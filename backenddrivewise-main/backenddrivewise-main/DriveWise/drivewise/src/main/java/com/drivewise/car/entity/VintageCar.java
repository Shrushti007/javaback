package com.drivewise.car.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="vintagecar")
public class VintageCar {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int vintageCarId;
	private String make; //name,brand
	private String model;
	private String year;
	private String bodyStyle;
	private String color;
	private double price;
	private String vintageCarType;
	
	@ManyToOne
	@JoinColumn(name ="userid")
	private User user;
	
	public VintageCar() {
		// TODO Auto-generated constructor stub
	}

	public VintageCar(int vintageCarId, String make, String model, String year, String bodyStyle, String color,
			double price, String vintageCarType, User user) {
		super();
		this.vintageCarId = vintageCarId;
		this.make = make;
		this.model = model;
		this.year = year;
		this.bodyStyle = bodyStyle;
		this.color = color;
		this.price = price;
		this.vintageCarType = vintageCarType;
		this.user = user;
	}

	public int getVintageCarId() {
		return vintageCarId;
	}

	public void setVintageCarId(int vintageCarId) {
		this.vintageCarId = vintageCarId;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(String bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getVintageCarType() {
		return vintageCarType;
	}

	public void setVintageCarType(String vintageCarType) {
		this.vintageCarType = vintageCarType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "VintageCar [vintageCarId=" + vintageCarId + ", make=" + make + ", model=" + model + ", year=" + year
				+ ", bodyStyle=" + bodyStyle + ", color=" + color + ", price=" + price + ", vintageCarType="
				+ vintageCarType + ", user=" + user + "]";
	}
	
}
