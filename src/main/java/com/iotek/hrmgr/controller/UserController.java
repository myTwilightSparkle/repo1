package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.LoginService;
import com.iotek.hrmgr.service.VisitorService;
import com.iotek.hrmgr.utils.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller("/user")
public class UserController {

    @Autowired
    VisitorService visitorService;

    @Autowired
    LoginService loginService;

    /*
    注册页面
    */
    @GetMapping("/visitor/new")
    public String visitorSignUpPage(Visitor visitor, HttpSession session, HttpServletRequest request) {
        String token = TokenProcessor.getInstance().makeToken();
        session.setAttribute("loginToken", token);
        return "/signUp";
    }

    /*
    注册
    */
    @PutMapping("/visitor")
    public String visitorSignUp(Visitor visitor, HttpSession session, HttpServletRequest request) {
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
            return "/signUp";
        }
        return visitorLogin(visitorR, session, request);
    }

    /*
    登录页面
     */
    @GetMapping("/sessions/new")
    public String visitorLoginPage(HttpSession session) {
        String token = TokenProcessor.getInstance().makeToken();
        session.setAttribute("loginToken", token);
        return "/login/visitorLogin";
    }

    /*
    登录
     */
    @PostMapping("/sessions/create")
    public String visitorLogin(Visitor visitor, HttpSession session, HttpServletRequest request) {
        boolean b = isRelogin(request);
        if (b) {
            request.setAttribute("res", "重复提交");
            return "/visitor/visitorLogin";
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
            System.out.println("HttpSession keys: "+session.getAttributeNames());
            return "/visitor/visitorHome";
        }
        session.setAttribute("res",res);
        return "/login/visitorLogin";
    }


    /*
    判断重复提交
     */
    private boolean isRelogin(HttpServletRequest request) {
        String client_token = request.getParameter("loginToken");
        System.out.println("client_token: " + client_token);
        if (client_token == null || client_token == "") {
            return true;
        }

        String server_token = (String) request.getSession().getAttribute("loginToken");
        System.out.println("server_token: " + server_token);
        if (server_token == null) {
            return true;
        }
        if (!client_token.equals(server_token)) {
            return true;
        }

        return false;
    }

}