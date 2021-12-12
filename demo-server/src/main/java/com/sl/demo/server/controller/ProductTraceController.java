package com.sl.demo.server.controller;

import com.sl.demo.server.service.ProductTraceHistoryService;
import com.sl.demo.server.service.ProductTraceService;
import com.sl.domain.dto.ProductTraceResult;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.ProductTrace;
import com.sl.domain.entity.ProductTraceHistory;
import io.swagger.annotations.Api;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@Api(value = "ProductTraceController", description = "产品溯源验证接口")
public class ProductTraceController {

    @Autowired
    private ProductTraceService productTraceService;
    @Autowired
    private ProductTraceHistoryService productTraceHistoryService;

    @PostMapping(value = {"/productTrace/save"})
    public Result<Long> save(@RequestBody ProductTrace productTrace){
        productTraceService.save(productTrace);
        return new Result<Long>(productTrace.getId());
    }

    @GetMapping(value = {"/productTrace/getPage"})
    public Result<Pagination> getPage(Pagination pagination){

        pagination = productTraceService.findPage(pagination);
        return new Result<Pagination> (pagination);
    }

    @GetMapping(value = {"/productTrace/getOne"})
    public Result<ProductTrace> getOne(Long id){

        ProductTrace productTrace = productTraceService.findById(id);
        return new Result<ProductTrace> (productTrace);
    }

    @PostMapping(value = {"/productTrace/delete"})
    public Result<Long[]> delete(Long[] id){

        productTraceService.delete(id);
        return new Result<Long[]> (id);
    }

    @PostMapping(value = {"/fc/productTrace/check"})
    public Result<ProductTraceResult> check(@RequestBody ProductTraceHistory productTraceHistory){

        ProductTraceResult result = new ProductTraceResult();
        result.setResultFlag(0);
        ProductTrace productTrace = productTraceService.findByProductTraceCode(productTraceHistory.getProductCode(), productTraceHistory.getTraceCode());
        if (null == productTrace) {
            result.setResultFlag(-1);
            result.setMsg("没有此产品验证码，请联系卖家查实！");
        } else {
            List<ProductTraceHistory> histories = productTraceHistoryService.findHistories(productTraceHistory.getProductCode(), productTraceHistory.getTraceCode());
            productTraceHistory.setCreateDate(new Date());
            if (CollectionUtils.isEmpty(histories)) {
                result.setMsg("此产品为正品，本次为第一次验证！");
                result.setFirstQueryDate(productTraceHistory.getCreateDate());
            } else {
                result.setMsg("本次为第 " + (histories.size() + 1) + " 次验证，多次验证，谨防假冒！" );
            }
            productTraceHistoryService.save(productTraceHistory);
        }

        return new Result<ProductTraceResult>(result);
    }


    @PostMapping(value = {"/fc/productTrace/updateAddress"})
    public Result<ProductTraceHistory> updateAddress(@RequestBody ProductTraceHistory productTraceHistory){

        ProductTraceHistory exists = productTraceHistoryService.findById(productTraceHistory.getId());
        exists.setSource(productTraceHistory.getSource());
        productTraceHistoryService.save(exists);

        return new Result<ProductTraceHistory>(productTraceHistory);
    }
}
