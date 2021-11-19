package com.example.xiaohu.modules.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="登录对象",description = "登录对象")
public class SysLoginModel {
    @ApiModelProperty(value="账号")
    private String username;
    @ApiModelProperty(value="密码")
    private String password;
}
