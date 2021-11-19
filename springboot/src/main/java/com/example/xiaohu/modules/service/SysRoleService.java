package com.example.xiaohu.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaohu.modules.entity.SysRole;


public interface SysRoleService extends IService<SysRole> {
    SysRole getSysRoleByName(String roleName);
}
