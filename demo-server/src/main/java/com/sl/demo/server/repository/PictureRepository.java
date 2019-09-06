package com.sl.demo.server.repository;

import com.sl.domain.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<Picture, Long> {

    @Query("select p from Picture p where p.albumCode is ?1 and p.rowSts = 10")
    List<Picture> findByCodes(String albumCode);
}
