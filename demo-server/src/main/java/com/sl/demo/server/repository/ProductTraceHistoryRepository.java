package com.sl.demo.server.repository;

import com.sl.domain.entity.ProductTraceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductTraceHistoryRepository extends JpaRepository<ProductTraceHistory,Long>, JpaSpecificationExecutor<ProductTraceHistory> {


    @Query("select nt from ProductTraceHistory nt where nt.productCode = (?1) and nt.traceCode = (?2)")
    List<ProductTraceHistory> findHistories(String productCode, String traceCode);

    @Query(value = "select nt.* from sl_product_trace_history nt where nt.product_code = (?1) and nt.trace_code = (?2) order by nt.create_date asc limit 1", nativeQuery=true)
    ProductTraceHistory findEarliest(String productCode, String traceCode);
}
