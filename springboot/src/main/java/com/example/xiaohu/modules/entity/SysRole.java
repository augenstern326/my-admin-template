package com.example.xiaohu.modules.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("SYS_ROLE")
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String roleName;

    private String description;
}
