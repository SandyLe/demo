package com.sl.demo.server.controller;

import com.sl.demo.server.service.ProductService;
import com.sl.domain.entity.Product;
import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@ApiModel(value = "ProductSourceController", description = "产品溯源")
public class ProductSourceController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/fc/product/sourceQuery", method = RequestMethod.GET)
    public String querySource(Model model, @RequestParam(value = "code", required = true, defaultValue = "0") String code) {

        Product product = productService.findByCode(code);
        model.addAttribute("product", product);
        return "productSource";
    }
}
