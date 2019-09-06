package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Picture;

public interface PictureService {

    void save (Picture picture);

    Pagination<Picture> findPage(Pagination<Picture> pagination);

    Picture findById(Long id);

    void delete(Long[] id);
}
