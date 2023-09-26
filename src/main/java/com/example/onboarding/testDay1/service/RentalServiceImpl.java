package com.example.onboarding.testDay1.service;

import com.example.onboarding.testDay1.dao.RentalsDao;
import com.example.onboarding.testDay1.model.RentalsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onboarding.testDay1.dao.CarsDao;
import com.example.onboarding.testDay1.model.CarsModel;

import java.util.List;
import java.util.Optional;

@Service
public class RentalServiceImpl implements RentalService {
	@Autowired
	CarsDao carsDao;

	@Autowired
	RentalsDao rentalsDao;
	
	@Override
	public void insertCars(long id, List<CarsModel> cars) throws RuntimeException{
		RentalsModel rental = rentalsDao.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
		cars.forEach((car) -> {
			car.setRental(rental);
			carsDao.save(car);
		});
	}

	@Override
	public List<RentalsModel> getRentals() {
		return rentalsDao.findAll();
	}

	public List<CarsModel> getCars(long id) {
		return carsDao.findByRental_Id(id);
	}

	@Override
	public void updateRental(long id, RentalsModel rental) throws RuntimeException{
		RentalsModel oldRental = rentalsDao.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
		oldRental.setName(rental.getName());
		oldRental.setAddress(rental.getAddress());
		oldRental.setRating(rental.getRating());
		oldRental.setPhoneNumber(rental.getPhoneNumber());
		rentalsDao.save(oldRental);
	}

	@Override
	public void deleteCar(long id, String plateNumber) throws RuntimeException {
		RentalsModel rental = rentalsDao.findById(id).orElseThrow(() -> new RuntimeException("Rental not found"));
		CarsModel car = carsDao.findById(plateNumber).orElseThrow(() -> new RuntimeException("Car not found"));
		carsDao.delete(car);
	}
}
