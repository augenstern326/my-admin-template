package com.example.xiaohu.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaohu.modules.entity.SysUser;

public interface SysUserService extends IService<SysUser> {
    SysUser getSysUserByName(String username);
}
