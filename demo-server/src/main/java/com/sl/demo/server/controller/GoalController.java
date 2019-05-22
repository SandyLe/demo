package com.sl.demo.server.controller;

import com.sl.demo.server.service.GoalService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Goal;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "GoalController", description = "目标管理")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @PostMapping(value = {"/goal/save"})
    public Result<Long> save(@RequestBody Goal goal){
        if(null == goal.getId()){
            goal.setCreateUserId(LoginUtils.getLoginUser().getId());
            goal.setCreateDate(new Date());
        }
        goal.setUpdateUserId(LoginUtils.getLoginUser().getId());
        goal.setUpdateDate(new Date());
        goalService.save(goal);
        return new Result<Long>(goal.getId());
    }

    @GetMapping(value = {"/goal/getOne"})
    public Result<Goal> getOne(@RequestParam(value = "id") Long id){
        Goal goal = goalService.findById(id);
        return new Result<Goal>(goal);
    }

    public Result<List<Goal>> getList(){
        List<Goal> list = goalService.findByUserId(LoginUtils.getLoginUser().getId());
        return new Result<List<Goal>>(list);
    }

}
