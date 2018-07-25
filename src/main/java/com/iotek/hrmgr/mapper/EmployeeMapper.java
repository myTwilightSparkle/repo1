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
    根据id查询没走员工
     */
    public Employee selectEmployeeById(int id);

    /*
    根据name查询没走员工
     */
    public Employee selectEmployeeByName(String name);

    /*
    根据email查询没走员工
     */
    public Employee selectEmployeeByEmail(String email);

    /*
    查询部门没走员工
    分页
     */
    //public List<Employee> selectEmployeesByDept(Dept dept, int currentPage);

    /*
    查询全部没走员工
    分页
     */
    public List<Employee> selectAllEmployees(int currentPage);

    /*
    根据id查询已走员工
     */
    public Employee selectHistoricalEmployeeById(int id);

    /*
    根据name查询已走员工
     */
    public Employee selectHistoricalEmployeeByName(String name);

    /*
    根据email查询已走员工
     */
    public Employee selectHistoricalEmployeeByEmail(String email);

    /*
    查询部门已走员工
    分页
     */
    public List<Employee> selectHistoricalEmployeesByDept(Dept dept, int currentPage);

    /*
    查询全部已走员工
    分页
     */
    public List<Employee> selectHistoricalEmployees();



    public void insertEmployee(Employee employee);

    /*
    有id的Employee
     */
    public void updateEmployee(Employee employee);

    public void deleteEmployeeById(int id);

} 