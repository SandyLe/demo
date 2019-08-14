package com.sl.demo.server.repository;

import com.sl.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {
    @Query("select u from User u where (u.name = ?1) and u.rowSts = 10")
    User findByName(String name);

    @Query("select u from User u where (u.wechatOpenId = ?1) and u.rowSts = 10")
    User findByWechatOpenId(String wechatOpenId);
}
