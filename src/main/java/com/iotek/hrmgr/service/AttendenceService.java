package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.Attendence;
import com.iotek.hrmgr.entity.Visitor;

import java.util.List;

public interface AttendenceService {

    public void clearRecord();

    public void add(Attendence attendence);

    public List<Attendence> getAllAttendences();

    public List<Attendence> getOnesAttendences(Visitor visitor);

    public boolean hasClockin(String name);

    public boolean hasClockout(String name);
}
