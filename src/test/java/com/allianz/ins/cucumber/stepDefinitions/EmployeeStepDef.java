package com.allianz.ins.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class EmployeeStepDef{

	String baseUrl = "http://localhost:8080";
	String apiUrl = "";
	
	@Given("get authentication url")
	public void getAuthUrl() {
	    
		apiUrl = "/authenticate";
	}
	
	@When("get the authentication token")
	public void getAuthToken() {
	    
		
	}
	
	@Given("client wants to see all employees")
	public void getAllEmp() {
	    
		apiUrl = "/getAllEmployees";
	}
	
	
}
