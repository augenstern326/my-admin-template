package com.example.xiaohu.modules.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@TableName(value = "SYS_USER")
public class SysUser implements Serializable {

    private static final long serivalVersionUID = 1L;

    private String id;

    private String username;

    private String password;

    private String salt;

    private String status;

    private Date createTime;
}
