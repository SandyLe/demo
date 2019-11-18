package com.sl.demo.server.repository;

import com.sl.domain.entity.IndexConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndexConfigRepository extends JpaRepository<IndexConfig, Long> , JpaSpecificationExecutor<IndexConfig> {

    @Query("select a from IndexConfig a where a.rowSts = :rowSts")
    List<IndexConfig> findList(@Param(value = "rowSts") Integer rowSts);
}
