package com.example.xiaohu.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.xiaohu.modules.dto.SysUserDto;
import com.example.xiaohu.modules.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserDtoMapper extends BaseMapper<SysUserDto> {

    List<SysUserDto> listSysUserDto();
}
