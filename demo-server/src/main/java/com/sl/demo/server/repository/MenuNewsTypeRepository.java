package com.sl.demo.server.repository;

import com.sl.domain.entity.relation.MenuNewsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MenuNewsTypeRepository extends JpaRepository<MenuNewsType,Long>, JpaSpecificationExecutor<MenuNewsType> {

}
