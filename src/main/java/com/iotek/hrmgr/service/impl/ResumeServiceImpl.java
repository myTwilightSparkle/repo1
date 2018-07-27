package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.entity.Resume;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.ResumeMapper;
import com.iotek.hrmgr.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("ResumeService")
public class ResumeServiceImpl implements ResumeService {

    @Autowired
    private ResumeMapper resumeMapper;

    @Override
    @Transactional
    public void createResume(Resume resume) {
        resumeMapper.insertResume(resume);
    }

    @Override
    @Transactional
    public void updateResume(Resume resume) {
        resumeMapper.updateResume(resume);
    }

    @Override
    @Transactional
    public void deleteResume(Resume resume) {
        resumeMapper.deleteResumeById(resume.getResumeId());
    }

    @Override
    @Transactional
    public Resume findResumeById(int id) {
        return resumeMapper.selectResumeById(id);
    }

    @Override
    @Transactional
    public List<Resume> findOnesResume(Visitor visitor) {
        return resumeMapper.selectResumesByVisitor(visitor);
    }

    @Override
    @Transactional
    public List<Resume> searchResume(String arg) {
        return resumeMapper.selectResumesByPositionstr(arg);
    }
}
