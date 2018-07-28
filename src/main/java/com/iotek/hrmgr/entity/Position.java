package com.iotek.hrmgr.entity;
/*
* 职位
* */
public class Position {
    private int positionId;
    private String name;
    private int deptId;

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

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", name='" + name + '\'' +
                '}';
    }
}
