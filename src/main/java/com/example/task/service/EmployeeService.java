package com.example.task.service;


import java.util.List;

import org.springframework.data.domain.Page;

import com.example.task.entity.Employee;


public interface EmployeeService {


    Employee saveEmployee(Employee employee);


    List<Employee> getAllEmployees();


    Employee getEmployeeById(Long id);


    Employee updateEmployee(Long id, Employee employee);


    void deleteEmployee(Long id);


    List<Employee> searchByName(String name);


    List<Employee> searchByDepartment(String department);


    Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);


    long getTotalEmployees();


    long getActiveEmployees();


}