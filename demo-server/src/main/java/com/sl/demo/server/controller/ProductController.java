package com.sl.demo.server.controller;

import com.sl.demo.server.service.ProductService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Product;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "ProductController", description = "热销产品接口")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = {"/product/save"})
    public Result<Long> save(@RequestBody Product product){
        productService.save(product);
        return new Result<Long>(product.getId());
    }
    @GetMapping(value = {"/product/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = productService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/product/getOne"})
    public Result<Product> getOne(Long id){
        Product product = productService.findById(id);
        return new Result<Product> (product);
    }

    @PostMapping(value = {"/product/delete"})
    public Result<Long[]> delete(Long[] id){
        productService.delete(id);
        return new Result<Long[]> (id);
    }
}
