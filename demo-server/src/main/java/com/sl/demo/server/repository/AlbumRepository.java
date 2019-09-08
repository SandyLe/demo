package com.sl.demo.server.repository;

import com.sl.domain.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("select a from Album a where (a.code in (?1)) and a.rowSts = 10")
    List<Album> findByCodes(List<String> codeList);

    @Query("select a from Album a where a.rowSts = :rowSts")
    List<Album> findList(@Param(value = "rowSts") Integer rowSts);
}
