package com.iotek.hrmgr.entity;

import java.util.Date;

/**
 * 伪排班表
 * 不查数据库
 * 和员工没关联
 * 直接返回迟到没迟到早退没早退
 */
public class Schedule {

    private int employeeId;

    private String clockin;
    private String clockout;

    public Schedule(){
        this.employeeId = employeeId;
        Date date = new Date();
        if (date.getDay()==6||date.getDay()==7){
            clockin = "";
            clockout = "";
        } else if (date.getHours()>=9){
            clockin = "late";
            clockout = "";
        } else if (date.getHours()<17){
            clockin = "";
            clockout = "early";
        }
    }

    public String getClockin() {
        return clockin;
    }

    public String getClockout() {
        return clockout;
    }
}
