package com.example.xiaohu.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaohu.modules.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
    SysRole getSysRoleByName(String roleName);
}
