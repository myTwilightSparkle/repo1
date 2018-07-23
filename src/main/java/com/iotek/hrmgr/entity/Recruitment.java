package com.iotek.hrmgr.entity;

import java.util.Date;

/*
* 一条招聘信息
* */
public class Recruitment {
    private int recruitId;
    private Position position;//无id的Position
    private int number;//招几个
    private boolean available;//是否有效
    private Date pbdate;//发布日期

    public Recruitment() {
    }

    public int getRecruitId() {
        return recruitId;
    }

    public void setRecruitId(int recruitId) {
        this.recruitId = recruitId;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Date getPbdate() {
        return pbdate;
    }

    public void setPbdate(Date pbdate) {
        this.pbdate = pbdate;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "recruitId=" + recruitId +
                ", position=" + position +
                ", number=" + number +
                ", available=" + available +
                ", pbdate=" + pbdate +
                '}';
    }
}
