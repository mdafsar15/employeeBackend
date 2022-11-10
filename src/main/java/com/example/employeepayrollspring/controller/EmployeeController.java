package com.example.employeepayrollspring.controller;

import com.example.employeepayrollspring.dto.EmployeeDTO;
import com.example.employeepayrollspring.dto.ResponseDTO;
import com.example.employeepayrollspring.entity.Employee;
import com.example.employeepayrollspring.service.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Controller Class - EmployeeController.
 * Base URL: localhost:8080/employee
 */
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RequestMapping("/employee")
@RestController
public class EmployeeController {
    /**
     * Dependency injection using Autowired - iEmployeeService.
     */
    @Autowired
    IEmployeeService iEmployeeService;

    /**
     * PostMapping method to create employee in database.
     * URL - /post
     * Creating Employee object using EmployeeDTO object taken from Request Body -
     * employee1
     * Creating responseDTO object with message and implementation of add employee
     * method on employee1
     *
     * @param employeeDTO - EmployeeDTO object to be provided in RequestBody.
     * @return - ResponseEntity consisting response DTO and Status Code.
     */
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> createEmployees(@Valid @RequestBody EmployeeDTO employeeDTO) {
        Employee employee1 = new Employee(employeeDTO);
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", iEmployeeService.addEmployee(employee1));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    /**
     * GetMapping method to get all existing employees in database.
     * URL - /get-all
     * Creating responseDTO object with message and implementation of
     * getAllEmployees method.
     *
     * @return - ResponseEntity consisting response DTO and Status Code.
     */
    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getAllEmployees() {
        ResponseDTO responseDTO = new ResponseDTO("Employee List Received Successfully",
                iEmployeeService.getAllEmployees());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * GetMapping method to get specific employee by id.
     * URL - /get/id.
     * Creating responseDTO object with message and implementation of getById
     * using id.
     *
     * @param id - Id of existing employee that needs to be updated in path
     *           variable.
     * @return - ResponseEntity consisting response DTO and Status Code.
     */
    @GetMapping("get/{id}")
    public ResponseEntity<ResponseDTO> getByID(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Employee Received Successfully",
                iEmployeeService.getById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * DeleteMapping method to delete specific employee by id.
     * Creating responseDTO object with message and implementation of deleteById
     * using id.
     *
     * @param id - Id of existing employee that needs to be deleted in path
     *           variable.
     * @return - ResponseEntity consisting response DTO and Status Code.
     */
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Employee Deleted Successfully",
                iEmployeeService.deleteById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /**
     * PutMapping method to edit existing employee by id.
     * Creating responseDTO object with message and implementation of editEmployee
     * using id and employeeDTO.
     *
     * @param employeeDTO - EmployeeDTO object with new field values that need to
     *                    be placed.
     * @param id          - Id of existing employee that needs to be updated in path
     *                    variable.
     * @return - ResponseEntity consisting response DTO and Status Code.
     */
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseDTO> editEmployee(@Valid @RequestBody EmployeeDTO employeeDTO, @PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Employee Updated Successfully",
                iEmployeeService.editEmployee(employeeDTO, id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
