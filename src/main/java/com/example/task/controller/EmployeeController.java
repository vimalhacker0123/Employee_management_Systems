package com.example.task.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.example.task.entity.Employee;
import com.example.task.service.EmployeeService;
import com.example.task.util.CsvExporter;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

   
    @GetMapping("/dashboard")
    public String dashboard(Model model) {


        long totalEmployees = employeeService.getTotalEmployees();

        long activeEmployees = employeeService.getActiveEmployees();


        // Check values in console
        System.out.println("Total = " + totalEmployees);
        System.out.println("Active = " + activeEmployees);



        model.addAttribute("totalEmployees", totalEmployees);

        model.addAttribute("activeEmployees", activeEmployees);



        return "dashboard";
    }

   
    @GetMapping("/add")
    public String showAddEmployee(Model model) {

        model.addAttribute("employee", new Employee());

        return "addEmployee";
    }

   
    @PostMapping("/save")
    public String saveEmployee(@Valid @ModelAttribute("employee") Employee employee,
                               BindingResult result) {

        if (result.hasErrors()) {
            return "addEmployee";
        }

        employeeService.saveEmployee(employee);

        return "redirect:/employees/list";
    }

   
    @GetMapping("/list")
    public String listEmployees(Model model) {

        List<Employee> employees = employeeService.getAllEmployees();

        model.addAttribute("employees", employees);

        return "employeeList";
    }

    
    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id,
                               Model model) {

        Employee employee = employeeService.getEmployeeById(id);

        model.addAttribute("employee", employee);

        return "updateEmployee";
    }

  
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id,
                                 @Valid @ModelAttribute("employee") Employee employee,
                                 BindingResult result) {

        if (result.hasErrors()) {
            return "updateEmployee";
        }

        employeeService.updateEmployee(id, employee);

        return "redirect:/employees/list";
    }

  
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {

        employeeService.deleteEmployee(id);

        return "redirect:/employees/list";
    }

  
    @GetMapping("/search/name")
    public String searchByName(@RequestParam("keyword") String keyword,
                               Model model) {

        List<Employee> employees = employeeService.searchByName(keyword);

        model.addAttribute("employees", employees);

        return "employeeList";
    }

   
    @GetMapping("/search/department")
    public String searchByDepartment(@RequestParam("department") String department,
                                     Model model) {

        List<Employee> employees = employeeService.searchByDepartment(department);

        model.addAttribute("employees", employees);

        return "employeeList";
    }

   
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo,
                                @RequestParam(defaultValue = "5") int pageSize,
                                @RequestParam(defaultValue = "name") String sortField,
                                @RequestParam(defaultValue = "asc") String sortDir,
                                Model model) {

        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir",
                sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("employees", page.getContent());

        return "employeeList";
    }

  
    @GetMapping("/export")
    public void exportCSV(HttpServletResponse response) throws IOException {

        List<Employee> employees = employeeService.getAllEmployees();

        CsvExporter.export(response, employees);
    }

}