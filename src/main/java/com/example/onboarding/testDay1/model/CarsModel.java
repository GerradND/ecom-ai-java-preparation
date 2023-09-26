package com.example.onboarding.testDay1.model;
import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "cars")
public class CarsModel {
	@Id
	@Column(name="plate_number")
	private String plateNumber;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "capacity")
	private int capacity;
	
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "price")
	private int price;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "rentalId", referencedColumnName = "id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private RentalsModel rental;
	
	public CarsModel() {
		
	}
	
	public CarsModel(String plateNumber, String name, int capacity, boolean status, int price) {
		this.plateNumber = plateNumber;
		this.name = name;
		this.capacity = capacity;
		this.status = status;
		this.price = price;
	}
	
	public String getPlateNumber() {
		return this.plateNumber;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	public void setCapacity(int capacity) {
		this.capacity= capacity;
	}
	
	public boolean getStatus() {
		return this.status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public void setRental(RentalsModel rental) {this.rental = rental;}
	
	@Override
	public String toString() {
		return "Cars [plateNumber=" + plateNumber + ", name=" + name + ", capacity=" + capacity + ", status=" + status + ", price=" + price + "]";
	}
}
