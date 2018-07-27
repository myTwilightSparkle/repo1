package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Recruitment;

import java.util.List;

public interface RecruitmentMapper {
    public Recruitment selectRecruitmentById();

    public List<Recruitment> selectRecruitmentsByCondition(String arg);

    public List<Recruitment> selectAllRecruitments();

    public void insertRecruitment(Recruitment recruitment);

    //需要id
    public void updateRecruitment(Recruitment recruitment);

    public void deleteRecruitmentById(int id);
}