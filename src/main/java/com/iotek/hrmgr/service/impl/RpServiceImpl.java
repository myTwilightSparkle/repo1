package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.entity.RwdPnt;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.RwdPntMapper;
import com.iotek.hrmgr.service.RpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service("RpService")
public class RpServiceImpl implements RpService {

    @Autowired
    private RwdPntMapper rwdPntMapper;

    @Transactional
    @Override
    public void addRP(RwdPnt rwdPnt) {
        rwdPntMapper.insertRp(rwdPnt);
    }

    @Transactional
    @Override
    public void setFine(int rpId, BigDecimal fine) {
        RwdPnt rp = rwdPntMapper.selectRPById(rpId);
        if (rp==null)return;
        rp.setFine(fine);
        rwdPntMapper.updateRwdPnt(rp);
    }

    @Transactional
    @Override
    public void handleRp(int rpId) {
        RwdPnt rp = rwdPntMapper.selectRPById(rpId);
        if (rp==null)return;
        rp.setHandled(true);
        rwdPntMapper.updateRwdPnt(rp);
    }

    @Override
    public List<RwdPnt> getOnesRecentRPs(Visitor visitor, Date date) {
        String year = ((Integer)date.getYear()).toString();
        String month = ((Integer)date.getMonth()).toString();
        return rwdPntMapper.selectRwdPntByVisitorDate(visitor,year,month);
    }

    @Override
    public List<RwdPnt> getAllRPs() {
        return rwdPntMapper.selectAllRPs();
    }

    @Override
    public List<RwdPnt> getUnhandledRPs() {
        return rwdPntMapper.selectNotHandled();
    }
}
