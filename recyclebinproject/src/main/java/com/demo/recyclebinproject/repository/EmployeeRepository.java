package com.demo.recyclebinproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.recyclebinproject.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
