package com.iotek.hrmgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Recruitment;
import com.iotek.hrmgr.mapper.PositionMapper;
import com.iotek.hrmgr.mapper.RecruitmentMapper;
import com.iotek.hrmgr.service.RecruitmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

@Service("RecruitmentService")
public class RecruitmentServiceImpl implements RecruitmentService {

    int pageSize = 15;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private RecruitmentMapper recruitmentMapper;

    @Transactional
    @Cacheable(value = "recruitPage", key = "#arg")
    @Override
    public PageInfo<Recruitment> searchRecruitments(String arg, int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List<Recruitment> rs = recruitmentMapper.selectRecruitmentsByCondition(arg);
        PageInfo<Recruitment> pageInfo = new PageInfo<Recruitment>(rs);
        pageInfo.setSize(pageSize);
        pageInfo.setPageNum(currentPage);
        System.out.println("page size: "+pageInfo.getPageSize()+",page number: "+pageInfo.getPageNum());
        return pageInfo;
    }

    @Override
    @Transactional
    @CacheEvict(value = "recruitPage")
    public void postRecruitment(Recruitment recruitment) {
        recruitmentMapper.insertRecruitment(recruitment);
    }

    @Override
    @Transactional
    @CacheEvict(value = "recruitPage")
    public void removeRecruitment(Recruitment recruitment) {
        recruitmentMapper.deleteRecruitmentById(recruitment.getRecruitId());
    }

    @Override
    @Transactional
    @CacheEvict(value = "recruitPage")
    public void updateRecruitment(Recruitment recruitment) {
        recruitmentMapper.updateRecruitment(recruitment);
    }
}
