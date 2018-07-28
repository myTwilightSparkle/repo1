package com.iotek.hrmgr.entity;

import java.util.Date;
import java.util.Objects;

/*
* 一条招聘信息
* */
public class Recruitment {
    private int recruitId;
    private Position position;
    private String demand;
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

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    @Override
    public String toString() {
        return "Recruitment{" +
                "recruitId=" + recruitId +
                ", position=" + position +
                ", demand='" + demand + '\'' +
                ", number=" + number +
                ", available=" + available +
                ", pbdate=" + pbdate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recruitment that = (Recruitment) o;
        return recruitId == that.recruitId;
    }

    @Override
    public int hashCode() {

        return Objects.hash(recruitId);
    }
}
