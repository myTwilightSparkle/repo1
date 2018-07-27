package com.iotek.hrmgr.service;

import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Recruitment;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface RecruitmentService {
    int pageSize = 15;

    public PageInfo<Recruitment> searchRecruitments(String arg, int currentPage);

    public void postRecruitment(Recruitment recruitment);

    public void removeRecruitment(Recruitment recruitment);

    public void updateRecruitment(Recruitment recruitment);
}
