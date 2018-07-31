package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Dept;
import com.iotek.hrmgr.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/*
* 这个接口有一坨方法
* */
@Mapper
public interface EmployeeMapper {
    /*
    根据id查询员工
     */
    public Employee selectEmployeeById(int id);

    /*
    根据name查询员工
     */
    public Employee selectEmployeeByRealname(String realname);

    /*
    查询部门没走员工
     */
    public List<Employee> selectEmployeesByDept(Dept dept);

    /*
    查询全部没走员工
     */
    public List<Employee> selectAllEmployees();

    /*
    查询全部员工
     */
    public List<Employee> selectHistoricalEmployees();

    public void insertEmployee(Employee employee);

    /*
    有id的Employee
     */
    public void updateEmployee(Employee employee);

    public void deleteEmployeeById(int id);

} 