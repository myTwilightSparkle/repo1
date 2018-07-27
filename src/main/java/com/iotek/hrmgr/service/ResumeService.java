package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.Resume;
import com.iotek.hrmgr.entity.Visitor;

import java.util.List;

public interface ResumeService {
    public void createResume(Resume resume);

    public void updateResume(Resume resume);

    public void deleteResume(Resume resume);

    public Resume findResumeById(int id);

    public List<Resume> findOnesResume(Visitor visitor);

    public List<Resume> searchResume(String arg);
}
