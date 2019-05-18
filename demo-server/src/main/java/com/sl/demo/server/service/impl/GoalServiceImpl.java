package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.GoalRepository;
import com.sl.demo.server.service.GoalService;
import com.sl.domain.entity.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public void save(Goal goal) {
        goalRepository.save(goal);
    }
}
