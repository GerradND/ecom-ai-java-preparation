package com.example.onboarding.testDay1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onboarding.testDay1.model.RentalsModel;

public interface RentalsDao extends JpaRepository<RentalsModel, Long> {
}
