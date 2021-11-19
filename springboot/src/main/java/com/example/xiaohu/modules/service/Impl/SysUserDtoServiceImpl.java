package com.example.xiaohu.modules.service.Impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.xiaohu.modules.dto.SysUserDto;
import com.example.xiaohu.modules.mapper.SysUserDtoMapper;
import com.example.xiaohu.modules.service.SysUserDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserDtoServiceImpl extends ServiceImpl<SysUserDtoMapper, SysUserDto> implements SysUserDtoService {

    @Autowired
    SysUserDtoMapper sysUserDtoMapper;

    @Override
    public List<SysUserDto> listSysUserDto() {
        return sysUserDtoMapper.listSysUserDto();
    }
}
