package com.example.onboarding.testDay1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.onboarding.testDay1.dto.ResponseDTO;

@RestController
public class TestController {
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public ResponseDTO helloWorld() {
		ResponseDTO response = new ResponseDTO();
		
		response.setCode("200");
		response.setMessage("Hello world!");
		
		return response;
	}
}
