package com.sl.demo.server.repository;

import com.sl.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand,Long>, JpaSpecificationExecutor<Brand> {

    @Query("select nt from Brand nt where (nt.code in (?1)) and nt.rowSts = 10")
    public List<Brand> findByCodes(List<String> codes);

    @Query("select nt from Brand nt where nt.productTypeCode = (?1) and nt.rowSts = 10")
    public List<Brand> findByProductType(String productType);
}
