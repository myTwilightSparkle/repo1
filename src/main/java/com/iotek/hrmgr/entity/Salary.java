package com.iotek.hrmgr.entity;

import java.math.BigDecimal;
import java.util.Date;
/*
* 一条工资记录
* */
public class Salary {
    private int salaryId;
    private Employee employee;
    private BigDecimal salary;
    private Date date;
    private boolean haspaid;

    public Salary() {
    }

    public int getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(int salaryId) {
        this.salaryId = salaryId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isHaspaid() {
        return haspaid;
    }

    public void setHaspaid(boolean haspaid) {
        this.haspaid = haspaid;
    }

    @Override
    public String toString() {
        return "Salary{" +
                "salaryId=" + salaryId +
                ", employee=" + employee +
                ", salary=" + salary +
                ", date=" + date +
                ", haspaid=" + haspaid +
                '}';
    }
}
