package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
@Mapper
public interface VisitorMapper {

    public Visitor selectVisitorById(int id);

    public Visitor selectVisitorByName(String name);

    public Visitor selectVisitorByEmail(String email);

    public List<Visitor> selectAllVisitors();

    public void insertVisitor(Visitor visitor);

    // 根据id修改
    public void updateVisitor(Visitor visitor);

    public void deleteVisitorById(int id);
} 