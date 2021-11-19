package com.example.xiaohu.modules.controller;


import com.example.xiaohu.common.Result;
import com.example.xiaohu.modules.entity.SysRole;
import com.example.xiaohu.modules.model.SysRoleModel;
import com.example.xiaohu.modules.service.SysRoleService;
import com.example.xiaohu.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sys")
@Api(value="角色管理",tags="角色管理")
public class RoleController {

    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation("新增角色")
    @RequestMapping(value="/createRole",method = RequestMethod.POST)
    public Result<String> createRole(@RequestBody SysRoleModel sysRoleModel){
        Result<String> result = new Result<>();

        String roleName = sysRoleModel.getRoleName();
        String description = sysRoleModel.getDescription();

        if(sysRoleService.getSysRoleByName(roleName)!=null){
            result.setCode(400);
            result.setMessage("角色已经存在!");
            return result;
        }
        SysRole sysRole = new SysRole();
        sysRole.setId(CommonUtils.getUid());
        sysRole.setRoleName(roleName);
        sysRole.setDescription(description);

        sysRoleService.save(sysRole);
        result.setMessage("角色创建成功！");
        result.setCode(200);
        result.setSuccess(true);
        return result;
    }

    @ApiOperation("显示已存在角色")
    @RequestMapping(value="/listRole",method = RequestMethod.GET)
    public List<SysRole> listRole(){
        List<SysRole> sysRoleList  = sysRoleService.list();
        return sysRoleList;
    }

    @ApiOperation("删除已存在角色")
    @RequestMapping(value="/deleteRole",method = RequestMethod.POST)
    public Result<String> deleteRole(@RequestBody SysRole sysRole){
        Result<String> result = new Result<>();
        String id = sysRole.getId();
        if(sysRoleService.removeById(id)) {
            result.setSuccess(true);
            result.setMessage("删除成功!");
            result.setCode(200);
        }else{
            result.setSuccess(false);
            result.setMessage("删除失败!");
            result.setCode(400);
        }
        return result;
    }
}
