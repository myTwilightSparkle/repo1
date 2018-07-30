package com.iotek.hrmgr.entity;

import java.util.Date;

public class Interview {
    private int interviewId;
    private Visitor visitor;
    private Date time;
    private String interviewer;
    private String result;

    public int getInterviewId() {
        return interviewId;
    }

    public void setInterviewId(int interviewId) {
        this.interviewId = interviewId;
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
                "interviewId=" + interviewId +
                ", visitor=" + visitor +
                ", time=" + time +
                ", interviewer='" + interviewer + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
