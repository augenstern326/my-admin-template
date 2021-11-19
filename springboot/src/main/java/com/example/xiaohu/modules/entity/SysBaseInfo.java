package com.example.xiaohu.modules.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
//@TableName(value = "SYSTEM_INFO")
public class SysBaseInfo {

    //host名称
    private String hostName;
    //系统名称
    private String osName;
    //系统版本信息
    private String version;
    //核数
    private int cpuCoreNum;
    //内存大小
    private double memorySize;
    //外盘大小
    private double storageSize;

    //主机状态，1正常，2下线
    private String state;
    //创建时间
    private String createTime;

}
