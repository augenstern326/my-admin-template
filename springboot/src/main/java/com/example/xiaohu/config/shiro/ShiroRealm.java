package com.example.xiaohu.config.shiro;

import com.example.xiaohu.config.shiro.Jwt.JwtToken;
import com.example.xiaohu.config.shiro.Jwt.JwtUtil;
import com.example.xiaohu.modules.entity.SysUser;
import com.example.xiaohu.modules.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Slf4j
@Configuration
public class ShiroRealm extends AuthorizingRealm {


    @Lazy
    @Autowired
    SysUserService sysUserService;


    //使用Jwt替代原生Token
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("\n===============Shiro身份认证开始============\n");
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtil.getUsername(token);
        System.out.println("===============username:"+username+"==================");

        SysUser sysUser = sysUserService.getSysUserByName(username);
        if(sysUser!=null){
            if(JwtUtil.verify(token,username,sysUser.getPassword())){
                return new SimpleAuthenticationInfo(token,token,this.getName());
            }
        }
        return null;
    }
}
