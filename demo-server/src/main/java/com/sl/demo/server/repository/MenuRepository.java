package com.sl.demo.server.repository;

import com.sl.domain.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface MenuRepository extends JpaRepository<Menu,Long>, JpaSpecificationExecutor<Menu> {
    @Query("select u from Menu u where (u.name = ?1) and u.rowSts = 10")
    Menu findByName(String name);

}
