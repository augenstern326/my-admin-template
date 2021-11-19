package com.example.xiaohu.modules.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xiaohu.common.Result;
import com.example.xiaohu.config.shiro.Jwt.JwtUtil;
import com.example.xiaohu.modules.dto.SysUserDto;
import com.example.xiaohu.modules.entity.SysUser;
import com.example.xiaohu.modules.entity.SysUserRole;
import com.example.xiaohu.modules.model.SysUserRoleModel;
import com.example.xiaohu.modules.service.SysRoleService;
import com.example.xiaohu.modules.service.SysUserDtoService;
import com.example.xiaohu.modules.service.SysUserRoleService;
import com.example.xiaohu.modules.service.SysUserService;
import com.example.xiaohu.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/sys")
@Api(value="用户管理",tags="用户管理")
public class SysUserDtoController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserDtoService sysUserDtoService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation("获取用户列表")
    @RequestMapping(value="/listSysUserDto",method = RequestMethod.GET)
    public List<SysUserDto> listSysUserDto(){
        List<SysUserDto> sysUserDtoList = sysUserDtoService.listSysUserDto();
        return sysUserDtoList;
    }


    @ApiOperation("获取用户角色")
    @RequestMapping(value="/getRoleByToken",method = RequestMethod.GET)
    public String getRoleByToken(@RequestParam(name = "Token") String Token){
        String username = JwtUtil.getUsername(Token);
        String userId   = sysUserService.getSysUserByName(username).getId();
        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        String roleId   = sysUserRoleService.getOne(queryWrapper).getRoleId();
        String roleName = sysRoleService.getById(roleId).getRoleName();

        return roleName;
    }



    @ApiOperation("新增用户")
    @RequestMapping(value="/createSysUserDto",method = RequestMethod.POST)
    public Result<String> createSysUserDto(@RequestBody SysUserRoleModel sysUserRoleModel){
        Result<String> result = new Result<>();
        String username = sysUserRoleModel.getUsername();
        String password = sysUserRoleModel.getPassword();
        String status   = sysUserRoleModel.getStatus();
        String roleName = sysUserRoleModel.getRoleName();

        if(sysUserService.getSysUserByName(username)!=null){
            result.setSuccess(false);
            result.setMessage("用户已存在!");
            result.setCode(403);
            return result;
        }

        String userId   = CommonUtils.getUid();
        String roleId   = sysRoleService.getSysRoleByName(roleName).getId();

        String salt = CommonUtils.getSalt();

        SysUser sysUser = new SysUser();
        SysUserRole sysUserRole = new SysUserRole();

        sysUser.setId(userId);
        sysUser.setUsername(username);
        sysUser.setSalt(salt);
        sysUser.setStatus(status);
        sysUser.setPassword(new Md5Hash(password,salt).toHex());
        sysUser.setCreateTime(new DateTime().toDate());

        sysUserService.save(sysUser);

        sysUserRole.setId(CommonUtils.getUid());
        sysUserRole.setUserId(userId);
        sysUserRole.setRoleId(roleId);
        sysUserRoleService.save(sysUserRole);

        result.setMessage("创建成功!");
        result.setSuccess(true);
        result.setCode(200);
        return result;
    }



    @ApiOperation("修改用户状态")
    @RequestMapping(value="/editSysUser",method=RequestMethod.POST)
    public Result<String> editSysUser(@RequestBody SysUser sysUser){
        Result<String> result = new Result<>();
        if(sysUser.getUsername().equals("admin")){
            result.setCode(503);
            result.setSuccess(false);
            result.setMessage("权限不够!");
            return result;
        }
        if(sysUserService.updateById(sysUser)){
            result.setCode(200);
            result.setSuccess(true);
            result.setMessage("修改成功!");
        }else{
            result.setCode(403);
            result.setSuccess(false);
            result.setMessage("修改失败!");
        }
        return result;
    }

    @ApiOperation("修改用户角色")
    @RequestMapping(value="/editSysUserRole",method=RequestMethod.POST)
    public Result<String> editSysUserRole(@RequestBody SysUserDto sysUserDto){
        Result<String> result = new Result<>();
        SysUserRole sysUserRole = new SysUserRole();

        if(sysUserDto.getUsername().equals("admin")){
            result.setCode(503);
            result.setSuccess(false);
            result.setMessage("权限不够!");
            return result;
        }

        String userId = sysUserService.getSysUserByName(sysUserDto.getUsername()).getId();
        String roleId = sysRoleService.getSysRoleByName(sysUserDto.getRoleName()).getId();



        QueryWrapper<SysUserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        SysUserRole originSysUserRole     = sysUserRoleService.getOne(queryWrapper);
        if(originSysUserRole==null){
            result.setCode(403);
            result.setSuccess(false);
            result.setMessage("修改失败!");
        }else{
            String id = originSysUserRole.getId();
            sysUserRole.setId(id);
            sysUserRole.setUserId(userId);
            sysUserRole.setRoleId(roleId);
            sysUserRoleService.updateById(sysUserRole);
            result.setCode(200);
            result.setSuccess(true);
            result.setMessage("修改成功!");
        }

        return result;
    }



    @ApiOperation("删除用户")
    @RequestMapping(value="/deleteSysUserDto",method = RequestMethod.POST)
    public Result<String> deleteSysUser(@RequestBody SysUser sysUser){
        Result<String> result = new Result<>();
        String id = sysUser.getId();
        if(sysUser.getUsername().equals("admin")){
            result.setCode(503);
            result.setSuccess(false);
            result.setMessage("权限不够!");
            return result;
        }
        if(sysUserService.removeById(id)){
            result.setCode(200);
            result.setSuccess(true);
            result.setMessage("删除成功!");
        }else{
            result.setCode(400);
            result.setSuccess(false);
            result.setMessage("删除失败!");
        }
        return result;
    }


}
