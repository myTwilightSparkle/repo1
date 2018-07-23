package com.iotek.hrmgr.entity;
/*
* 部门
* */
public class Dept {
    private int deptId;
    private String name;

    public Dept() {
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptId=" + deptId +
                ", name='" + name + '\'' +
                '}';
    }
}
