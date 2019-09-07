package com.sl.domain.dto;

import com.sl.domain.entity.Album;

import java.util.List;

public class PicDto {

    private Album album;

    private List<String> urls;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
