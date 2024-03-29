package com.allianz.ins.junit.integrationTesting;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.allianz.ins.model.AuthenticationRequest;
import com.allianz.ins.model.AuthenticationResponse;
import com.allianz.ins.model.Employee;
import com.allianz.ins.springBoot.SpringBootStarter;
import com.google.gson.Gson;



@ExtendWith(SpringExtension.class)

// It will start our SpringBoot application internally
@SpringBootTest(classes =SpringBootStarter.class, webEnvironment=WebEnvironment.DEFINED_PORT)
// To define the execution order of our test cases
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class EmployeeIntegrationTest  {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeIntegrationTest.class);
	private static String BASE_URL = "http://localhost:";
	
	// fetching port from application.properties
	@Value("${server.port}")
	private String PORT;
	
	//Defining API's url
	private final static String AUTHENTICATE = "/authenticate";
	private final static String GET_ALL_EMPLOYEES = "/getAllEmployees";
	private final static String GET_EMPLOYEE = "/getEmployee/";
	private final static String SAVE_EMPLOYEE = "/save";
	private final static String DELETE_EMPLOYEE = "/deleteEmployee/";
	private final static String UPDATE_EMPLOYEE = "/updateEmployee";

	private static String JWTToken = "";

	String URL;
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpEntity<String> entity;
	HttpHeaders headers;

	/*
	 * This is setup method, which is responsible for
	 * generating the token
	 * setting the token in header for each call 
	 */
	@BeforeEach
	public void testgetAuthenticationToken() {

		
		
		// We will generate token only once, and use in subsequent calls
		if(JWTToken == "") {
			LOGGER.debug("Getting AUTH Token");
			
			// Appending the port number in base URL. This will execute only once.
			BASE_URL = BASE_URL + PORT;

			AuthenticationRequest authenticationRequest = new AuthenticationRequest();
			authenticationRequest.setUsername("Jatin");
			authenticationRequest.setPassword("Kumar");

			ResponseEntity<AuthenticationResponse> authenticationResponse = restTemplate.postForEntity(BASE_URL + AUTHENTICATE, authenticationRequest,  AuthenticationResponse.class);

			JWTToken  = authenticationResponse.getBody().getJwtToken();
			LOGGER.debug("authenticationResponse token: "+JWTToken);
		}else {
			LOGGER.debug("ALREADY HAVE Token");
		}
		
		// Setting the token in header for all API's
		headers = new HttpHeaders();
		headers.add("Authorization", "Bearer "+JWTToken);
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		LOGGER.debug("BASE_URL: "+BASE_URL);
	}

	/*
	 * This method will fetch the employees list
	 * that we have added in data.sql
	 */
	@Test
	public void testA_ListAllEmployees() {

		// Creating url
		URL = BASE_URL + GET_ALL_EMPLOYEES;
		LOGGER.debug("URL: "+URL);

		entity = new HttpEntity<>("", headers);

		ResponseEntity<Employee[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Employee[].class);

		assertEquals(200, response.getStatusCodeValue());
		LOGGER.debug("ListAll assert success");

		// data.sql has 3 entries
		assertEquals(3, response.getBody().length);
		Arrays.stream(response.getBody()).forEach( e -> LOGGER.debug(e+"\n"));

	}

	@Test
	public void testB_GetEmployee() {

		// Adding the userName in path param
		String userName = "Tanuj";
		URL = BASE_URL + GET_EMPLOYEE + userName;
		LOGGER.debug("URL: "+URL);

		entity = new HttpEntity<>("", headers);

		ResponseEntity<Employee> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Employee.class);

		assertEquals(200, response.getStatusCodeValue());
		LOGGER.debug("GetEmployee assert success");

		assertEquals("Mahajan", response.getBody().getPassword());
	}

	@Test
	public void testC_AddEmployee() {

		URL = BASE_URL + SAVE_EMPLOYEE;
		LOGGER.debug("URL: "+URL);

		Employee employee = new Employee();
		employee.setEmployeename("Nitin");
		employee.setPassword("Thakur");
		employee.setEmail("nitgin@gmail.com");

		Gson gson = new Gson();
		String jsonRequest = gson.toJson(employee);

		// setting the employee object as string
		entity = new HttpEntity<>(jsonRequest, headers);

		ResponseEntity<Employee> response = restTemplate.exchange(URL, HttpMethod.POST, entity, Employee.class);

		assertEquals(200, response.getStatusCodeValue());
		LOGGER.debug("AddEmployee assert success");

		assertEquals("nitgin@gmail.com", response.getBody().getEmail());

	}

	@Test
	public void testD_UpdateEmployee() {

		URL = BASE_URL + UPDATE_EMPLOYEE;
		LOGGER.debug("URL: "+URL);

		Employee employee = new Employee();
		employee.setEmployeename("Nitin");
		employee.setPassword("changed passwprd");
		employee.setEmail("nitgin@gmail.com");

		Gson gson = new Gson();
		String jsonRequest = gson.toJson(employee);

		entity = new HttpEntity<>(jsonRequest, headers);

		ResponseEntity<Integer> response = restTemplate.exchange(URL, HttpMethod.POST, entity, Integer.class);

		assertEquals(200, response.getStatusCodeValue());
		LOGGER.debug("UpdateEmployee assert success");

		assertEquals(1, response.getBody().intValue());

	}

	/*
	 * This method validates the data addition/modification
	 * we did in above 2 test cases
	 */

	@Test
	public void testE_VerifyNewData() {

		URL = BASE_URL + GET_ALL_EMPLOYEES;
		LOGGER.debug("URL: "+URL);

		entity = new HttpEntity<>("", headers);

		ResponseEntity<Employee[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Employee[].class);

		assertEquals(200, response.getStatusCodeValue());
		LOGGER.debug("VerifyNewData assert success");

		
		assertEquals(4, response.getBody().length);
		Arrays.stream(response.getBody()).forEach( e -> LOGGER.debug(e+"\n"));

	}
	@Test
	public void testF_DeleteEmployee() {

		String userName = "Nitin";
		URL = BASE_URL + DELETE_EMPLOYEE + userName;
		LOGGER.debug("URL: "+URL);

		entity = new HttpEntity<>("", headers);

		ResponseEntity<Integer> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Integer.class);

		assertEquals(200, response.getStatusCodeValue());
		LOGGER.debug("DeleteEmployee assert success");

		assertEquals(1, response.getBody().intValue());
	}
}
