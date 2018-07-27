package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Recruitment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecruitmentMapper {
    public Recruitment selectRecruitmentById(int id);

    public List<Recruitment> selectRecruitmentsByCondition(String arg);

    public List<Recruitment> selectAllRecruitments();

    public List<Recruitment> selectHistoricalRecruitments();

    public void insertRecruitment(Recruitment recruitment);

    //需要id
    public void updateRecruitment(Recruitment recruitment);

    public void deleteRecruitmentById(int id);
}