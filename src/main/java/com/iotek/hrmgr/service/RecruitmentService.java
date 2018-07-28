package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.Recruitment;

import java.util.List;


public interface RecruitmentService {
    int pageSize = 15;

    public List<Recruitment> searchRecruitments(String arg, int currentPage);

    public void postRecruitment(Recruitment recruitment);

    public void removeRecruitment(Recruitment recruitment);

    public void updateRecruitment(Recruitment recruitment);
}
