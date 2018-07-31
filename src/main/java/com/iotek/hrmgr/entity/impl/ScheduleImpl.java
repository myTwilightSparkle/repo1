package com.iotek.hrmgr.entity.impl;

import com.iotek.hrmgr.entity.Schedule;

import java.util.Date;

/**
 * 伪排班表
 * 不查数据库
 * 和员工没关联
 * 直接返回迟到没迟到早退没早退
 */
public class ScheduleImpl implements Schedule {


    private String clockin;
    private String clockout;

    public ScheduleImpl(){
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

    @Override
    public String getClockin(String name) {
        return clockin;
    }

    @Override
    public String getClockout(String name) {
        return clockout;
    }
}
