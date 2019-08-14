package com.sl.demo.server.service;

import com.sl.domain.entity.Goal;

import java.util.List;

public interface GoalService {

    void save(Goal goal);

    Goal findById(Long id);

    List<Goal> findByUserId(Long id);
}
