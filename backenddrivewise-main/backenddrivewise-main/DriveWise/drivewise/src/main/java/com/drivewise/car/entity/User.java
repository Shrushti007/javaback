package com.drivewise.car.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="user", uniqueConstraints={@UniqueConstraint(columnNames = {"email"})})

public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String firstName;
	private String lastName;
	private String phoneNo;
    @Email
	private String email;
	private String Address;
	private String userName;
	@Size(min=5,max=15)
    private String password; //unencrytped
   @Column(name="role", length=20)
    private String role;
   @OneToMany(mappedBy = "User",cascade = CascadeType.ALL)
   @JsonIgnore
   private List<Cars> carsList = new ArrayList<Cars>();
   
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   @JsonIgnore
   private List<VintageCar> vintageCars=new ArrayList<VintageCar>();
   
   @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
   @JsonIgnore
   private List<Booking> bookingList = new ArrayList<Booking>();
	
   
   public User() {
	// TODO Auto-generated constructor stub
}

public User(int id, String firstName, String lastName, String phoneNo, @Email String email, String address,
		String userName, @Size(min = 5, max = 15) String password, String role) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.phoneNo = phoneNo;
	this.email = email;
	Address = address;
	this.userName = userName;
	this.password = password;
	this.role = role;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPhoneNo() {
	return phoneNo;
}

public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getAddress() {
	return Address;
}

public void setAddress(String address) {
	Address = address;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getRole() {
	return role;
}

public void setRole(String role) {
	this.role = role;
}

@Override
public String toString() {
	return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNo=" + phoneNo
			+ ", email=" + email + ", Address=" + Address + ", userName=" + userName + ", password=" + password
			+ ", role=" + role + "]";
}
   

}
