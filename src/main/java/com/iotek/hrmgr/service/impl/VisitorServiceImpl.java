package com.iotek.hrmgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.VisitorMapper;
import com.iotek.hrmgr.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service("VisitorService")
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private VisitorMapper visitorMapper;

    int pageSize = 3;

    @Transactional
    @Override
    @Cacheable(value="visitorPage", key="#currentPage")
    public PageInfo<Visitor> getAllVisitors(int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List rs = visitorMapper.selectAllVisitors();
        //博学善思。。ljd: 其实一开始看到这段代码时候，我觉得应该是内存分页。其实插件对mybatis执行流程进行了增强，添加了limit以及count查询，属于物理分页
        PageInfo<Visitor> pageInfo = new PageInfo<>(rs);
        return pageInfo;
    }

    @Transactional
    @Cacheable(value="visitor", key="#visitor.toString()")
    @Override
    public Visitor getVisitor(Visitor visitor){
        int id = -1;
        if ((id = visitor.getVisitorId()) > 0){
            return visitorMapper.selectVisitorById(id);
        }
        String email = null;
        if ((email = visitor.getEmail()) != null){
            return visitorMapper.selectVisitorByEmail(visitor.getEmail());
        }
        return visitorMapper.selectVisitorByName(visitor.getName());
    }
}