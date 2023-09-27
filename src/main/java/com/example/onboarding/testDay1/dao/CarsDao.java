package com.example.onboarding.testDay1.dao;

import java.util.List;

import com.example.onboarding.testDay1.model.RentalsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onboarding.testDay1.model.CarsModel;

public interface CarsDao extends JpaRepository<CarsModel, String> {
	List<CarsModel> findByRental_Id(long id);
}
