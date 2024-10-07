package com.demo.recyclebinproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.recyclebinproject.model.Employee;
import com.demo.recyclebinproject.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	//add a new employee
	@PostMapping("/add")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		Employee savedEmployee = employeeService.addEmployee(employee);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}
	
	//get all employees
	@GetMapping("/all")
	public ResponseEntity<List<Employee>> getAllEmp(){
		List<Employee> employees = employeeService.getAllEmployee();
		return new ResponseEntity<>(employees,HttpStatus.OK);
	}
	
	//Get employee by ID
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
		Optional<Employee> employee = employeeService.getEmployeeById(id);
		return employee.map(value -> new ResponseEntity<>(value,HttpStatus.OK))
				.orElseGet(()-> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	// Delete an employee and move it to recycle bin
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteAndMoveToRecycleBin(id);
        return ResponseEntity.ok("Employee moved to recycle bin and deleted from active records");
	
	}
}
