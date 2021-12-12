package com.sl.demo.server.controller;

import com.sl.demo.server.service.ProductService;
import com.sl.demo.server.service.ProductTraceHistoryService;
import com.sl.demo.server.service.ProductTraceService;
import com.sl.domain.dto.ProductTraceResult;
import com.sl.domain.entity.Product;
import com.sl.domain.entity.ProductTrace;
import com.sl.domain.entity.ProductTraceHistory;
import io.swagger.annotations.ApiModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

@Controller
@ApiModel(value = "ProductSourceController", description = "产品溯源")
public class ProductSourceController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductTraceHistoryService productTraceHistoryService;

    @Autowired
    private ProductTraceService productTraceService;

    @RequestMapping(value = "/fc/product/sourceQuery", method = RequestMethod.GET)
    public String querySource(Model model, @RequestParam(value = "code", required = true, defaultValue = "0") String code,
                              @RequestParam(value = "traceCode", required = false) String traceCode) {

        Product product = productService.findByCode(code);
        ProductTraceResult result = new ProductTraceResult();
        if (StringUtils.hasText(traceCode)) {

            ProductTrace productTrace = productTraceService.findByProductTraceCode(code, traceCode);
            if (null == productTrace) {
                result.setResultFlag(-1);
                result.setMsg("没有此产品验证码，请联系卖家查实！");
            } else {
                ProductTraceHistory productTraceHistory = new ProductTraceHistory();
                productTraceHistory.setCreateDate(new Date());
                productTraceHistory.setProductCode(code);
                productTraceHistory.setTraceCode(traceCode);
                ProductTraceHistory history = productTraceHistoryService.findEarliest(code, traceCode);
                if (null != history) {
                    result.setFirstQueryDate(history.getCreateDate());
                    result.setFirstAdd(history.getSource());
                }
                List<ProductTraceHistory> histories = productTraceHistoryService.findHistories(code, traceCode);
                productTraceHistoryService.save(productTraceHistory);
                result.setResultFlag(2);
                result.setHistoryId(productTraceHistory.getId());
                if (CollectionUtils.isEmpty(histories)) {
                    result.setResultFlag(1);
                    result.setMsg("此产品为正品，本次为第一次验证！");
                } else {
                    result.setMsg("本次为第 " + (histories.size() + 1) + " 次验证，多次验证，谨防假冒！" );
                }
            }
        }

        model.addAttribute("product", product);
        model.addAttribute("firstTrace", result);

        return "productSource";
    }
}
