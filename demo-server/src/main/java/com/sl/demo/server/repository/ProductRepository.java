package com.sl.demo.server.repository;

import com.sl.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @Query("select nt from Product nt where (nt.code = (?1)) and nt.rowSts = 10")
    Product findByCode(String code);
}
