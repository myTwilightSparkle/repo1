package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.entity.Attendence;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.AttendenceMapper;
import com.iotek.hrmgr.service.AttendenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;

@Service("AttendenceService")
public class AttendenceServiceImpl implements AttendenceService {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    private AttendenceMapper attendenceMapper;


    @Scheduled(cron = "0 0 0 1 * *")
    @Async
    @Transactional
    @Override
    public void clearRecord() {
        Date date = new Date();
        date.setMonth(date.getMonth() - 1);
        attendenceMapper.deleteAttendenceByDatetime(date);
    }

    @Transactional
    @Override
    public void add(Attendence attendence) {
        attendenceMapper.insertAttendence(attendence);
    }

    @Override
    public List<Attendence> getAllAttendences() {
        return attendenceMapper.selectAllAttendences();
    }

    @Override
    public List<Attendence> getOnesAttendences(Visitor visitor) {
        return attendenceMapper.selectAttendencesByVisitor(visitor);
    }

    @Override
    public boolean hasClockin(String name) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        List<Attendence> rs = attendenceMapper.selectAttendencesByDate(sdf.format(date));
        for (Attendence att : rs) {
            try {
                if (att.getVisitor().getName().equals(name) && (att.getType().equals("clockin") || att.getType().equals("late"))) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;

    }

    @Override
    public boolean hasClockout(String name) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy:MM:dd");
        List<Attendence> rs = attendenceMapper.selectAttendencesByDate(sdf.format(date));
        for (Attendence att : rs) {
            try {
                if (att.getVisitor().getName().equals(name) && (att.getType().equals("clockout") || att.getType().equals("early"))) {
                    return true;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return false;
    }
}
