package com.sl.demo.server.controller;


import com.sl.demo.core.utils.HMACSHA256;
import com.sl.demo.server.service.UserService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.demo.server.util.PasswordHelper;
import com.sl.domain.dto.sys.LoginUser;
import com.sl.domain.dto.sys.UserLoginDto;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;


@RestController
@Api(value = "LoginController", description = "登录接口")
public class LoginController {

    @Autowired
    private UserService userService;
    @PostMapping(value = {"/login"})
    public Result<LoginUser> login(@RequestBody UserLoginDto userLoginDto) throws Exception{
        User user = userService.findByName(userLoginDto.getUsername());
        String password = HMACSHA256.decrypt(userLoginDto.getPassword(), "Password!");
        password = PasswordHelper.encryptTryPassword(user,password);
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginDto.getUsername(),password);
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(loginUser, user);
        loginUser.setToken(SecurityUtils.getSubject().getSession().getId().toString());
        LoginUtils.getSession().setAttribute(LoginUtils.LOGIN_USER, loginUser);
        return new Result<LoginUser>(loginUser);
    }

    @GetMapping(value = {"/getUserInfo"})
    public Result<LoginUser> getUserInfo(@RequestParam String token) throws Exception{
        LoginUser loginUser =(LoginUser)LoginUtils.getSession().getAttribute(LoginUtils.LOGIN_USER);
        if(null == loginUser || !token.equals(loginUser.getToken())){
            return new Result<>();
        }
        return new Result<LoginUser>(loginUser);
    }
}
