package com.iotek.hrmgr.entity;

import java.util.Date;
/*
* 一次出勤的记录
* */
public class Attendence {
    private Employee employee;
    private Date start;
    private Date end;

    public Attendence() {
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Attendence{" +
                "employee=" + employee +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
