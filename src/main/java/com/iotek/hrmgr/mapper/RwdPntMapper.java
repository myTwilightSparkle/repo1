package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.RwdPnt;
import com.iotek.hrmgr.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RwdPntMapper {
    public RwdPnt selectRPById(int id);

    public List<RwdPnt> selectNotHandled();

    public List<RwdPnt> selectRwdPntByVisitor(Visitor visitor);

    public List<RwdPnt> selectAllRPs();

    public void insertRp(RwdPnt rwdPnt);

    public void updateRwdPnt(RwdPnt rwdPnt);

    //某人某年某月的奖惩记录
    public List<RwdPnt> selectRwdPntByVisitorDate(@Param("visitor") Visitor visitor, @Param("year") String year, @Param("month") String month);
}
