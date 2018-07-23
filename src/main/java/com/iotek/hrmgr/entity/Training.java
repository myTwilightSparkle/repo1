package com.iotek.hrmgr.entity;

import java.util.Date;
/*
* 一次培训记录
* */
public class Training {
    private int trainingId;
    private String teacher;//老师
    private String issue;//课题
    private Date beginDate;//开始日期
    private Date finishDate;//结束日期

    public Training() {
    }

    public int getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(int trainingId) {
        this.trainingId = trainingId;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Date finishDate) {
        this.finishDate = finishDate;
    }

    @Override
    public String toString() {
        return "Training{" +
                "trainingId=" + trainingId +
                ", teacher='" + teacher + '\'' +
                ", issue='" + issue + '\'' +
                ", beginDate=" + beginDate +
                ", finishDate=" + finishDate +
                '}';
    }
}
