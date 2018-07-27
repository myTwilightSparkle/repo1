package com.iotek.hrmgr.aspect;

import com.iotek.hrmgr.entity.MailUser;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.utils.MailMessageGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MailAspect {

    @Autowired
    JavaMailSender javaMailSender;

    @Pointcut("within(com.iotek.hrmgr.controller.UserController+) && execution(* com.iotek.hrmgr.controller.UserController.visitorSignUp())")
    public void signUpInfo() {
    }


    /*
    注册邮件
     */
    @Async
    @Around("signUpInfo()")
    public void process(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");

        //访问目标方法的参数：

        Object[] args = point.getArgs();

        //用改变后的参数执行目标方法

        Object returnValue = point.proceed(args);

        System.out.println("@Around：执行目标方法之后...");

        Visitor visitor = (Visitor)args[0];
        System.out.println("Aspect: arg: "+visitor);

        String fromUsername = "2570945863@qq.com";
        String template = "*{@{toName}注册成功}#{感谢注册\n@{currentDate}}";
        String[] params = null;
        SimpleMailMessage message = new MailMessageGenerator(new MailUser(visitor), fromUsername, template, params).genMessage();

        javaMailSender.send(message);
    }

}
