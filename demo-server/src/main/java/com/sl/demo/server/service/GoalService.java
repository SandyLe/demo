package com.sl.demo.server.service;

import com.sl.domain.entity.Goal;

import java.util.List;

public interface GoalService {

    public void save(Goal goal);

    public Goal findById(Long id);

    public List<Goal> findByUserId(Long id);
}
