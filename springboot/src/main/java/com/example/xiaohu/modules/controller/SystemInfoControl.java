package com.example.xiaohu.modules.controller;


import com.example.xiaohu.common.Result;
import com.example.xiaohu.modules.entity.SysBaseInfo;
import com.example.xiaohu.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import oshi.SystemInfo;
import oshi.hardware.*;
import oshi.software.os.OperatingSystem;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

@RestController
@RequestMapping("/sys")
@Api(value="系统信息",tags="系统信息")
public class SystemInfoControl {

    @ApiOperation("获取系统信息")
    @RequestMapping(value="/systemInfo",method = RequestMethod.GET)
    public Result<SysBaseInfo> getSystemInfo(HttpServletRequest req){
        Result<SysBaseInfo> result = new Result<>();
        SysBaseInfo sysInfo = new SysBaseInfo();
        SystemInfo systemInfo = new SystemInfo();
        Double storageSize = 0.0;

        HardwareAbstractionLayer hardwareAbstractionLayer = systemInfo.getHardware();
        OperatingSystem operatingSystem = systemInfo.getOperatingSystem();
        ComputerSystem computerSystem = hardwareAbstractionLayer.getComputerSystem();
        Properties props = System.getProperties();


        for(HWDiskStore hwDiskStore:hardwareAbstractionLayer.getDiskStores()){
            for(HWPartition hwPartition:hwDiskStore.getPartitions()){
                storageSize+=hwPartition.getSize()/Math.pow(1024.0,3);
            }
        }

        sysInfo.setOsName(props.getProperty("os.name"));

        sysInfo.setMemorySize(CommonUtils.round(2,hardwareAbstractionLayer.getMemory().getTotal()/Math.pow(1024.0,3)));
        sysInfo.setHostName(operatingSystem.getNetworkParams().getHostName());
        sysInfo.setStorageSize(CommonUtils.round(2,storageSize));
        sysInfo.setVersion(operatingSystem.getVersionInfo().getVersion());
        sysInfo.setCpuCoreNum(hardwareAbstractionLayer.getProcessor().getLogicalProcessorCount());
        sysInfo.setCreateTime(new DateTime().toString("yyyy-MM-dd HH:mm:ss"));

        result.setData(sysInfo);
        return result;
    }

}
