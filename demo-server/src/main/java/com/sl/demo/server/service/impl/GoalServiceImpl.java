package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.GoalRepository;
import com.sl.demo.server.service.GoalService;
import com.sl.domain.entity.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Override
    public void save(Goal goal) {
        goalRepository.save(goal);
    }

    @Override
    public Goal findById(Long id) {
        return goalRepository.findOne(id);
    }

    @Override
    public List<Goal> findByUserId(Long id) {
        return goalRepository.findByCreateUserId(id);
    }
}
