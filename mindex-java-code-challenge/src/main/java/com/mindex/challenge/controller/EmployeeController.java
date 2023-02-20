package com.mindex.challenge.controller;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }

    /*
        1. For Task 1, created a new endpoint /employee/get_num_of_reports
        2. This is a GET endpoint.
        3. Request Input: This endpoint accepts an employee ID as a input parameter
        4. Response Output: This endpoint returns the total number of direct report for the employee along ith the fully filled out ReportingStructure
        4. Please check the following google document which contains sample request and response for this endpoint- https://docs.google.com/document/d/1ytzQe4LXqocHPvNvlAK0b-mtcMFPCnSJ42Ssn_lSbYQ/edit?usp=sharing
     */
    @GetMapping("/employee/get_num_of_reports/{id}")
    public ReportingStructure getDirectReports(@PathVariable String id) {
        LOG.debug("Received employee total report count request for id [{}]", id);

        return employeeService.numberOfReports(id);
    }
}
