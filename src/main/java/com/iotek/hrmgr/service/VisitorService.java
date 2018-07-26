package com.iotek.hrmgr.service;

import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Visitor;

public interface VisitorService {

    public PageInfo<Visitor> getAllVisitors(int currentPage);

    public Visitor getVisitor(Visitor visitor);

    /*public Visitor login(Visitor visitor);*/

    public Visitor signUp(Visitor visitor);

    public void updateVisitor(Visitor visitor);

    public void deleteVisitor(int id);

}