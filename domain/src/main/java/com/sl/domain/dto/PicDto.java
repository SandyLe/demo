package com.sl.domain.dto;

import com.sl.domain.entity.Album;
import com.sl.domain.entity.Picture;

import java.util.List;

public class PicDto {

    private Album album;

    private List<Picture> pics;

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public List<Picture> getPics() {
        return pics;
    }

    public void setPics(List<Picture> pics) {
        this.pics = pics;
    }
}
