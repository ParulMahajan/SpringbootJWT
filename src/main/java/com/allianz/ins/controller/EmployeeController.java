package com.allianz.ins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.ins.model.AuthenticationRequest;
import com.allianz.ins.model.AuthenticationResponse;
import com.allianz.ins.model.Employee;
import com.allianz.ins.service.EmployeeService;
import com.allianz.ins.service.MyUserDetailsService;
import com.allianz.ins.util.JWTUtil;

import antlr.StringUtils;

@RestController
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private JWTUtil JWTUtil;

	/*
	 * This method is used to first do the authentication and
	 * then generate a jwt token and return in response
	 */
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		System.out.println("Inside authenticate controller");
		String token="";

		if(authenticationRequest != null && authenticationRequest.getUsername() != null) {

			//get User credentials
			Employee employee = employeeService.getEmployee(authenticationRequest.getUsername());

			//Validate User credentials
			if(employee != null && 
					authenticationRequest.getUsername().equals(employee.getEmployeename())&&
					authenticationRequest.getPassword().equals(employee.getPassword()))	{

				//generate JWT token using EmployeeName
				token = JWTUtil.generateToken(employee.getEmployeename());
			}
		}

		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	

	@RequestMapping(value = "/save", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, 
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws Exception {

		System.out.println("IN: saveEmployee");
		
		Employee savedEmployee = employeeService.addEmployee(employee);
		return ResponseEntity.ok(savedEmployee);
	}
	
	
	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployees() throws Exception {

		System.out.println("IN: getAllEmployees");
		
		List<Employee> employees = employeeService.getAllEmployees();
		return ResponseEntity.ok(employees);
	}

	@RequestMapping(value = "/getEmployee/{employeeName}", method = RequestMethod.GET)
	public ResponseEntity<?> getEmployee(@PathVariable String employeeName) throws Exception {

		System.out.println("IN: getEmployee controller: "+employeeName);

		Employee employee = employeeService.getEmployee(employeeName);

		System.out.println("employee: "+employee);
		return ResponseEntity.ok(employee);
	}
	
	@RequestMapping(value = "/deleteEmployee/{employeeName}", method = RequestMethod.GET)
	public ResponseEntity<?> deleteEmployee(@PathVariable String employeeName) throws Exception {

		System.out.println("IN: deleteEmployee: ");
		int status = employeeService.deleteEmployee(employeeName);
		return ResponseEntity.ok(status);
	}
	
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) throws Exception {
 
		System.out.println("IN: updateEmployee: ");
		int status = employeeService.updateEmployee(employee);
		return ResponseEntity.ok(status);
	}
	
	
}
