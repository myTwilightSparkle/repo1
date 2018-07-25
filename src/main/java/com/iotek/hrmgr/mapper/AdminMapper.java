package com.iotek.hrmgr.mapper;

import com.iotek.hrmgr.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {

    public Admin selectAdminById();

    public List<Admin> selectAllAdmins();

    public void insertAdmin(Admin admin);

    public void updateAdmin(Admin admin);

    public void deleteAdminById(Admin admin);
} 