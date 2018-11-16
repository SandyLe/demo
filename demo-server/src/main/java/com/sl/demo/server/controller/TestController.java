package com.sl.demo.server.controller;

import com.sl.demo.server.service.UserService;
import com.sl.domain.entity.Position;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Api(value = "TestController", description = "测试接口")
public class TestController {

    @Autowired
    private UserService userService;
    @ApiOperation(value = "测试",notes = "Test")
    @GetMapping(value = {"/test/test"})
    public String test() {
        User user = new User();
        user.setName("Sandy");
        user.setCreateDate(new Date());
        user.setCreateUserId(1l);
        user.setUpdateDate(new Date());
        user.setUpdateUserId(1l);
        user.setDescription("test");

        Position position = new Position();
        position.setName("总经理");
        userService.save(user);
        return "hello word1!" + user.getName() + position.getName();
    }
}
