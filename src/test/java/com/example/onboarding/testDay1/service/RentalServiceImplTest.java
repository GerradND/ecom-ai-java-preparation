package com.example.onboarding.testDay1.service;

import com.example.onboarding.testDay1.dao.CarsDao;
import com.example.onboarding.testDay1.dao.RentalsDao;
import com.example.onboarding.testDay1.model.CarsModel;
import com.example.onboarding.testDay1.model.RentalsModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RentalServiceImplTest {
    @Mock
    private CarsDao carsDao;

    @Mock
    private RentalsDao rentalsDao;

    @InjectMocks
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
    void testServiceInsertCars() {
        List<CarsModel> listCars = new ArrayList<CarsModel>();
        listCars.add(carsModel);

        when(rentalsDao.findById(anyLong())).thenReturn(Optional.of(rentalsModel));
        when(carsDao.save(carsModel)).thenReturn(carsModel);

        assertDoesNotThrow(() -> rentalService.insertCars(rentalsModel.getId(), listCars));

        verify(rentalsDao).findById(rentalsModel.getId());
        verify(carsDao).save(carsModel);
    }

    @Test
    void testServiceInsertCarsRentalNotFound() {
        List<CarsModel> listCars = new ArrayList<CarsModel>();
        listCars.add(carsModel);
        String errorMessage = null;

        when(rentalsDao.findById(anyLong())).thenReturn(Optional.empty());

        try {
            rentalService.insertCars(rentalsModel.getId(), listCars);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }
        assertNotNull(errorMessage);
        assertEquals(errorMessage, "Rental not found");
    }

    @Test
    void testServiceGetRentals() {
        List<RentalsModel> listRentals = new ArrayList<RentalsModel>();
        listRentals.add(rentalsModel);
        when(rentalsDao.findAll()).thenReturn(listRentals);
        List<RentalsModel> currentRentals = rentalService.getRentals();
        Assertions.assertEquals(listRentals, currentRentals);
    }

    @Test
    void testServiceGetCars() {
        List<CarsModel> listCars = new ArrayList<CarsModel>();
        listCars.add(carsModel);
        when(carsDao.findByRental_Id(rentalsModel.getId())).thenReturn(listCars);
        List<CarsModel> currentCars = rentalService.getCars(rentalsModel.getId());
        Assertions.assertEquals(listCars, currentCars);
    }


    @Test
    void testServiceUpdateRental() {
        CarsModel car = new CarsModel("b 1234 abc", "car 1", 4, false, 10000 );

        when(rentalsDao.findById(anyLong())).thenReturn(Optional.of(rentalsModel));
        when(rentalsDao.save(rentalsModel)).thenReturn(rentalsModel);

        rentalService.updateRental(rentalsModel.getId(), rentalsModel);

        verify(rentalsDao).findById(rentalsModel.getId());
        verify(rentalsDao).save(rentalsModel);
    }

    @Test
    void testServiceUpdateRentalRentalNotFound() {
        when(rentalsDao.findById(anyLong())).thenReturn(Optional.empty());
        String errorMessage = null;
        try {
            rentalService.updateRental(rentalsModel.getId(), rentalsModel);
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertNotNull(errorMessage);
        assertEquals(errorMessage, "Rental not found");

    }

    @Test
    void testServiceDeleteCar() {
        when(rentalsDao.findById(rentalsModel.getId())).thenReturn(Optional.of(rentalsModel));
        when(carsDao.findById(carsModel.getPlateNumber())).thenReturn(Optional.of(carsModel));

        assertDoesNotThrow(() -> rentalService.deleteCar(rentalsModel.getId(), carsModel.getPlateNumber()));

        verify(rentalsDao).findById(rentalsModel.getId());
        verify(carsDao).findById(carsModel.getPlateNumber());
        verify(carsDao).delete(carsModel);
    }

    @Test
    void testServiceDeleteCarRentalNotFound() {
        when(rentalsDao.findById(anyLong())).thenReturn(Optional.empty());
        String errorMessage = null;

        try{
            rentalService.deleteCar(rentalsModel.getId(), carsModel.getPlateNumber());
        } catch (Exception e) {
            errorMessage = e.getMessage();
        }

        assertNotNull(errorMessage);
        assertEquals(errorMessage, "Rental not found");
    }

    @Test
    void testServiceDeleteCarCarNotFound() {
        when(rentalsDao.findById(rentalsModel.getId())).thenReturn(Optional.of(rentalsModel));
        when(carsDao.findById(anyString())).thenReturn(Optional.empty());
        String errorMessage = null;

        try{
            rentalService.deleteCar(rentalsModel.getId(), carsModel.getPlateNumber());
        } catch (Exception e){
            errorMessage = e.getMessage();
        }

        assertNotNull(errorMessage);
        assertEquals(errorMessage, "Car not found");
    }
}
