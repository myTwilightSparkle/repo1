package com.iotek.hrmgr.entity;
/*
* 员工
* */
public class Employee {
    private int employeeId;
    private String realname;
    private String email;
    private Dept dept;
    private boolean resigned;

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public boolean isResigned() {
        return resigned;
    }

    public void setResigned(boolean resigned) {
        this.resigned = resigned;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", realname='" + realname + '\'' +
                ", email='" + email + '\'' +
                ", dept=" + dept +
                ", resigned=" + resigned +
                '}';
    }
}
