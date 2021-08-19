package com.sl.demo.server.repository;

import com.sl.domain.entity.ProductTrace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductTraceRepository extends JpaRepository<ProductTrace,Long>, JpaSpecificationExecutor<ProductTrace> {

    @Query("select nt from ProductTrace nt where (nt.productCode = (?1)) and nt.traceCode = (?2)")
    ProductTrace findByProductTraceCode(String productCode, String traceCode);
}
