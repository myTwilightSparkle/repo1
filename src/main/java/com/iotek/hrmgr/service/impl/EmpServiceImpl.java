package com.iotek.hrmgr.service.impl;

import com.iotek.hrmgr.entity.Dept;
import com.iotek.hrmgr.entity.Employee;
import com.iotek.hrmgr.entity.Visitor;
import com.iotek.hrmgr.mapper.EmployeeMapper;
import com.iotek.hrmgr.mapper.VisitorMapper;
import com.iotek.hrmgr.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("EmpService")
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private VisitorMapper visitorMapper;

    @Override
    public Employee getEmployeeById(int empId) {
        return employeeMapper.selectEmployeeById(empId);
    }

    @Override
    public Visitor getVisitorByEmployee(Employee emp) {
        return visitorMapper.selectVisitorByEmail(emp.getEmail());
    }

    @Override
    public List<Employee> getEmployeesInDept(Dept dept) {
        return employeeMapper.selectEmployeesByDept(dept);
    }

    @Override
    public List<Visitor> getEmployeesInDeptAsVisitors(Dept dept) {
        List<Employee> rs = employeeMapper.selectEmployeesByDept(dept);
        List<Visitor> rs1 = new ArrayList<Visitor>();
        for (Employee emp:rs){
            rs1.add(visitorMapper.selectVisitorByEmail(emp.getEmail()));
        }
        return rs1;
    }


    @Override
    public List<Employee> getAllEmployees() {
        return employeeMapper.selectAllEmployees();
    }

    @Override
    public List<Visitor> getAllEmployeesAsVisitors() {
        List<Employee> rs = employeeMapper.selectAllEmployees();
        List<Visitor> rs1 = new ArrayList<Visitor>();
        for (Employee emp:rs){
            rs1.add(visitorMapper.selectVisitorByEmail(emp.getEmail()));
        }
        return rs1;
    }

    @Override
    public List<Employee> selectHistoricalEmployees() {
        return employeeMapper.selectHistoricalEmployees();
    }
}
