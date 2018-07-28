package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Recruitment;
import com.iotek.hrmgr.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("/recruitment")
    public String searchRecruitment(@RequestParam String arg, Model model){
        model.addAttribute("arg",arg);
        List<Recruitment> rs = recruitmentService.searchRecruitments(arg,1);
        model.addAttribute("recruits",rs);
        return "/visitor/recruitList";
    }

    /*@GetMapping("/recruitment/nextPage")
    public PageInfo<Recruitment> nextPage(HttpSession session){
        String arg = (String)session.getAttribute("arg");
        int pageNum = (Integer)session.getAttribute("pageNum");
        PageInfo<Recruitment> pi = recruitmentService.searchRecruitments(arg, pageNum+1);
        session.setAttribute("pageNum",pi.getPageNum());
        return pi;
    }

    @GetMapping("/recruitment/previousPage")
    public PageInfo<Recruitment> previousPage(HttpSession session){
        String arg = (String)session.getAttribute("arg");
        int pageNum = (Integer)session.getAttribute("pageNum");
        PageInfo<Recruitment> pi = recruitmentService.searchRecruitments(arg, pageNum-1);
        session.setAttribute("pageNum",pi.getPageNum());
        return pi;
    }*/



}
