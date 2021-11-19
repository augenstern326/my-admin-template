package com.example.xiaohu.modules.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaohu.modules.entity.SysUser;
import com.example.xiaohu.modules.mapper.SysUserMapper;
import com.example.xiaohu.modules.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public SysUser getSysUserByName(String username) {
        return sysUserMapper.getSysUserByName(username);
    }
}
