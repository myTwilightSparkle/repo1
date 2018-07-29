package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.entity.Interview;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.InterviewMapper;
import com.iotek.hrmgr.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("InterviewService")
public class InterviewServiceImpl implements InterviewService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private InterviewMapper interviewMapper;

    @Override
    public Interview findInterviewById(int id) {
        return interviewMapper.selectInterviewById(id);
    }

    @Override
    public List<Interview> findOnesInterviews(Visitor visitor) {
        return interviewMapper.selectInterviewsByVisitor(visitor);
    }

    @Override
    public List<Interview> getAllInterviews() {
        return interviewMapper.selectAllInterviews();
    }

    @Override
    public void offerInterview(Interview interview) {
        interviewMapper.insertInterview(interview);
    }

    @Override
    public void acceptInterview(int interviewId) {
        Interview interview = interviewMapper.selectInterviewById(interviewId);
        interview.setResult("通过");
        interviewMapper.updateInterview(interview);
    }

    @Override
    public void denyInterview(int interviewId) {
        Interview interview = interviewMapper.selectInterviewById(interviewId);
        interview.setResult("未通过");
        interviewMapper.updateInterview(interview);
    }

    @Override
    public void rearrangeInterview(int interviewId, Date time, String interviewer) {
        Interview interview = interviewMapper.selectInterviewById(interviewId);
        interview.setTime(time);
        interview.setInterviewer(interviewer);
        interviewMapper.updateInterview(interview);
    }
}
