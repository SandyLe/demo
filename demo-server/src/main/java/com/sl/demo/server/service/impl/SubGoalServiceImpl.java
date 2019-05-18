package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.SubGoalRepository;
import com.sl.demo.server.service.SubGoalService;
import com.sl.domain.entity.SubGoal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubGoalServiceImpl implements SubGoalService {

    @Autowired
    private SubGoalRepository subGoalRepository;

    @Override
    public void save(SubGoal subGoal) {
        subGoalRepository.save(subGoal);
    }
}
