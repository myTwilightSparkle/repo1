package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Interview;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.InterviewService;
import com.iotek.hrmgr.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/newInterview")
    public String newInterview(@RequestParam int visitorId, Model model) {
        model.addAttribute("visitorId", visitorId);
        return "/admin/newInterview";
    }

    /*
    提供面试
     */
    @PostMapping("/admin/interview")
    public String offerInterview(@RequestParam String date, @RequestParam String time, @RequestParam String interviewer, @RequestParam Integer visitorId) {
        Visitor visitor = new Visitor();
        visitor.setVisitorId(visitorId);
        visitor = visitorService.getVisitor(visitor);
        Interview interview = new Interview();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");

        Date date1 = new Date();
        Date date2 = new Date();

        try {
            date1 = sdf1.parse(date);
            date2 = sdf2.parse(time);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        date1.setHours(date2.getHours());
        date1.setMinutes(date2.getMinutes());

        interview.setTime(date1);
        interview.setInterviewer(interviewer);
        interview.setVisitor(visitor);
        interview.setResult("无");
        interviewService.offerInterview(interview);
        return "/admin/adminHome";
    }

    @GetMapping("/interviewList")
    public String interviewList(Model model){
        List<Interview> rs = interviewService.getAllInterviews();
        model.addAttribute("intvs", rs);
        return "/admin/interviewList";
    }

    @GetMapping("/admin/interviewAccept")
    public String acceptInterview(@RequestParam int interviewId, Model model){
        interviewService.acceptInterview(interviewId);
        return "/admin/interviewList";
    }

}
