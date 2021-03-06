package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.Resume;
import com.iotek.hrmgr.entity.Visitor;

import java.util.List;

public interface ResumeService {
    public void createResume(Resume resume,String username);

    public void updateResume(Resume resume);

    public void deleteResume(int resumeId);

    public Resume findResumeById(int id);

    public List<Resume> findOnesResume(Visitor visitor, int currentPage);

    public List<Resume> searchResume(String arg, int currentPage);

    public List<Resume> getResumes();
}
