package com.example.xiaohu.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaohu.modules.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    SysUser getSysUserByName(String username);
}
