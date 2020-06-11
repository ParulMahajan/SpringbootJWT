package com.allianz.ins.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allianz.ins.model.Employee;
import com.allianz.ins.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	public void addEmployee(Employee employee) {
		
		Employee saved = employeeRepository.save(employee);
		System.out.println("saved emp:"+saved);
	}

	public Employee getEmployee(String employeeName){
		System.out.println("IN: getEmployee service");
		
		return employeeRepository.findById(employeeName).get();
	}

	public List<Employee> getAllEmployees(){
		
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();
		
		System.out.println("employees List:"+employees);
		return employees;
	}
	
	@Transactional
	public int deleteEmployee(String employeeName) {
		int status = employeeRepository.deleteEmployee(employeeName);
		System.out.println("record deleted: "+status);
		
		return status;
	}
	
	@Transactional
	public int updateEmployee(Employee employee) {
		
		int status = employeeRepository.updateEmployee(employee.getEmployeename(), employee.getPassword(), employee.getEmail(), employee.getMobile());
		System.out.println("record deleted: "+status);
		
		return status;
	}

}
