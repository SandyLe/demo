package com.sl.demo.server.controller;

import com.sl.demo.server.service.ProductService;
import com.sl.demo.server.service.ProductTraceHistoryService;
import com.sl.demo.server.service.ProductTraceService;
import com.sl.domain.dto.ProductTraceResult;
import com.sl.domain.dto.util.Result;
import com.sl.domain.entity.Product;
import com.sl.domain.entity.ProductTrace;
import com.sl.domain.entity.ProductTraceHistory;
import io.swagger.annotations.ApiModel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
                              @RequestParam(value = "scanCode", required = false) String scanCode) {

        Product product = new Product();
        if (StringUtils.hasText(code)) {
            product = productService.findByCode(code);
        }
        ProductTraceResult result = new ProductTraceResult();
        if (StringUtils.hasText(scanCode)) {

            ProductTrace productTrace = productTraceService.findByProductScanCode(code, scanCode);
            if (null == productTrace) {
                result.setResultFlag(-1);
                result.setMsg("没有此产品验证码，请联系卖家查实！");
            } else {
                ProductTraceHistory productTraceHistory = new ProductTraceHistory();
                productTraceHistory.setCreateDate(new Date());
                productTraceHistory.setProductCode(code);
                productTraceHistory.setScanCode(scanCode);
                ProductTraceHistory history = productTraceHistoryService.findEarliestByScanCode(code, scanCode);
                if (null != history) {
                    result.setFirstQueryDate(history.getCreateDate());
                    result.setFirstAdd(history.getSource());
                }
                List<ProductTraceHistory> histories = productTraceHistoryService.findHistoriesByScanCode(code, scanCode);
                productTraceHistoryService.save(productTraceHistory);
                result.setResultFlag(2);
                result.setHistoryId(productTraceHistory.getId());
                result.setScanCode(scanCode);
                if (CollectionUtils.isEmpty(histories)) {
                    result.setResultFlag(1);
                    result.setMsg("本次为第一次扫码！");
                } else {
                    result.setMsg("本次为第 " + (histories.size() + 1) + " 次扫码，多次扫码，请输入验证码验证真伪！" );
                    histories.add(productTraceHistory);
                    model.addAttribute("histories", histories);
                }
            }
        }

        model.addAttribute("product", product);
        model.addAttribute("firstTrace", result);

        return "productSource";
    }

    @RequestMapping(value = "/fc/product/sourceQueryWeb", method = RequestMethod.GET)
    public String sourceQueryWeb(Model model, @RequestParam(value = "code", required = true, defaultValue = "0") String code,
                              @RequestParam(value = "scanCode", required = false) String scanCode) {

        return "productSourceWeb";
    }

}
