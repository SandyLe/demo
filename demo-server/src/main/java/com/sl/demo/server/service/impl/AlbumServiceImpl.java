package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.AlbumRepository;
import com.sl.demo.server.repository.GoalRepository;
import com.sl.demo.server.service.AlbumService;
import com.sl.demo.server.service.GoalService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Album;
import com.sl.domain.entity.Goal;
import com.sl.domain.enums.RowSts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository albumRepository;

    @Override
    public void save(Album album) {
        albumRepository.save(album);
    }

    @Override
    public Album findById(Long id) {
        return albumRepository.findOne(id);
    }

    @Override
    public Pagination<Album> findPage(Pagination<Album> pagination) {
        Page<Album> page = albumRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public List<Album> findByCodes(List<String> codes) {
        return albumRepository.findByCodes(codes);
    }

    @Override
    public void delete(Long[] id) {
        for(Long tempId : id){
            albumRepository.delete(tempId);
        }
    }

    @Override
    public List<Album> findList(){
        return albumRepository.findList(RowSts.EFFECTIVE.getId());
    }
}
