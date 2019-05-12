package com.sl.demo.server.controller;


import com.sl.domain.dto.sys.UserLoginDto;
import com.sl.domain.dto.util.Result;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@RestController
@Api(value = "LoginController", description = "登录接口")
public class LoginController {

    @PostMapping(value = {"/login"})
    public Result login(@RequestBody UserLoginDto userLoginDto){
        UsernamePasswordToken token = new UsernamePasswordToken(userLoginDto.getUsername(),userLoginDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        return new Result();
    }
}
