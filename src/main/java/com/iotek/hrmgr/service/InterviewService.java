package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.Interview;
import com.iotek.hrmgr.entity.Visitor;

import java.util.Date;
import java.util.List;

public interface InterviewService {
    public Interview findInterviewById(int id);

    public List<Interview> findOnesInterviews(Visitor visitor);

    public List<Interview> getAllInterviews();

    public void offerInterview(Interview interview);

    public void acceptInterview(int interviewId);

    public void denyInterview(int interviewId);

    public void rearrangeInterview(int interviewId, Date time, String interviewer);
}
