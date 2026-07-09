package com.example.task.entity;


import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name="employee")
public class Employee {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;



    @NotBlank(message="Name is required")
    private String name;



    @Email(message="Invalid Email")
    @Column(unique = true)
    private String email;



    @NotBlank(message="Phone number is required")
    @Pattern(
        regexp = "^[0-9]{10}$",
        message = "Phone number must contain exactly 10 digits"
    )
    private String phone;



    @NotBlank(message="Department is required")
    private String department;



    @NotBlank(message="Designation is required")
    private String designation;



    @Positive(message="Salary must be greater than zero")
    private double salary;



    private LocalDate joiningDate;



    @Column(nullable = false)
    private String status = "Active";



    public Employee() {

    }



    public Employee(Long employeeId,
                    String name,
                    String email,
                    String phone,
                    String department,
                    String designation,
                    double salary,
                    LocalDate joiningDate,
                    String status) {


        this.employeeId = employeeId;

        this.name = name;

        this.email = email;

        this.phone = phone;

        this.department = department;

        this.designation = designation;

        this.salary = salary;

        this.joiningDate = joiningDate;

        this.status = status;

    }




    public Long getEmployeeId() {
        return employeeId;
    }


    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }



    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }



    public String getDepartment() {
        return department;
    }


    public void setDepartment(String department) {
        this.department = department;
    }



    public String getDesignation() {
        return designation;
    }


    public void setDesignation(String designation) {
        this.designation = designation;
    }



    public double getSalary() {
        return salary;
    }


    public void setSalary(double salary) {
        this.salary = salary;
    }



    public LocalDate getJoiningDate() {
        return joiningDate;
    }


    public void setJoiningDate(LocalDate joiningDate) {
        this.joiningDate = joiningDate;
    }



    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

}