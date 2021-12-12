package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Feedback;

public interface FeedbackService {

    void save(Feedback Feedback);
    Pagination<Feedback> findPage(Pagination<Feedback> pagination);
    Feedback findById(Long id);
    void delete(Long[] id);
}
