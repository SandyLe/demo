package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.PictureRepository;
import com.sl.demo.server.service.PictureService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Picture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public Picture findById(Long id) {
        return pictureRepository.findOne(id);
    }

    @Override
    public Pagination<Picture> findPage(Pagination<Picture> pagination) {
        Page<Picture> page = pictureRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public void delete(Long[] id) {
        for(Long tempId : id){
            pictureRepository.delete(tempId);
        }
    }

}
