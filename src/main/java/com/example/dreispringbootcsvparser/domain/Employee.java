package com.example.dreispringbootcsvparser.domain;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import com.opencsv.bean.CsvNumber;
import com.opencsv.bean.validators.MustMatchRegexExpression;
import com.opencsv.bean.validators.PreAssignmentValidator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


public class Employee {

    @CsvBindByName(column = "emp_id", required = true)
    private long empId;
    @CsvBindByName(column = "first_name", required = true)
    private String firstName;
    @CsvBindByName(column = "last_name", required = true)
    private String lastName;

    @PreAssignmentValidator(validator = MustMatchRegexExpression.class, paramString = "^1001|1002|1003$")
    @CsvBindByName(column = "dep_id", required = true)
    private String depId;

    @CsvDate(value = "yyyy-mm-dd")
    @CsvBindByName(column = "salary_date", required = true)
    private Date salaryDate;
    @CsvBindByName(required = true)
    private float salary;

    /**
     * Constructs an Employee object with specified employee id, first name,
     * last name, department id, salary date and salary.
     *
     * @param empId      the employee id
     * @param firstName  the first name of the employee
     * @param lastName   the last name of the employee
     * @param depId      the department id of the employee
     * @param salaryDate the salary date of the employee
     * @param salary     the salary of the employee
     */
    public Employee(long empId, String firstName, String lastName, String depId, Date salaryDate, float salary) {
        this.empId = empId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.depId = depId;
        this.salaryDate = salaryDate;
        this.salary = salary;
    }

    public Employee() {

    }

    public long getEmpId() {
        return empId;
    }

    public void setEmpId(long empId) {
        this.empId = empId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return empId + "," + firstName + "," + lastName + "," + depId + "," + dateFormat.format(salaryDate) + "," + salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return empId == employee.empId && employee.salary == salary && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && depId.equals(employee.depId);
    }


    @Override
    public int hashCode() {
        return Objects.hash(empId, firstName, lastName, depId, salaryDate, salary);
    }

}
