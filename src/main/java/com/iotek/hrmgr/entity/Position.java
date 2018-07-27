package com.iotek.hrmgr.entity;
/*
* 职位
* */
public class Position {
    private int positionId;
    private String name;

    public Position() {
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", name='" + name + '\'' +
                '}';
    }
}
