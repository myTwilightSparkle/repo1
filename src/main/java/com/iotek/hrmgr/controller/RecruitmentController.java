package com.iotek.hrmgr.controller;

import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Recruitment;
import com.iotek.hrmgr.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/recruitList")
public class RecruitmentController {

    @Autowired
    private RecruitmentService recruitmentService;

    @GetMapping("/recruitment")
    public PageInfo<Recruitment> searchRecruitment(@RequestParam String arg, HttpSession session){
        session.setAttribute("arg",arg);
        PageInfo<Recruitment> pi = recruitmentService.searchRecruitments(arg,1);
        session.setAttribute("pageNum",pi.getPageNum());
        return pi;
    }

    @GetMapping("/recruitment/nextPage")
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
    }



}
