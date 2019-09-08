package com.sl.demo.server.service;

import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Album;

import java.util.List;

public interface AlbumService {

    void save(Album album);

    Album findById(Long id);

    Pagination<Album> findPage(Pagination<Album> pagination);;

    List<Album> findByCodes(List<String> codes);

    void delete(Long[] id);

    List<Album> findList();
}
