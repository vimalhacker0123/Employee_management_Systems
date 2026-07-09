package com.example.task.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.task.entity.Employee;
import com.example.task.repository.EmployeeRepository;


@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    private EmployeeRepository repository;



    @Override
    public Employee saveEmployee(Employee employee) {

        return repository.save(employee);

    }



    @Override
    public List<Employee> getAllEmployees() {

        return repository.findAll();

    }



    @Override
    public Employee getEmployeeById(Long id) {

        return repository.findById(id)
                .orElseThrow();

    }



    @Override
    public Employee updateEmployee(Long id, Employee employee) {


        Employee emp = getEmployeeById(id);


        emp.setName(employee.getName());

        emp.setEmail(employee.getEmail());

        emp.setPhone(employee.getPhone());

        emp.setDepartment(employee.getDepartment());

        emp.setDesignation(employee.getDesignation());

        emp.setSalary(employee.getSalary());

        emp.setJoiningDate(employee.getJoiningDate());

        emp.setStatus(employee.getStatus());


        return repository.save(emp);

    }




    @Override
    public void deleteEmployee(Long id) {

        repository.deleteById(id);

    }





    @Override
    public List<Employee> searchByName(String name) {

        return repository.findByNameContainingIgnoreCase(name);

    }





    @Override
    public List<Employee> searchByDepartment(String department) {

        return repository.findByDepartmentContainingIgnoreCase(department);

    }





    @Override
    public Page<Employee> findPaginated(
            int pageNo,
            int pageSize,
            String sortField,
            String sortDir) {


        Sort sort = sortDir.equalsIgnoreCase(
                Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();



        Pageable pageable =
                PageRequest.of(pageNo - 1, pageSize, sort);



        return repository.findAll(pageable);

    }





    // Dashboard Total Employee Count

    @Override
    public long getTotalEmployees() {

        return repository.count();

    }





    // Dashboard Active Employee Count

    @Override
    public long getActiveEmployees() {

        return repository.countByStatus("Active");

    }


}