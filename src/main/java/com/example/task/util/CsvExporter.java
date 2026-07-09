package com.example.task.util;



import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.example.task.entity.Employee;

import jakarta.servlet.http.HttpServletResponse;

public class CsvExporter {

    public static void export(HttpServletResponse response,
                              List<Employee> employees) throws IOException {

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition",
                "attachment; filename=employees.csv");

        PrintWriter writer = response.getWriter();

        writer.println("Employee ID,Name,Email,Phone,Department,Designation,Salary,Joining Date,Status");

        for (Employee emp : employees) {

            writer.println(
                    emp.getEmployeeId() + "," +
                    emp.getName() + "," +
                    emp.getEmail() + "," +
                    emp.getPhone() + "," +
                    emp.getDepartment() + "," +
                    emp.getDesignation() + "," +
                    emp.getSalary() + "," +
                    emp.getJoiningDate() + "," +
                    emp.getStatus()
            );
        }

        writer.flush();
        writer.close();
    }

}