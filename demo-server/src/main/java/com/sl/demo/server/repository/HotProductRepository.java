package com.sl.demo.server.repository;

import com.sl.domain.entity.HotProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HotProductRepository extends JpaRepository<HotProduct,Long>, JpaSpecificationExecutor<HotProduct> {

}
