package com.sl.demo.server.repository;

import com.sl.domain.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BrandRepository extends JpaRepository<Brand,Long>, JpaSpecificationExecutor<Brand> {

}
