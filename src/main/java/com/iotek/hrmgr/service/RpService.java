package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.RwdPnt;
import com.iotek.hrmgr.entity.Visitor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface RpService {

    public void addRP(RwdPnt rwdPnt);

    /*
    设置罚金
     */
    public void setFine(int rpId, BigDecimal fine);

    /*
    只把handle的值改为1
    不做别的
    另有工资结算的流程
    handle为0的记录不会加入结算
     */
    public void handleRp(int rpId);

    public List<RwdPnt> getOnesRecentRPs(Visitor visitor, Date date);

    public List<RwdPnt> getAllRPs();

    public List<RwdPnt> getUnhandledRPs();

}
