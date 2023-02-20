package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    // Created a new abstract method in the interface to fetch the total number of direct reports for an employee
    ReportingStructure numberOfReports(String id);
}
