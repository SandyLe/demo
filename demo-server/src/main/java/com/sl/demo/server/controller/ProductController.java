package com.sl.demo.server.controller;

import com.sl.demo.server.service.BrandService;
import com.sl.demo.server.service.ProductService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Brand;
import com.sl.domain.entity.Product;
import com.sl.domain.enums.RowSts;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@RestController
@Api(value = "ProductController", description = "热销产品接口")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private BrandService brandService;

    @PostMapping(value = {"/product/save"})
    public Result<Long> save(@RequestBody Product product){
        productService.save(product);
        return new Result<Long>(product.getId());
    }

    @PostMapping(value = {"/product/upload"})
    public Result<Long> upload(String name, String code, @RequestParam("starPic") MultipartFile starPic){
        Product product = new Product();
//        productService.save(product);
        return new Result<Long>(product.getId());
    }

    @GetMapping(value = {"/product/getPage"})
    public Result<Pagination> getPage(Pagination pagination){
        pagination = productService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }
    @GetMapping(value = {"/product/getOne","/fc/product/getOne"})
    public Result<Product> getOne(Long id){
        Product product = productService.findById(id);
        return new Result<Product> (product);
    }
    @GetMapping(value = {"/product/generateQcode"})
    public Result<Product> generateQcode(Long id) throws Exception{
        String productUrl = productService.generateQcode(id);
        return new Result<Product> (productUrl);
    }
    @PostMapping(value = {"/product/delete"})
    public Result<Long[]> delete(Long[] id){
        productService.delete(id);
        return new Result<Long[]> (id);
    }

    @GetMapping(value = {"/product/getList"})
    public Result<List<Product>> getList(@RequestParam(value = "brandCode") String brandCode){
        List<Product> productList = productService.findList(null, brandCode, RowSts.EFFECTIVE.getId());
        return new Result<List<Product>> (productList);
    }

}
