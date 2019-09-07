package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Picture;

import java.util.List;

public interface PictureService {

    void save (Picture picture);

    Pagination<Picture> findPage(Pagination<Picture> pagination);

    Picture findById(Long id);

    void delete(Long[] id);

    List<Picture> findList(Integer rowSts);
    List<Picture> findList(Integer rowSts, String albumCode);
}
