package com.allianz.ins.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

@RestController
public class EmployeeController {

	@Autowired
	private AuthenticationManager authenticationManager;


	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	private JWTUtil JWTUtil;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

		System.out.println("Inside authenticate controller");
		//Validate User credentials
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		//get User credentials
		final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		//generate JWT token using User credentials
		final String token = JWTUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new AuthenticationResponse(token));
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) throws Exception {

		System.out.println("IN: saveEmployee: ");
		
		employeeService.addEmployee(employee);
		return ResponseEntity.ok().build();
	}
	
	@RequestMapping(value = "/getAllEmployees", method = RequestMethod.GET)
	public ResponseEntity<?> getAllEmployees() throws Exception {

		System.out.println("IN: getAllEmployees: ");
		
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
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
}
