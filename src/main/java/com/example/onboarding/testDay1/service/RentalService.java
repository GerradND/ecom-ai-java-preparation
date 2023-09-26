package com.example.onboarding.testDay1.service;

import com.example.onboarding.testDay1.model.CarsModel;
import com.example.onboarding.testDay1.model.RentalsModel;

import java.util.List;

public interface RentalService {
	public void insertCars(long id, List<CarsModel> cars);
	public List<RentalsModel> getRentals();
	public List<CarsModel> getCars(long id);
	public void updateRental(long id, RentalsModel rental);
	public void deleteCar(long id, String plateNumber);
}
