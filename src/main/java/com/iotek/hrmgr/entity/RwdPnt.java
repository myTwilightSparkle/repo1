package com.iotek.hrmgr.entity;

import java.math.BigDecimal;
import java.util.Date;
/*
* 奖惩记录
* */
public class RwdPnt {
    private int rpId;
    private Visitor visitor;
    private Date date;
    private String cause;//原因
    private BigDecimal fine;//正罚负奖
    private boolean handled;//已经处理 默认false

    public RwdPnt() {
    }

    public int getRpId() {
        return rpId;
    }

    public void setRpId(int rpId) {
        this.rpId = rpId;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
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

    public boolean isHandled() {
        return handled;
    }

    public void setHandled(boolean handled) {
        this.handled = handled;
    }

    @Override
    public String toString() {
        return "RwdPnt{" +
                "rpId=" + rpId +
                ", visitor=" + visitor +
                ", date=" + date +
                ", cause='" + cause + '\'' +
                ", fine=" + fine +
                ", handled=" + handled +
                '}';
    }
}
