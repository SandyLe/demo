package com.sl.demo.server.repository;

import com.sl.domain.entity.ProductTraceTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ProductTraceTaskRepository extends JpaRepository<ProductTraceTask,Long>, JpaSpecificationExecutor<ProductTraceTask> {

    @Query("select nt from ProductTrace nt where (nt.productCode = (?1)) and nt.traceCode = (?2)")
    ProductTraceTask findByProductTraceCode(String productCode, String traceCode);
}
