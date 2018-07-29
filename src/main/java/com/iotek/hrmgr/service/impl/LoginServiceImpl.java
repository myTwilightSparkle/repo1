package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("LoginService")
public class LoginServiceImpl implements LoginService {

    @Transactional
    public String login(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        if(!username.equals(subject.getPrincipal())||!subject.isAuthenticated()){
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            try {
                subject.login(token);
            } catch (UnknownAccountException uae) {
                //用户名不存在
                return uae.getMessage();
            }catch (IncorrectCredentialsException ice) {
                //密码错误
                return ice.getMessage();
            }catch (LockedAccountException lae) {
                //账户被锁定
                return lae.getMessage();
            }catch(ExcessiveAttemptsException eae){
                //登录失败次数超过系统最大次数,请稍后重试
                return eae.getMessage();
            }catch (Exception e) {
                //出现其他异常
                return "用户名或密码错误";
            }
        }

        /*System.out.println("shiro session keys: "+subject.getSession().getAttributeKeys());
        System.out.println("DefaultSubjectContext_AUTHENTICATED_SESSION_KEY: " + subject.getSession().getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_AUTHENTICATED_SESSION_KEY"));
        System.out.println("HttpServletSession.HOST_SESSION_KEY: " + subject.getSession().getAttribute("org.apache.shiro.web.session.HttpServletSession.HOST_SESSION_KEY"));

        //这个就是用户名
        System.out.println("DefaultSubjectContext_PRINCIPALS_SESSION_KEY: " + subject.getSession().getAttribute("org.apache.shiro.subject.support.DefaultSubjectContext_PRINCIPALS_SESSION_KEY"));*/

        return "success";
    }
}