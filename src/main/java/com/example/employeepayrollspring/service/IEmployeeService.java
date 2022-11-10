package com.example.employeepayrollspring.service;

import com.example.employeepayrollspring.dto.EmployeeDTO;
import com.example.employeepayrollspring.entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Interface containing methods from service class - IEmployeeService
 */
public interface IEmployeeService {
    Employee addEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Optional<Employee> getById(int id);

    String deleteById(int id);

    Employee editEmployee(EmployeeDTO employeeDtO, int id);
}
