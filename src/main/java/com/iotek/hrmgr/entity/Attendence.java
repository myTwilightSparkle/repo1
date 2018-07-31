package com.iotek.hrmgr.entity;

import java.util.Date;
/*
* 一次出勤的记录
* */
public class Attendence {
    private int attendenceId;
    private Visitor visitor;
    private Date datetime;
    private String type;

    public Attendence() {
    }

    public int getAttendenceId() {
        return attendenceId;
    }

    public void setAttendenceId(int attendenceId) {
        this.attendenceId = attendenceId;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Attendence{" +
                "attendenceId=" + attendenceId +
                ", visitor=" + visitor +
                ", datetime=" + datetime +
                ", type='" + type + '\'' +
                '}';
    }
}
