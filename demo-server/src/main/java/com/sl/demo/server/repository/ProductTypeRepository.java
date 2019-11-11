package com.sl.demo.server.repository;

import com.sl.domain.entity.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductTypeRepository extends JpaRepository<ProductType,Long>, JpaSpecificationExecutor<ProductType> {

}
