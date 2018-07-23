package com.iotek.hrmgr.entity;

import java.util.Date;

/*
* 一次请假信息
* */
public class Leave {
    private int leaveId;
    private Employee employee;
    private Date start;
    private Date end;

    public Leave() {
    }

    public int getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(int leaveId) {
        this.leaveId = leaveId;
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
        return "Leave{" +
                "leaveId=" + leaveId +
                ", employee=" + employee +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
