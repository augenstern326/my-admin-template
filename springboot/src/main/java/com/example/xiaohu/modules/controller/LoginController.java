package com.example.xiaohu.modules.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.xiaohu.common.Result;
import com.example.xiaohu.config.shiro.Jwt.JwtUtil;
import com.example.xiaohu.modules.entity.SysUser;
import com.example.xiaohu.modules.model.SysLoginModel;
import com.example.xiaohu.modules.service.SysUserService;
import com.example.xiaohu.utils.CommonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/sys")
@Api(value="用户登录",tags="用户登录")
public class LoginController {

    @Autowired
    SysUserService sysUserService;

    @ApiOperation("登录")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result<Map<String,String>> login(@RequestBody SysLoginModel sysLoginModel){
        Result<Map<String,String>> result = new Result<>();

        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();

        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        sysUserQueryWrapper.eq("status","0");
        sysUserQueryWrapper.eq("username",username);
        SysUser sysUser = sysUserService.getOne(sysUserQueryWrapper);

        if(sysUser!=null){
            if(sysUser.getPassword().equals(new Md5Hash(password,sysUser.getSalt()).toHex())){
                HashMap<String,String> data = new HashMap<>();
                data.put("token", JwtUtil.sign(username,password));
                data.put("username",username);
                result.setData(data);
                result.setMessage("登录成功!");
            }else{
                result.setMessage("密码不正确!");
                result.setSuccess(false);
                result.setCode(401);
            }
        } else{
            result.setMessage("用户不存在或已被冻结!");
            result.setSuccess(false);
            result.setCode(401);
        }
        return result;
    }



    @ApiOperation("登出")
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public String logout(){
        String result = "Hello World!";
        return result;
    }

    @ApiOperation("注册")
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public Result<Map<String,String>> register(@RequestBody SysLoginModel sysLoginModel){
        Result<Map<String,String>> result = new Result<>();
        String username = sysLoginModel.getUsername();
        String password = sysLoginModel.getPassword();

        if(sysUserService.getSysUserByName(username)!=null) {
            result.setMessage("用户名已存在!");
            result.setCode(400);
            result.setSuccess(false);
            return result;
        }

        String salt = CommonUtils.getSalt();
        SysUser sysUser = new SysUser();
        sysUser.setId(CommonUtils.getUid());
        sysUser.setUsername(username);
        sysUser.setSalt(salt);
        sysUser.setPassword(new Md5Hash(password,salt).toHex());

        sysUserService.save(sysUser);
        result.setMessage("注册成功!");
        result.setCode(200);
        result.setSuccess(true);
        return result;
    }
}
