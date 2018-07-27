package com.iotek.hrmgr.entity;
/*
* 简历
* */
public class Resume {
    private int resumeId;
    private Visitor visitor;
    private String objective;
    private String content;

    public Resume() {
    }

    public int getResumeId() {
        return resumeId;
    }

    public void setResumeId(int resumeId) {
        this.resumeId = resumeId;
    }

    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Resume{" +
                "resumeId=" + resumeId +
                ", visitor=" + visitor +
                ", objective=" + objective +
                ", content='" + content + '\'' +
                '}';
    }
}
