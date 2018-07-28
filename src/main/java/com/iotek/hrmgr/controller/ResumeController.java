package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Resume;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.ResumeService;
import com.iotek.hrmgr.service.VisitorService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private VisitorService visitorService;

    @GetMapping("/resume")
    public String searchResume(String arg, Model model){
        List<Resume> rs = resumeService.searchResume(arg,1);
        model.addAttribute("resumes",rs);
        return "/admin/resumeList";
    }

    @GetMapping("/myResume")
    public String getMyResume(Model model){
        Visitor visitor = new Visitor();
        String name = (String)SecurityUtils.getSubject().getPrincipal();
        visitor.setName(name);
        visitor = visitorService.getVisitor(visitor);
        List<Resume> rs = resumeService.findOnesResume(visitor,1);
        model.addAttribute("resumes",rs);
        System.out.println(rs);
        return "/visitor/resumeList";
    }
}
