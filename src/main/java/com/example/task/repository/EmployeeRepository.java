package com.example.task.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.task.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {


    List<Employee> findByNameContainingIgnoreCase(String name);


    List<Employee> findByDepartmentContainingIgnoreCase(String department);


    long countByStatus(String status);

}