package com.iotek.hrmgr.entity;
/*
* 邮件接收者
* 构造参数可以是：Visitor, Employee, Admin
* */
public class MailUser {
    public String name;
    public String mailAddress;

    public MailUser(Admin admin) {
    }

    public MailUser(Employee employee) {
    }

    public MailUser(Visitor visitor) {
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
