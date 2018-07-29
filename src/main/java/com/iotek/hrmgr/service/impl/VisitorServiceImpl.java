package com.iotek.hrmgr.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.iotek.hrmgr.mapper.VisitorMapper;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service("VisitorService")
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private VisitorMapper visitorMapper;

    int pageSize = 15;

    @Transactional
    @Cacheable(value = "visitorPage", key = "#currentPage")
    public PageInfo<Visitor> getAllVisitors(int currentPage) {
        PageHelper.startPage(currentPage, pageSize);
        List rs = visitorMapper.selectAllVisitors();
        //博学善思。。ljd: 其实一开始看到这段代码时候，我觉得应该是内存分页。其实插件对mybatis执行流程进行了增强，添加了limit以及count查询，属于物理分页
        PageInfo<Visitor> pageInfo = new PageInfo<Visitor>(rs);
        return pageInfo;
    }

    @Transactional
    @Cacheable(value = "visitor", key = "#visitor.toString()")
    public Visitor getVisitor(Visitor visitor) {
        Integer id = visitor.getVisitorId();
        if (id != null && id > 0) {
            Visitor visitorR = visitorMapper.selectVisitorById(id);
            if (visitorR!=null)
                return visitorR;
        }
        String email = null;
        if ((email = visitor.getEmail()) != null) {
            Visitor visitorR = visitorMapper.selectVisitorByEmail(visitor.getEmail());
            if (visitorR!=null)
                return visitorR;
        }
        String phone = null;
        if ((phone = visitor.getPhone()) != null) {
            Visitor visitorR = visitorMapper.selectVisitorByPhone(visitor.getPhone());
            if (visitorR!=null)
                return visitorR;
        }
        return visitorMapper.selectVisitorByName(visitor.getName());
    }
/*
    //老登录
    public Visitor login(Visitor visitor){
        Visitor visitorR = getVisitor(visitor);
        if (visitorR.getPassword().equals(visitor.getPassword())){
            return visitorR;
        }
        return null;
    }
*/

    //注册
    @Transactional
    @CacheEvict(value="visitorPage")
    public Visitor signUp(Visitor visitor) {
        Visitor visitorR = getVisitor(visitor);
        System.out.println(visitor!=null);
        if (visitorR != null) {
            return null;
        }
        visitorMapper.insertVisitor(visitor);
        return getVisitor(visitor);
    }

    //修改
    @Caching(evict = {
            @CacheEvict(value="visitor",key="#visitor.toString()"),
            @CacheEvict(value="visitorPage") })
    public void updateVisitor(Visitor visitor) {
        visitorMapper.updateVisitor(visitor);
    }

    //删除
    @Caching(evict = {
            @CacheEvict(value="visitor",key="#visitor.toString()"),
            @CacheEvict(value="visitorPage") })
    public void deleteVisitor(int id) {
        visitorMapper.deleteVisitorById(id);
    }
}