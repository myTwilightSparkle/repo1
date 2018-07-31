package com.iotek.hrmgr.aspect;

import com.iotek.hrmgr.entity.RwdPnt;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.service.AttendenceService;
import com.iotek.hrmgr.service.EmpService;
import com.iotek.hrmgr.service.RpService;
import com.iotek.hrmgr.service.VisitorService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 生成待处理的奖惩记录
 * 这个切面负责起诉
 * (handled=0)
 * 管理员通过RpController来判
 * (handled=1)
 * 工资结算流程只看已经判的记录
 * (handled==1)
 */
@Aspect
@Component
public class RPAspect {

    @Autowired
    private RpService rpService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private EmpService empService;

    @Autowired
    private AttendenceService attendenceService;

    /*
    迟到惩罚
     */
    @Async
    @Around("execution(* com.iotek.hrmgr.controller.AttendenceController.late())")
    public void latePnt(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");

        //生成记录
            //拿用户
            String username = (String)SecurityUtils.getSubject().getPrincipal();
            Visitor visitor = new Visitor();
            visitor.setName(username);
            visitor=visitorService.getVisitor(visitor);
            RwdPnt rp = new RwdPnt();
            rp.setVisitor(visitor);
            rp.setDate(new Date());
            rp.setCause("迟到");

        //调用RpService的方法
        rpService.addRP(rp);
    }

    /*
    早退惩罚
     */
    @Async
    @Around("execution(* com.iotek.hrmgr.controller.AttendenceController.early())")
    public void earlyPnt(ProceedingJoinPoint point) throws Throwable {
        System.out.println("@Around：执行目标方法之前...");
        //访问目标方法的参数：
        Object[] args = point.getArgs();
        //用改变后的参数执行目标方法
        Object returnValue = point.proceed(args);
        System.out.println("@Around：执行目标方法之后...");
        //生成记录
        //拿用户
        String username = (String)SecurityUtils.getSubject().getPrincipal();
        Visitor visitor = new Visitor();
        visitor.setName(username);
        visitor=visitorService.getVisitor(visitor);
        RwdPnt rp = new RwdPnt();
        rp.setVisitor(visitor);
        rp.setDate(new Date());
        rp.setCause("早退");

        //调用RpService的方法
        rpService.addRP(rp);
    }


    /*
    找缺席
    这不是个切面上的方法
     */
    @Scheduled(cron="0,0,17,*")
    public void absence(){
        List<Visitor> rs = empService.getAllEmployeesAsVisitors();
        for (Visitor v:rs){
            if (attendenceService.hasClockin(v.getName())){
                RwdPnt rp = new RwdPnt();
                rp.setVisitor(v);
                rp.setDate(new Date());
                rp.setCause("缺勤");
                rpService.addRP(rp);
            }
        }
    }
}
