package com.iotek.hrmgr.entity;

import java.util.Date;

/*
* 一次加班信息
* */
public class Overtime {
    private int overtimeId;
    private Employee employee;
    private Date start;
    private Date end;

    public Overtime() {
    }

    public int getOvertimeId() {
        return overtimeId;
    }

    public void setOvertimeId(int overtimeId) {
        this.overtimeId = overtimeId;
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
        return "Overtime{" +
                "overtimeId=" + overtimeId +
                ", employee=" + employee +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
