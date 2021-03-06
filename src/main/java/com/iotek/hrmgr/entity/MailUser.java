package com.iotek.hrmgr.entity;
/*
* 邮件接收者
* 构造参数可以是：Visitor, Employee, Admin
* */
public class MailUser {
    public String name;
    public String mailAddress;

    public MailUser(Visitor visitor) {
        this.name = visitor.getName();
        this.mailAddress = visitor.getEmail();
    }

    public MailUser (Employee employee){
        this.name = employee.getRealname();
        this.mailAddress = employee.getEmail();
    }

    public MailUser (String name, String mailAddress){
        this.name = name;
        this.mailAddress = mailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }
}
