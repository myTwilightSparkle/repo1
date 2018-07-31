package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Attendence;
import com.iotek.hrmgr.entity.Visitor;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;

@Mapper
public interface AttendenceMapper {

    public Attendence selectAttendenceById(int id);

    public List<Attendence> selectAttendencesByVisitor(Visitor visitor);

    public List<Attendence> deleteAttendenceByDatetime(Date datetime);

    // order by date desc group by type
    public List<Attendence> selectAllAttendences();

    public void insertAttendence(Attendence attendence);

    public List<Attendence> selectAttendencesByDate(String date);
}
