package com.iotek.hrmgr.service;

import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Visitor;

import java.util.List;

public interface VisitorService {

    public PageInfo<Visitor> getAllVisitors(int currentPage);

    public Visitor getVisitor(Visitor visitor);

} 