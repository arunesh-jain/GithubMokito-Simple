package com.knoldus.simplemockito.service;

import com.knoldus.simplemockito.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import com.knoldus.simplemockito.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;


    public Employee addEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getEmployee() {
        List<Employee> employee = repository.findAll();
        System.out.println("Executed data : " + employee);
        return employee;
    }

    public List<Employee> getEmployeebyID(long id) {
        return repository.findByID(id);
    }

    public void deleteEmployee(Employee employee) {
        repository.delete(employee);
    }
}
