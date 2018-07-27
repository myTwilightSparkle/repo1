package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Dept;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DeptMapper {
    public Dept selectDeptById(int id);

    public Dept selectDeptByName(String name);

    public void insertDept(Dept dept);

    //需要id
    public void updateDept(Dept dept);

    public void deleteDeptById(int id);
} 