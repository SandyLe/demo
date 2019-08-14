package com.sl.demo.server.controller;

import com.sl.demo.server.service.UserService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "UserController", description = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = {"/user/save"})
    public Result<Long> save(@RequestBody User user){
        userService.save(user);
        return new Result<Long>(user.getId());
    }
    @GetMapping(value = {"/user/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = userService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
}
