package com.example.xiaohu.modules.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaohu.modules.entity.SysRole;
import com.example.xiaohu.modules.mapper.SysRoleMapper;
import com.example.xiaohu.modules.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Override
    public SysRole getSysRoleByName(String roleName) {
        return sysRoleMapper.getSysRoleByName(roleName);
    }
}
