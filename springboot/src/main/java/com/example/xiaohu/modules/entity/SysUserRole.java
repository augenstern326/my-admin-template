package com.example.xiaohu.modules.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
@TableName(value = "SYS_USER_ROLE")
public class SysUserRole implements Serializable {

    private static final long serivalVersionUID = 1L;

    private String id;

    private String userId;

    private String roleId;
}
