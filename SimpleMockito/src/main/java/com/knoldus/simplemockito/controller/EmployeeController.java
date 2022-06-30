package com.knoldus.simplemockito.controller;

import java.util.List;
import java.util.Optional;

import com.knoldus.simplemockito.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.knoldus.simplemockito.service.EmployeeService;

@RestController
public class EmployeeController{
    @Autowired
    private EmployeeService service;

    @PostMapping(value = "/save")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @GetMapping("/getEmployee")
    public List<Employee> findAllEmployee() {
        return service.getEmployee();
    }

    @GetMapping("/getEmployeeByID/{id}")
    public Optional<Employee> findEmployeeByID(@PathVariable long id) {
        return service.getEmployeebyID(id);
    }

    @DeleteMapping(value="/remove")
    public Employee deleteEmployee(@RequestBody Employee employee) {
        service.deleteEmployee(employee);
        return employee;
    }
}
