package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.FeedbackRepository;
import com.sl.demo.server.service.FeedbackService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void save(Feedback product) {
        feedbackRepository.save(product);
    }

    @Override
    public Pagination<Feedback> findPage(Pagination<Feedback> pagination) {
        Page<Feedback> page = feedbackRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<Feedback> datas = page.getContent();
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public Feedback findById(Long id) {
        return feedbackRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            feedbackRepository.delete(tempId);
        }
    }

}
