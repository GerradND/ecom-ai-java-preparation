package com.example.onboarding.testDay1.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.onboarding.testDay1.dao.CarsDao;
import com.example.onboarding.testDay1.dao.RentalsDao;
import com.example.onboarding.testDay1.dto.ResponseDTO;
import com.example.onboarding.testDay1.model.CarsModel;
import com.example.onboarding.testDay1.model.RentalsModel;
import com.example.onboarding.testDay1.service.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalsController {

	@Autowired
	RentalService rentalService;

	@GetMapping("/")
	public ResponseDTO getRentals() {
		ResponseDTO response = new ResponseDTO();
		List<RentalsModel> result = rentalService.getRentals();
		response.setCode("200");
		response.setMessage("Success");
		response.setData(result);
		return response;
	}

	@GetMapping("/{id}/cars")
	public ResponseDTO getCars(@PathVariable("id") long id) {
		ResponseDTO response = new ResponseDTO();
		List<CarsModel> result =  rentalService.getCars(id);
		response.setCode("200");
		response.setMessage("Success");
		response.setData(result);
		return response;
	}

	@PostMapping("/{id}/cars")
	public ResponseDTO insertCar(@PathVariable("id") long id, @RequestBody List<CarsModel> request) {
		ResponseDTO response = new ResponseDTO();
		try {
			rentalService.insertCars(id, request);
			response.setCode("200");
			response.setMessage("Successfully insert car");
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage(e.getMessage());
			return response;
		}

		return response;
	}

	@PutMapping("/{id}")
	public ResponseDTO updateRental(@PathVariable("id") long id, @RequestBody RentalsModel request) {
		ResponseDTO response = new ResponseDTO();
		try {
			rentalService.updateRental(id, request);
			response.setCode("200");
			response.setMessage("Successfully update rental");
			return response;
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setCode("500");
			return response;
		}

	}

	@DeleteMapping("{id}/cars/{plateNumber}")
	public ResponseDTO deleteCar(@PathVariable("id") long id, @PathVariable("plateNumber") String plateNumber) {
		ResponseDTO response = new ResponseDTO();
		try {
			rentalService.deleteCar(id, plateNumber);
			response.setCode("200");
			response.setMessage("Successfully delete car");
			return response;
		} catch (Exception e) {
			response.setCode("500");
			response.setMessage(e.getMessage());
			return response;
		}
	}
}
