package com.example.xiaohu.modules.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.xiaohu.modules.dto.SysUserDto;

import java.util.List;


public interface SysUserDtoService extends IService<SysUserDto> {
    List<SysUserDto> listSysUserDto();
}
