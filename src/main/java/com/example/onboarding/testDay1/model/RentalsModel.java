package com.example.onboarding.testDay1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rentals")
public class RentalsModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "phone_number")
	private String phoneNumber;

	@JsonIgnore
	@OneToMany(mappedBy = "rental")
	private List<CarsModel> cars;
	
	public RentalsModel() {
		
	}
	
	public RentalsModel(String name, String address, float rating, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.rating = rating;
		this.phoneNumber = phoneNumber;
	}
	
	public long getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public float getRating() {
		return this.rating;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Override
	public String toString() {
		return "Rentals [id=" + id + ", name=" + name + ", address=" + address + ", rating=" + rating + ", phoneNumber=" + phoneNumber + "]";
	}
	
}
