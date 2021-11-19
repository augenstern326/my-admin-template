package com.example.xiaohu.modules.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="角色对象",description = "角色对象")
public class SysRoleModel {
    @ApiModelProperty(value="角色名称")
    private String roleName;
    @ApiModelProperty(value="角色描述")
    private String description;
}