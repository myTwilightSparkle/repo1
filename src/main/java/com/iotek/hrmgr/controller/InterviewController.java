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

import java.util.Date;

@Controller
public class InterviewController {

    @Autowired
    private InterviewService interviewService;

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/newInterview")
    public String newInterview(@RequestParam int visitorId, Model model){
        model.addAttribute(visitorId);
        return "/admin/newInterview";
    }

    @PostMapping("/interview")
    public String offerInterview(@RequestParam Date time, @RequestParam String interviewer, @RequestParam Integer visitorId){
        Visitor visitor = new Visitor();
        visitor.setVisitorId(visitorId);
        visitor=visitorService.getVisitor(visitor);
        Interview interview = new Interview();
        interview.setTime(time);
        interview.setInterviewer(interviewer);
        interview.setVisitor(visitor);
        interviewService.offerInterview(interview);
        return "/admin/adminHome";
    }

}
