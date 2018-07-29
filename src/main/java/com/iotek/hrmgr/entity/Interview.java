package com.iotek.hrmgr.entity;

import java.util.Date;

public class Interview {
    private int interviewName;
    private Visitor visitor;
    private Date time;
    private String interviewer;
    private String result;

    public int getInterviewName() {
        return interviewName;
    }

    public void setInterviewName(int interviewName) {
        this.interviewName = interviewName;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Interview{" +
                "interviewName=" + interviewName +
                ", visitor=" + visitor +
                ", time=" + time +
                ", interviewer='" + interviewer + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
