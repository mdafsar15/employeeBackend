package com.example.employeepayrollspring.service;

import com.example.employeepayrollspring.dto.EmployeeDTO;
import com.example.employeepayrollspring.entity.Employee;
import com.example.employeepayrollspring.exception.CustomException;
import com.example.employeepayrollspring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service Class with IEmployeeService implementations.
 */
@Service
public class EmployeeService implements IEmployeeService {
	/**
	 * Auto Wired EmployeeRepository - employeeRepository.
	 */
	@Autowired
	EmployeeRepository employeeRepository;

	/**
	 * Method to add employee to database.
	 *
	 * @param employee - employee object containing all required fields.
	 * @return - implementation of save method from EmployeeRepository by passing
	 *         employee object.
	 */
	public Employee addEmployee(Employee employee) {
		employee.setStartDate(LocalDate.now());
		return employeeRepository.save(employee);
	}

	/**
	 * Method to get all employees from database.
	 *
	 * @return - implementation of findAll method from EmployeeRepository.
	 */
	public List<Employee> getAllEmployees() {
		if (employeeRepository.findAll().isEmpty()) {
			throw new CustomException("No employee in database.");
		} else
			return employeeRepository.findAll();
	}

	/**
	 * Method to get employees using Id from database.
	 *
	 * @param id - Id of employee required to get from database.
	 * @return - implementation of findById method from EmployeeRepository.
	 */
	public Optional<Employee> getById(int id) {
		if (employeeRepository.findById(id).isPresent()) {
			return employeeRepository.findById(id);
		} else
			throw new CustomException("No employee matches withemployee");
	}

	/**
	 * Method to delete employee using Id from Database.
	 *
	 * @param id - Id of employee required to delete from database.
	 * @return - implementation of deleteById method from EmployeeRepository.
	 */
	public String deleteById(int id) {
		if (employeeRepository.findById(id).isPresent()) {
			employeeRepository.deleteById(id);
			return "Employee with ID: " + id + " is Deleted Successfully!!";
		} else
			throw new CustomException("No employee matches with the given ID");
	}

	/**
	 * Method to edit existing employee in database. If there is a match by passed
	 * id, we create a new employee object using the same id - employee1. Passing
	 * the object to the save method.
	 *
	 * @param employeeDTO - employee object with new field values.
	 * @param id          - Id of employee required to delete from database.
	 * @return - implementation of save method from EmployeeRepository.
	 */
	public Employee editEmployee(EmployeeDTO employeeDTO, int id) {
		if (employeeRepository.findById(id).isPresent()) {
			Employee employee1 = new Employee(id, employeeDTO);
			return employeeRepository.save(employee1);

		} else
			throw new CustomException("No employee matches with the given ID");
	}
}
