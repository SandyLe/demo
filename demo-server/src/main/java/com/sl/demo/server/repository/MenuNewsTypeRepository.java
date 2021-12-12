package com.sl.demo.server.repository;

import com.sl.domain.entity.relation.MenuNewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MenuNewsTypeRepository extends JpaRepository<MenuNewsType,Long>, JpaSpecificationExecutor<MenuNewsType> {

    @Query("select u from MenuNewsType u where (u.newsTypeCode = (?1)) and u.rowSts = 10")
    MenuNewsType findByNewsType(String newsType);
}
