package com.sl.demo.server.controller;


import com.sl.demo.server.service.UserService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.sys.UserLoginDto;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "LoginController", description = "登录接口")
public class LoginController {

    @Autowired
    private UserService userService;
    @PostMapping(value = {"/login"})
    public Result login(@RequestBody UserLoginDto userLoginDto){
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginDto.getUsername(),userLoginDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        User user = userService.findByName(userLoginDto.getUsername());
        LoginUtils.getSession().setAttribute(LoginUtils.LOGIN_USER, user);
        return new Result();
    }
}
