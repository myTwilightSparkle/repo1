package com.iotek.hrmgr.entity;

import java.math.BigDecimal;
import java.util.Date;
/*
* 奖惩记录
* */
public class RwdPnt {
    private int rpId;
    private Employee employee;
    private Date date;
    private String cause;
    private BigDecimal fine;//正罚负奖

    public RwdPnt() {
    }

    public int getRpId() {
        return rpId;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "RwdPnt{" +
                "rpId=" + rpId +
                ", employee=" + employee +
                ", date=" + date +
                ", cause='" + cause + '\'' +
                ", fine=" + fine +
                '}';
    }
}
