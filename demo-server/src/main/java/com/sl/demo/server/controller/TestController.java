package com.sl.demo.server.controller;

import com.sl.domain.entity.Position;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "TestController", description = "测试接口")
public class TestController {

    @ApiOperation(value = "测试",notes = "Test")
    @GetMapping(value = {"/test/test"})
    public String test() {
        User user = new User();
        user.setName("Sandy");
        Position position = new Position();
        position.setName("总经理");

        return "hello word1!" + user.getName() + position.getName();
    }
}
