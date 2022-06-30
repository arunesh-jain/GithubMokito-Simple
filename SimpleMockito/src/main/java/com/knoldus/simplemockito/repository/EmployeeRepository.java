package com.knoldus.simplemockito.repository;

import com.knoldus.simplemockito.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByID(long id);
}
