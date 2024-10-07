package com.demo.recyclebinproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.recyclebinproject.model.Employee;
import com.demo.recyclebinproject.model.RecycleBin;
import com.demo.recyclebinproject.repository.EmployeeRepository;
import com.demo.recyclebinproject.repository.RecycleBinRepository;

@Service
public class EmployeeService {
	
	
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private RecycleBinRepository recycleBinRepository;
	
	//add employee
	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get all employee details
	public List<Employee> getAllEmployee(){
		return employeeRepository.findAll();
	}
	
	//get employee by EmpId
	public Optional<Employee> getEmployeeById(Long Id){
		return employeeRepository.findById(Id);
	}
	
    //Delete employee and move it to Recycle Bin
	public void deleteAndMoveToRecycleBin(long Id) {
		Optional<Employee> employeeOptional = employeeRepository.findById(Id);
		
		if(employeeOptional.isPresent()) {
			Employee employee = employeeOptional.get();
			
			 // Move employee to recycle bin
			RecycleBin recycleBin = new RecycleBin();
			recycleBin.setName(employee.getName());
			recycleBin.setDesignation(employee.getDesignation());
			recycleBin.setSalary(employee.getSalary());

            // Save the employee data in the recycle bin
		recycleBinRepository.save(recycleBin);

        // Delete employee from the Employee table
		 employeeRepository.delete(employee);

         System.out.println("Employee moved to recycle bin and deleted from employee table.");
     } else {
         System.out.println("Employee with ID " + Id + " not found.");
     }
		
	}
}
