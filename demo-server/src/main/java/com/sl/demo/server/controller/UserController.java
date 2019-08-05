package com.sl.demo.server.controller;

import com.sl.demo.server.service.UserService;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UserController", description = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/user/save"})
    public Long save(@RequestBody User user){
        userService.save(user);
        return user.getId();
    }
}
