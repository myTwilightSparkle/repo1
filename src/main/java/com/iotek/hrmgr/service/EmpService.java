package com.iotek.hrmgr.service;

import com.iotek.hrmgr.entity.Dept;
import com.iotek.hrmgr.entity.Employee;
import com.iotek.hrmgr.entity.Visitor;

import java.util.List;

public interface EmpService {

    public Employee getEmployeeById(int empId);

    public Visitor getVisitorByEmployee(Employee emp);

    public List<Employee> getEmployeesInDept(Dept dept);

    public List<Visitor> getEmployeesInDeptAsVisitors(Dept dept);

    public List<Employee> getAllEmployees();

    public List<Visitor> getAllEmployeesAsVisitors();

    public List<Employee> selectHistoricalEmployees();

}
