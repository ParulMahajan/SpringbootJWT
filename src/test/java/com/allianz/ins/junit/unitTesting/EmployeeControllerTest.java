package com.allianz.ins.junit.unitTesting;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.allianz.ins.controller.EmployeeController;
import com.allianz.ins.model.AuthenticationRequest;
import com.allianz.ins.model.AuthenticationResponse;
import com.allianz.ins.model.Employee;
import com.allianz.ins.service.EmployeeService;
import com.allianz.ins.util.JWTUtil;

// for direct access, without class name
import static org.mockito.Mockito.*;

public class EmployeeControllerTest {

	@InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;
    
    @Mock
    private JWTUtil JWTUtil;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testcreateAuthenticationToken() throws Exception {
    	AuthenticationRequest authenticationRequest = new AuthenticationRequest();
    	authenticationRequest.setUsername("parul");
    	authenticationRequest.setPassword("mahajan");
    	
    	Employee employee = new Employee();
    	employee.setEmployeename("parul");
    	employee.setPassword("mahajan");
    	
    	// We have imported Mockito class statically, so no need to access using class name
    	when(employeeService.getEmployee(anyString())).thenReturn(employee);
        
    	when(JWTUtil.generateToken(anyString())).thenReturn("randomToken");

        @SuppressWarnings("unchecked")
		ResponseEntity<AuthenticationResponse> response = (ResponseEntity<AuthenticationResponse>) employeeController.createAuthenticationToken(authenticationRequest);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("randomToken", response.getBody().getJwtToken());
    }
    
    @Test
    public void testGetAllEmployees() throws Exception {
    	
    	List<Employee> employees = new ArrayList<Employee>();
    	
    	Employee employee1 = new Employee();
    	employee1.setEmployeename("parul");
    	employee1.setPassword("mahajan");
    	
    	Employee employee2 = new Employee();
    	employee2.setEmployeename("tanuj");
    	employee2.setPassword("jain");
    	
    	employees.add(employee1);
    	employees.add(employee2);
    	
    	when(employeeService.getAllEmployees()).thenReturn(employees);
        
        @SuppressWarnings("unchecked")
		ResponseEntity<List<Employee>> response = (ResponseEntity<List<Employee>>) employeeController.getAllEmployees();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("parul", response.getBody().get(0).getEmployeename());
        assertEquals("jain", response.getBody().get(1).getPassword());
    }
     
    
    @Test
    public void testsaveEmployee() throws Exception {
    	
    	Employee employee = new Employee();
    	employee.setEmployeename("parul");
    	employee.setPassword("mahajan");
    	
    	when(employeeService.addEmployee(any())).thenReturn(employee);
        
        @SuppressWarnings("unchecked")
		ResponseEntity<Employee> response = (ResponseEntity<Employee>) employeeController.saveEmployee(employee);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("parul", response.getBody().getEmployeename());
        assertEquals("mahajan", response.getBody().getPassword());
    }
    
    
    @Test
    public void testGetEmployee() throws Exception {
    	
    	Employee employee = new Employee();
    	employee.setEmployeename("Jatin");
    	employee.setPassword("kumar");
    	
    	when(employeeService.getEmployee(anyString())).thenReturn(employee);
        
        @SuppressWarnings("unchecked")
		ResponseEntity<Employee> response = (ResponseEntity<Employee>) employeeController.getEmployee("jatin");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Jatin", response.getBody().getEmployeename());
        assertEquals("kumar", response.getBody().getPassword());
    }
    
    @Test
    public void testDeleteEmployee() throws Exception {
    	   	
    	when(employeeService.deleteEmployee(anyString())).thenReturn(1);
        
        @SuppressWarnings("unchecked")
		ResponseEntity<Integer> response = (ResponseEntity<Integer>) employeeController.deleteEmployee("jatin");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().intValue());
      
    }
    
    @Test
    public void testUpdateEmployee() throws Exception {
    	   	
    	Employee employee = new Employee();
    	employee.setEmployeename("Nitin");
    	employee.setPassword("Thakur");
    	
    	when(employeeService.updateEmployee(any())).thenReturn(1);
        
        @SuppressWarnings("unchecked")
		ResponseEntity<Integer> response = (ResponseEntity<Integer>) employeeController.updateEmployee(employee);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().intValue());
      
    }
}
