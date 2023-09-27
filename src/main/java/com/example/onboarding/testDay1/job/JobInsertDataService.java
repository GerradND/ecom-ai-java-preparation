package com.example.onboarding.testDay1.job;

import com.example.onboarding.testDay1.model.CarsModel;
import com.example.onboarding.testDay1.service.RentalService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class JobInsertDataService implements Job {
    private static final Logger logger = LoggerFactory.getLogger(JobInsertDataService.class);
    @Autowired
    RentalService rentalService;
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            logger.info("Executing job with key {}", context.getJobDetail().getKey());

            String plateNumber = UUID.randomUUID().toString();
            String name = "test car name";
            int capacity = 2;
            boolean status = false;
            int price = 10000;

            List<CarsModel> listCar = new ArrayList<>();
            listCar.add(new CarsModel(plateNumber, name, capacity, status, price));

            rentalService.insertCars(123456789, listCar);
        } catch (Exception e) {
            logger.error("Failed to insert", e);
            throw e;
        }
    }
}
