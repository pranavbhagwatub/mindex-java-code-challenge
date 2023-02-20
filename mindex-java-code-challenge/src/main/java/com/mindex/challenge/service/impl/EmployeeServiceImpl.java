package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /*
        This method provides the implementation for fetching the total number of direct reports for an employee
     */
    @Override
    public ReportingStructure numberOfReports(String id) {
        LOG.debug("Fetching total report count details for the employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);
        int count = getTotalReportCount(employee, 0);
        ReportingStructure objReportingStructure = new ReportingStructure(employee, count);
        if (employee == null) {
            throw new RuntimeException("Invalid employee Id: " + id);
        }
        return objReportingStructure;
    }

    /*
        This is a helper function that uses recursion to traverse through the employee structure and returns the total direct report count to the main method
     */
    public int getTotalReportCount(Employee employee, int count) {
        Employee empFromRepo = employeeRepository.findByEmployeeId(employee.getEmployeeId());
        List<Employee> directReports = empFromRepo.getDirectReports();

        if(directReports != null) {
            employee.setDirectReports(directReports);
            for(Employee report: directReports) {
                populateEmployeeDetails(employee, empFromRepo);
                count++;
                count = getTotalReportCount(report, count);
            }
        }
        else {
            populateEmployeeDetails(employee, empFromRepo);
        }
        return count;
    }

    public void populateEmployeeDetails(Employee employee, Employee empFromRepo) {
        employee.setFirstName(empFromRepo.getFirstName());
        employee.setPosition(empFromRepo.getPosition());
        employee.setDepartment(empFromRepo.getLastName());
        employee.setLastName(empFromRepo.getLastName());
    }
}
