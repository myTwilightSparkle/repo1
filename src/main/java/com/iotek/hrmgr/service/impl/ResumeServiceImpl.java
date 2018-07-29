package com.iotek.hrmgr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Resume;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.ResumeMapper;
import com.iotek.hrmgr.mapper.VisitorMapper;
import com.iotek.hrmgr.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {

    int pageSize = 15;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private ResumeMapper resumeMapper;

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private VisitorMapper visitorMapper;

    @Override
    @Transactional
    public void createResume(Resume resume, String username) {
        Visitor visitor = visitorMapper.selectVisitorByName(username);
        resume.setVisitor(visitor);
        if(resume.getPosition()==null){
            resume.setPosition("空");
        }
        if(resume.getContent()==null){
            resume.setContent("空");
        }
        System.out.println(resume);
        resumeMapper.insertResume(resume);
    }

    @Override
    @Transactional
    public void updateResume(Resume resume) {
        resumeMapper.updateResume(resume);
    }

    @Override
    @Transactional
    public void deleteResume(int resumeId) {
        resumeMapper.deleteResumeById(resumeId);
    }

    @Override
    @Transactional
    public Resume findResumeById(int id) {
        return resumeMapper.selectResumeById(id);
    }

    @Override
    @Transactional
    public List<Resume> findOnesResume(Visitor visitor, int currentPage) {
        PageHelper.offsetPage(currentPage, pageSize);
        List rs = resumeMapper.selectResumesByVisitor(visitor);
        return rs;
    }

    @Override
    @Transactional
    public List<Resume> searchResume(String arg, int currentPage) {
        PageHelper.offsetPage(currentPage, pageSize);
        List rs = resumeMapper.selectResumesByPositionstr(arg);
        return rs;
    }

    @Override
    public List<Resume> getResumes() {
        return resumeMapper.selectAllResumes();
    }
}
