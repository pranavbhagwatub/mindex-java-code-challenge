package com.mindex.challenge.data;

/*
1. This class contains two data members
    a. employee
    b. numberOfReports
2. Getters and setters have been created to access and modify the data members
3. This class encapsulates the data members based on the expected response type for the Task 1
 */
public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure (Employee employee, int numberOfReports) {
        this.employee = employee;
        this.numberOfReports = numberOfReports;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public int getNumberOfReports() { return numberOfReports; }

    public void setNumberOfReports(int numberOfReports) {
        this.numberOfReports = numberOfReports;
    }
}
