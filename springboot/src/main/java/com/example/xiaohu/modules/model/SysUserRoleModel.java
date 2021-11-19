package com.example.xiaohu.modules.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户角色对象",description = "用户角色对象")
public class SysUserRoleModel {
    @ApiModelProperty(value="用户名称")
    private String username;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="状态")
    private String status;

    @ApiModelProperty(value="角色名称")
    private String roleName;
}