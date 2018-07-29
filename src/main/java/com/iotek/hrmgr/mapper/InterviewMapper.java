package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Interview;
import com.iotek.hrmgr.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InterviewMapper {

    public Interview selectInterviewById(int interviewId);

    public List<Interview> selectInterviewsByVisitor(Visitor visitor);

    public List<Interview> selectAllInterviews();

    public void insertInterview(Interview interview);

    public void updateInterview(Interview interview);

    public void deleteInterviewById(int id);

}
