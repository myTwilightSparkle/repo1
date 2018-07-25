package com.iotek.hrmgr.utils;

import com.iotek.hrmgr.entity.MailUser;
import org.springframework.mail.SimpleMailMessage;

import java.text.SimpleDateFormat;
import java.util.*;

/*
 *  生成message的工厂
 *  用genMessage()方法
 *  按这个格式解析字符串  *{subject @{param0}, @{param1}, ...}#{text @{param0}, @{param1}, ...}
 *  params的格式   param0=value0, param1=value1, ...
 *  内置parm: currentDate, toName
 * */
public class MailMessageGenerator {

    private static SimpleMailMessage message = new SimpleMailMessage();
    private String template;
    private MailUser mailUser;
    private String fromUsername;
    private Map<String, String> paramMap;

    static SimpleDateFormat sdf = new SimpleDateFormat("yy年MM月dd日 HH时mm分");

    private String subject = "";
    private String text = "";

    public MailMessageGenerator(MailUser mailUser, String fromUsername, String template, String... params){
        this.mailUser = mailUser;
        this.fromUsername = fromUsername;
        this.template = template;
        setMap(params);
    }

    public SimpleMailMessage genMessage(){
        substituteParams();
        setSubject();
        setText();
        message.setSubject(subject);
        message.setText(text);
        message.setTo(mailUser.getMailAddress());
        message.setFrom(fromUsername);
        return message;
    }

    private void setMap(String... params){
        paramMap = new HashMap<String, String>();
        paramMap.put("currentDate", sdf.format(new Date()));
        paramMap.put("toName",mailUser.getName());
        if (params == null || params.length == 0)
            return;
        for (String str:params){
            String[] strs = str.split("=");
            paramMap.put(strs[0],strs[1]);
        }
    }

    private void substituteParams(){
        String newTemplate = "";
        String[] strs = template.split("@");
        newTemplate += strs[0];
        strs = Arrays.copyOfRange(strs, 1,strs.length);
        Iterator<String> iter = Arrays.asList(strs).iterator();
        while(iter.hasNext()){
            String str = iter.next();
            String str0 = insideBracket(str);
            if (paramMap.containsKey(str0)){
                String val = paramMap.get(str0);
                String rm = str.substring(str0.length()+2);
                newTemplate += val + rm;
            } else {
                newTemplate += str;
            }
        }
        template = newTemplate;
    }

    private String insideBracket(String str){
        String str0 = "";
        if (str.charAt(0)!='{'){
            return str;
        }
        Stack stack = new Stack();
        for (char c:str.toCharArray()){
            if (c == '{') {
                stack.push('{');
            }
            if (c == '}'){
                stack.pop();
            }
            str0 += c;
            if (stack.isEmpty()){
                break;
            }
        }
        if (str0.length()>1)
            str0 = str0.substring(1,str0.length()-1);
        return str0;
    }

    private void setSubject(){
        String[] strs = template.split("\\u002A");
        List<String> ls = Arrays.asList(strs);
        ls = ls.subList(1,ls.size());
        for (String str:ls){
            if (str.length()>0&&str.charAt(0)!='{'){
                continue;
            }
            if (str.length()>0){
                subject = insideBracket(str);
            }
        }
    }

    private void setText(){
        String[] strs = template.split("#");
        List<String> ls = Arrays.asList(strs);
        ls = ls.subList(1,ls.size());
        for (String str:ls){
            if (str.length()>0&&str.charAt(0)!='{'){
                continue;
            }
            if (str.length()>0){
                text = insideBracket(str);
            }
        }
    }

}
