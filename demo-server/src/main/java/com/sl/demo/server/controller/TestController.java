package com.sl.demo.server.controller;

import com.sl.demo.core.utils.SpringUtils;
import com.sl.demo.server.service.PositionService;
import com.sl.demo.server.service.UserService;
import com.sl.domain.entity.Position;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Api(value = "TestController", description = "测试接口")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private PositionService positionService;

    @ApiOperation(value = "测试",notes = "Test")
    @GetMapping(value = {"/test/test"})
    public String test() {

        Position position = new Position();
        position.setName("总经理");
        position.setCreateDate(new Date());
        position.setCreateUserId(1l);
        position.setUpdateDate(new Date());
        position.setUpdateUserId(1l);
        position.setDescription("test");
        positionService.save(position);

        User user = new User();
        user.setName("Sandyq2");
        user.setCreateDate(new Date());
        user.setCreateUserId(1l);
        user.setUpdateDate(new Date());
        user.setUpdateUserId(1l);
        user.setDescription("test");
        List<Position> positions = new ArrayList<Position>();
        positions.add(position);
        user.setPositions(positions);
        userService.save(user);
        return "hello word1!" + user.getName() + position.getName();
    }
}
