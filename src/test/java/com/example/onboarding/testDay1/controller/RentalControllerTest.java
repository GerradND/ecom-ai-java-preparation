package com.example.onboarding.testDay1.controller;

import com.example.onboarding.testDay1.dto.ResponseDTO;
import com.example.onboarding.testDay1.model.CarsModel;
import com.example.onboarding.testDay1.model.RentalsModel;
import com.example.onboarding.testDay1.service.RentalServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
public class RentalControllerTest {
    @InjectMocks
    private RentalsController rentalsController;

    @Mock
    private RentalServiceImpl rentalService;

    private RentalsModel rentalsModel;
    private CarsModel carsModel;
    @BeforeEach
    public void setUp() {
        rentalsModel = new RentalsModel("test nama", "test address", 4, "test phone number");
        carsModel = new CarsModel("test plate number", "test nanma", 2, true, 100);
        carsModel.setRental(rentalsModel);
    }

    @Test
    void testControllerGetRentals() {
        List<RentalsModel> listRentals = new ArrayList<RentalsModel>();
        listRentals.add(rentalsModel);

        when(rentalService.getRentals()).thenReturn(listRentals);
        ResponseDTO responseDTO = rentalsController.getRentals();

        verify(rentalService).getRentals();
        assertNotNull(responseDTO);
        assertEquals(responseDTO.getData(), listRentals);
        assertEquals(responseDTO.getCode(), "200");
        assertEquals(responseDTO.getMessage(), "Success");
    }

    @Test
    void testControllerGetCars() {
        List<CarsModel> listCars = new ArrayList<CarsModel>();
        listCars.add(carsModel);

        when(rentalService.getCars(anyLong())).thenReturn(listCars);
        ResponseDTO responseDTO = rentalsController.getCars(rentalsModel.getId());

        verify(rentalService).getCars(rentalsModel.getId());
        assertNotNull(responseDTO);
        assertEquals(responseDTO.getData(), listCars);
        assertEquals(responseDTO.getCode(), "200");
        assertEquals(responseDTO.getMessage(), "Success");
    }

    @Test
    void testControllerInsertCar() {
        List<CarsModel> listCars = new ArrayList<CarsModel>();
        listCars.add(carsModel);

        doNothing().when(rentalService).insertCars(anyLong(), anyList());
        ResponseDTO responseDTO = rentalsController.insertCar(rentalsModel.getId(), listCars);

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "200");
        assertEquals(responseDTO.getMessage(), "Successfully insert car");
    }

    @Test
    void testControllerInsertCarThrowRentalNotFound() {
        List<CarsModel> listCars = new ArrayList<CarsModel>();
        listCars.add(carsModel);

        doThrow(new RuntimeException("Rental not found")).when(rentalService).insertCars(anyLong(), anyList());

        ResponseDTO responseDTO = rentalsController.insertCar(rentalsModel.getId(), listCars);

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "500");
        assertEquals(responseDTO.getMessage(), "Rental not found");
    }

    @Test
    void testControllerUpdateRental() {
        doNothing().when(rentalService).updateRental(anyLong(), any(RentalsModel.class));

        ResponseDTO responseDTO = rentalsController.updateRental(rentalsModel.getId(), rentalsModel);

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "200");
        assertEquals(responseDTO.getMessage(), "Successfully update rental");
    }

    @Test
    void testControllerUpdateRentalThrowRentalNotFound() {
        doThrow(new RuntimeException("Rental not found")).when(rentalService).updateRental(anyLong(), any(rentalsModel.getClass()));

        ResponseDTO responseDTO = rentalsController.updateRental(rentalsModel.getId(), rentalsModel);
        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "500");
        assertEquals(responseDTO.getMessage(), "Rental not found");
    }

    @Test
    void testControllerDeleteCar() {
        doNothing().when(rentalService).deleteCar(anyLong(), anyString());
        ResponseDTO responseDTO = rentalsController.deleteCar(rentalsModel.getId(), carsModel.getPlateNumber());

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "200");
        assertEquals(responseDTO.getMessage(), "Successfully delete car");
    }

    @Test
    void testControllerDeleteCarThrowRentalNotFound() {
        doThrow(new RuntimeException("Rental not found")).when(rentalService).deleteCar(anyLong(), anyString());

        ResponseDTO responseDTO = rentalsController.deleteCar(rentalsModel.getId(), carsModel.getPlateNumber());

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "500");
        assertEquals(responseDTO.getMessage(), "Rental not found");
    }

}
