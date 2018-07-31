package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Schedule;
import com.iotek.hrmgr.entity.impl.ScheduleImpl;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.entity.impl.ScheduleImpl;
import com.iotek.hrmgr.service.AttendenceService;
import com.iotek.hrmgr.service.LoginService;
import com.iotek.hrmgr.service.TrashService;
import com.iotek.hrmgr.service.VisitorService;
import com.iotek.hrmgr.utils.TokenProcessor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    VisitorService visitorService;

    @Autowired
    LoginService loginService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    TrashService trashService;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    AttendenceService attendenceService;

    Schedule schedule = new ScheduleImpl();

    /*
    注册页面
    */
    @GetMapping("/signUpPage")
    public String visitorSignUpPage(Visitor visitor, Model model, HttpServletRequest request) {
        String token = TokenProcessor.getInstance().makeToken();
        model.addAttribute("loginToken", token);
        request.getSession().setAttribute("loginToken",token);
        return "/signUp";
    }

    /*
    注册
    */
    @PostMapping("/signUp")
    public String visitorSignUp(Visitor visitor, HttpServletRequest request) {
        boolean b = isRelogin(request);
        if (b) {
            request.setAttribute("res", "重复提交");
            return "/signUp";
        }
        request.getSession().removeAttribute("loginToken");

        //md5加密
        /*try {
            MessageDigest md = MessageDigest.getInstance("md5");
            visitor.setPassword(md.digest(visitor.getPassword().getBytes()).toString());
        } catch (NoSuchAlgorithmException e) {
        }*/

        Visitor visitorR = visitorService.signUp(visitor);
        if (visitorR == null) {
            request.setAttribute("res","用户名或邮箱或手机号已经注册过了");
            return "/signUp";
        }
        return "/index";
    }

    /*
    登录页面
     */
    @GetMapping("/session")
    public String visitorLoginPage(Model model, HttpSession session) {
        String token = TokenProcessor.getInstance().makeToken();
        model.addAttribute("loginToken", token);
        session.setAttribute("loginToken",token);
        return "/login/visitorLogin";
    }



    /*
    登录
     */
    @PostMapping("/session")
    public String visitorLogin(Visitor visitor, Model model, HttpServletRequest request) {
        //trashService.sendTrashMails();
        boolean b = isRelogin(request);
        if (b) {
            request.setAttribute("res", "重复提交");
            System.out.println("重复提交");
            //return "/login/visitorLogin";
        }
        request.getSession().removeAttribute("loginToken");
/*
        //md5加密
        *//*try {
            MessageDigest md = MessageDigest.getInstance("md5");
            visitor.setPassword(md.digest(visitor.getPassword().getBytes()).toString());
        } catch (NoSuchAlgorithmException e) {
        }*//*
        Visitor visitorR = visitorService.login(visitor);
        if (visitorR == null) {
            request.setAttribute("res","登录失败");
            return "/login/visitorLogin";
        }
        session.setAttribute("visitor", visitorR);
        return "/visitor/visitorHome";*/

        String res = loginService.login(visitor.getName(),visitor.getPassword());
        if (res.equals("success")){
            /*if (SecurityUtils.getSubject().hasRole("admin"))
                return "/admin/adminHome";
            if (SecurityUtils.getSubject().hasRole("employee"))
                return "/employee/employeeHome";
            return "/visitor/visitorHome";*/
            return index(model);
        }
        model.addAttribute("res",res);
        return visitorLoginPage(model,request.getSession());
    }

    /*
    visitorHome
     */
    @GetMapping("/visitorHome")
    public String visitorHome(){
        return "/visitor/visitorHome";
    }

    /*
    employeeHome
     */
    @GetMapping("employeeHome")
    public String employeeHome(){
        return "/employee/employeeHome";
    }

    /*
    adminHome
     */
    @GetMapping("adminHome")
    public String adminHome(){
        return "/admin/adminHome";
    }

    /*
    判断重复提交
     */
    private boolean isRelogin(HttpServletRequest request) {
        String client_token = request.getParameter("loginToken");
        if (client_token == null || client_token == "") {
            return true;
        }

        String server_token = (String) request.getSession().getAttribute("loginToken");
        if (server_token == null) {
            return true;
        }
        if (!client_token.equals(server_token)) {
            return true;
        }

        return false;
    }


    /*
    登出
     */
    @GetMapping("/logOut")
    public String logOut(){
        System.out.println("loging out...");
        SecurityUtils.getSubject().logout();
        return "index";
    }

    @GetMapping("/")
    public String index(Model model){

        if (SecurityUtils.getSubject().hasRole("admin")){
            return "/admin/adminHome";
        }
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("employee")){

            String name = (String) subject.getPrincipal();

            //这里要查当天的个人出勤记录
            boolean b1 = attendenceService.hasClockin(name);
            boolean b2 = attendenceService.hasClockout(name);
            model.addAttribute("hasClockin",b1);
            model.addAttribute("hasClockout",b2);

            model.addAttribute("late",schedule.getClockin("name"));
            model.addAttribute("early",schedule.getClockout("name"));
            return "/employee/employeeHome";
        }
        if (SecurityUtils.getSubject().hasRole("visitor")){
            return "/visitor/visitorHome";
        }
        return "index";

    }
}