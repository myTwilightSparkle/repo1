package com.iotek.hrmgr.utils;

import com.iotek.hrmgr.entity.MailUser;
import org.springframework.mail.SimpleMailMessage;

import java.text.SimpleDateFormat;
import java.util.*;
public class MailMessageGenerator {
    private static SimpleMailMessage message = new SimpleMailMessage();

    private String template;
    private MailUser mailUser;
    private Date date;
    private String fromUsername;

    static SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日 HH时mm分");

    private String subject = "";
    private String text = "";


    public MailMessageGenerator(MailUser mailUser, Date date, String template, String fromUsername){
        this.mailUser = mailUser;
        this.template = template;
        this.date = date;
        this.fromUsername = fromUsername;
    }

    public SimpleMailMessage genMessage(){
        substitute();
        setSubject();
        setText();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(mailUser.getMailAddress());
        message.setFrom(fromUsername);
        return message;
    }

    /*
    *   *{subject@{name}@{date}}#{text}
    * */

    private void substitute(){
        String name = mailUser.getName();
        String dateStr = sdf.format(date);
        String newTemplate = "";
        String[] strs = template.split("@");
        Iterator<String> iter = Arrays.asList(strs).iterator();
        while(iter.hasNext()){
            String str = iter.next();
            if (str.length()<7){
                continue;
            }
            String str0 = str.substring(0,6);
            if (str0.equals("{name}")){
                newTemplate += name;
                newTemplate += str.substring(6,str.length());
            } else if(str0.equals("{date}")){
                newTemplate += dateStr;
                newTemplate += str.substring(6,str.length());
            } else {
                newTemplate += str;
            }
        }
        template = newTemplate;
    }


    private void setSubject(){
        String[] strs = template.split("\\u002A");
        List<String> ls = Arrays.asList(strs);
        for (String str:ls){
            if (ls.indexOf(str)<1){
                continue;
            }
            Stack stack = new Stack();
            if (str.length()>0){
                for (char c:str.toCharArray()){
                    if (c=='{'){
                        stack.push('{');
                    }if(c=='}'){
                        stack.pop();
                    }
                    if (stack.isEmpty()){
                        break;
                    }
                    subject += c;
                }
                subject = subject.substring(1,subject.length());
                return;
            } else {
                continue;
            }
        }
    }

    private void setText(){
        String[] strs = template.split("#");
        List<String> ls = Arrays.asList(strs);
        for (String str:ls){
            if (ls.indexOf(str)<1){
                continue;
            }
            Stack stack = new Stack();
            if (str.length()>0){
                for (char c:str.toCharArray()){
                    if (c=='{'){
                        stack.push('{');
                    }if(c=='}'){
                        stack.pop();
                    }
                    if (stack.isEmpty()){
                        break;
                    }
                    text += c;
                }
                text = text.substring(1,text.length());
                return;
            } else {
                continue;
            }
        }
    }

}
