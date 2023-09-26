package com.example.onboarding.testDay1.controller;

import com.example.onboarding.testDay1.dto.ResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class TestControllerTest {
    @InjectMocks
    TestController testController;
    @BeforeEach
    public void setUp() {

    }

    @Test
    void testControllerHelloWorld() {
        ResponseDTO responseDTO = testController.helloWorld();

        assertNotNull(responseDTO);
        assertEquals(responseDTO.getCode(), "200");
        assertEquals(responseDTO.getMessage(), "Hello world!");
    }
}
