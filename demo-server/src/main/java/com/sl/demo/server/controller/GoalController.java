package com.sl.demo.server.controller;

import com.sl.demo.server.service.GoalService;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Goal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "GoalController", description = "目标管理")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping(value = {"/goal/save"})
    public Result<Long> save(@RequestBody Goal goal){
        goalService.save(goal);
        return new Result<Long>(goal.getId());
    }

}
