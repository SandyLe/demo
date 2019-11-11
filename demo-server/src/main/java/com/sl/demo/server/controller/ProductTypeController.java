package com.sl.demo.server.controller;

import com.google.common.collect.Lists;
import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.ProductTypeService;
import com.sl.domain.dto.ProductTypeDto;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.ProductType;
import com.sl.domain.enums.RowSts;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "ProductTypeController", description = "产品类型接口")
public class ProductTypeController {

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private BrandService brandService;

    @PostMapping(value = {"/productType/save"})
    public Result<Long> save(@RequestBody ProductType productType){
        productTypeService.save(productType);
        return new Result<Long>(productType.getId());
    }
    @GetMapping(value = {"/productType/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = productTypeService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/productType/getOne"})
    public Result<ProductType> getOne(Long id){
        ProductType user = productTypeService.findById(id);
        return new Result<ProductType> (user);
    }

    @PostMapping(value = {"/productType/delete"})
    public Result<Long[]> delete(Long[] id){
        productTypeService.delete(id);
        return new Result<Long[]> (id);
    }

    @GetMapping(value = {"/productType/getList"})
    public Result<List<ProductType>> findList(Integer rowSts){
        rowSts = null == rowSts ? RowSts.EFFECTIVE.getId() : rowSts;
        List<ProductType> productTypes = productTypeService.findList(null, rowSts);
        return new Result<List<ProductType>>(productTypes);
    }

    @GetMapping(value = {"/fc/productType/getList"})
    public Result<List<ProductTypeDto>> productTypeFcList(){
        List<ProductTypeDto> productTypeDtos = Lists.newArrayList();
        List<ProductType> productTypeList = productTypeService.findList(null, RowSts.EFFECTIVE.getId());
        for (ProductType productType : productTypeList) {
            ProductTypeDto dto = new ProductTypeDto();
            dto.setProductType(productType);
            dto.setBrandList(brandService.findByProductType(productType.getCode()));
            productTypeDtos.add(dto);
        }
      return new Result<List<ProductTypeDto>>(productTypeDtos);
    }
}
