package com.example.xiaohu.modules.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysUserDto implements Serializable {

    private static final long serivalVersionUID = 1L;

    private String id;

    private String username;

    private String status;

    private Date createTime;

    private String roleName;

    private String description;

}
