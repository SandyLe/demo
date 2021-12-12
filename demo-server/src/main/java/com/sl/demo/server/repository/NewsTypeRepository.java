package com.sl.demo.server.repository;

import com.sl.domain.entity.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long>, JpaSpecificationExecutor<NewsType> {

    @Query("select nt from NewsType nt where (nt.code in (?1)) and nt.rowSts = 10")
    List<NewsType> findList(List<String> codes);
}
