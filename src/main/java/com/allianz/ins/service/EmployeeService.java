package com.allianz.ins.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.allianz.ins.controller.EmployeeController;
import com.allianz.ins.model.Employee;
import com.allianz.ins.repository.EmployeeRepository;

@Service
public class EmployeeService {

	private static final Logger LOGGER = LogManager.getLogger(EmployeeService.class);
	
	@Value("${kafka.topic}")
	private String topicName ;
//	private static final String topicName = "myKafka";
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	 
	private void sendMessage(String msg) {
	    kafkaTemplate.send(topicName, msg);
	}

	public Employee addEmployee(Employee employee) {

		sendMessage("Kafka --addEmployee");
		Employee saved = employeeRepository.save(employee);
		LOGGER.debug("saved emp:"+saved);
		
		return saved;
	}

	public Employee getEmployee(String employeeName){

		sendMessage("Kafka --getEmployee");
		return employeeRepository.findById(employeeName).get(); 
	}

	public List<Employee> getAllEmployees(){

		sendMessage("Kafka --getAllEmployees");
		List<Employee> employees = (List<Employee>) employeeRepository.findAll();

		LOGGER.debug("employees List:"+employees);
		return employees;
	}

	@Transactional
	public int deleteEmployee(String employeeName) {
		
		sendMessage("Kafka --deleteEmployee");
		
		int status = employeeRepository.deleteEmployee(employeeName);
		LOGGER.debug("record deleted: "+status);

		return status;
	}

	@Transactional
	public int updateEmployee(Employee employee) {

		sendMessage("Kafka --updateEmployee");
		
		int status = employeeRepository.updateEmployee(employee.getEmployeename(), employee.getPassword(), employee.getEmail(), employee.getMobile());
		LOGGER.debug("record UPDATED: "+status);

		return status;
	}

}
