package com.iotek.hrmgr.controller;

import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.VisitorService;
import com.iotek.hrmgr.utils.TokenProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Controller("/user")
public class UserController {

    @Autowired
    VisitorService visitorService;

    @GetMapping("/visitor/new")
    public String visitorSignUpPage(Visitor visitor, HttpSession session, HttpServletRequest request){
        String token = TokenProcessor.getInstance().makeToken();
        session.setAttribute("loginToken", token);
        return "/visitor/signUp";
    }

    @PutMapping("/visitor")
    public String visitorSignUp(Visitor visitor, HttpSession session, HttpServletRequest request){
        boolean b = isRelogin(request);
        if (b){
            request.setAttribute("res","重复提交");
            return "/visitor/visitorLogin";
        }
        request.getSession().removeAttribute("loginToken");

        //md5加密
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            visitor.setPassword(md.digest(visitor.getPassword().getBytes()).toString());
        } catch (NoSuchAlgorithmException e) {
        }
        Visitor visitorR = visitorService.signUp(visitor);
        if (visitorR == null){
            return "/visitor/signUp";
        }
        return visitorLogin(visitorR, session, request);
    }

    @GetMapping("/sessions/new")
    public String visitorLoginPage(HttpSession session) {
        String token = TokenProcessor.getInstance().makeToken();
        session.setAttribute("loginToken", token);
        return "/visitor/visitorLogin";
    }

    @PostMapping("/sessions/create")
    public String visitorLogin(Visitor visitor, HttpSession session, HttpServletRequest request) {
        boolean b = isRelogin(request);
        if (b){
            request.setAttribute("res","重复提交");
            return "/visitor/visitorLogin";
        }
        request.getSession().removeAttribute("loginToken");

        //md5加密
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            visitor.setPassword(md.digest(visitor.getPassword().getBytes()).toString());
        } catch (NoSuchAlgorithmException e) {
        }
        Visitor visitorR = visitorService.login(visitor);
        if (visitorR == null) {
            request.setAttribute("res","登录失败");
            return "visitor/visitorLogin";
        }
        session.setAttribute("visitor", visitorR);
        return "success";
    }


    private boolean isRelogin(HttpServletRequest request) {
        String client_token = request.getParameter("loginToken");
        System.out.println("client_token: "+client_token);
        if(client_token==null || client_token==""){
            return true;
        }

        String server_token = (String) request.getSession().getAttribute("loginToken");
        System.out.println("server_token: "+server_token);
        if(server_token==null){
            return true;
        }
        if(!client_token.equals(server_token)){
            return true;
        }

        return false;
    }

}