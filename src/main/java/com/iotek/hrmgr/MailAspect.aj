package com.iotek.hrmgr;

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

