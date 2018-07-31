package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Attendence;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.AttendenceService;
import com.iotek.hrmgr.service.VisitorService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class AttendenceController {

    @Autowired
    VisitorService visitorService;

    @Autowired
    AttendenceService attendenceService;


    /*
    上班打卡
     */
    @ResponseBody
    @GetMapping("clockin")
    public String clockin(){
        //拿用户
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Visitor visitor = new Visitor();
        visitor.setName(username);
        visitor=visitorService.getVisitor(visitor);
        //设Attendence
        Attendence attendence = new Attendence();
        attendence.setVisitor(visitor);
        attendence.setDatetime(new Date());
        attendence.setType("clockin");
        attendenceService.add(attendence);
        return "打卡成功";
    }

    /*
    迟到打卡
     */
    @ResponseBody
    @GetMapping("late")
    public String late(){
        //拿用户
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Visitor visitor = new Visitor();
        visitor.setName(username);
        visitor=visitorService.getVisitor(visitor);
        //设Attendence
        Attendence attendence = new Attendence();
        attendence.setVisitor(visitor);
        attendence.setDatetime(new Date());
        attendence.setType("late");
        attendenceService.add(attendence);
        return "迟到";
    }

    /*
    下班打卡
     */
    @ResponseBody
    @GetMapping("clockout")
    public String clockout(){
        //拿用户
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Visitor visitor = new Visitor();
        visitor.setName(username);
        visitor=visitorService.getVisitor(visitor);
        //设Attendence
        Attendence attendence = new Attendence();
        attendence.setVisitor(visitor);
        attendence.setDatetime(new Date());
        attendence.setType("clockout");
        attendenceService.add(attendence);
        return "打卡成功";
    }

    /*
    早退打卡
     */
    @ResponseBody
    @GetMapping("early")
    public String early(){
        //拿用户
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Visitor visitor = new Visitor();
        visitor.setName(username);
        visitor=visitorService.getVisitor(visitor);
        //设Attendence
        Attendence attendence = new Attendence();
        attendence.setVisitor(visitor);
        attendence.setDatetime(new Date());
        attendence.setType("early");
        attendenceService.add(attendence);
        return "早退";
    }

}
