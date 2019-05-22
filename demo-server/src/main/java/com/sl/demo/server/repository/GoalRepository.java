package com.sl.demo.server.repository;

import com.sl.domain.entity.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Long> {
    public List<Goal> findByCreateUserId(Long id);
}
