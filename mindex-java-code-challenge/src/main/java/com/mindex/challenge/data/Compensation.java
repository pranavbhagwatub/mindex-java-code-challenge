package com.mindex.challenge.data;

/*
1. This class contains three data members
    a. employee
    b. salary
    c. effectiveDate
2. Getters and setters have been created to access and modify the data members
3. This class encapsulates the data members based on the expected response type for the Task 2
 */
public class Compensation {
    Employee employee;
    String salary;
    String effectiveDate;

    public String getSalary() { return salary; }

    public void setSalary(String salary) { this.salary = salary; }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
