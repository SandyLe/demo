package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.PositionRepository;
import com.sl.demo.server.service.PositionService;
import com.sl.domain.entity.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl implements PositionService {

    @Autowired
    private PositionRepository positionRepository;

    @Override
    public void save(Position position) {
        positionRepository.save(position);
    }
}
