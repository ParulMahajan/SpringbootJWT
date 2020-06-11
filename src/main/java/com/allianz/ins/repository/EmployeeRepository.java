package com.allianz.ins.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.allianz.ins.model.Employee;


@Repository
public interface EmployeeRepository extends CrudRepository<Employee, String>{

	@Modifying
	@Query("update Employee e set e.employeename = ?1, e.password = ?2 , e.email = ?3, e.mobile = ?4 where e.employeename = ?1")
	int updateEmployee(String employeename, String password, String email, String mobile);
	
	@Modifying
	@Query("DELETE from Employee e  where e.employeename = ?1")
	int deleteEmployee(String employeename);
}
