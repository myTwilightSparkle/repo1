package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Resume;
import com.iotek.hrmgr.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ResumeMapper {
    public Resume selectResumeById(int id);

    public List<Resume> selectResumesByPositionstr(String positionstr);

    public List<Resume> selectResumesByVisitor(Visitor visitor);

    public void insertResume(Resume resume);

    //需要有id
    public void updateResume(Resume resume);

    public void deleteResumeById(int id);

    public List<Resume> selectAllResumes();
}
