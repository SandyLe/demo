package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.ProductTraceTaskRepository;
import com.sl.demo.server.service.ProductService;
import com.sl.demo.server.service.ProductTraceService;
import com.sl.demo.server.service.ProductTraceTaskService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Product;
import com.sl.domain.entity.ProductTrace;
import com.sl.domain.entity.ProductTraceTask;
import com.sl.domain.enums.RowSts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class ProductTraceTaskServiceImpl implements ProductTraceTaskService {

    @Autowired
    private ProductTraceTaskRepository productTraceTaskRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTraceService productTraceService;

    @Override
    public void save(ProductTraceTask product) {
        productTraceTaskRepository.save(product);
    }

    @Override
    public Pagination<ProductTraceTask> findPage(Pagination<ProductTraceTask> pagination) {
        Page<ProductTraceTask> page = productTraceTaskRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<ProductTraceTask> datas = page.getContent();
        List<String> proCodes = datas.stream().map(ProductTraceTask::getProductCode).collect(Collectors.toList());
        List<Product> products = productService.findList(proCodes, null, RowSts.EFFECTIVE.getId());
        List<String> brandCodes = products.stream().map(Product::getBrandCode).collect(Collectors.toList());
        Map<String, Product> productMap = products.stream().collect(Collectors.toMap(Product::getCode, o->o));
        datas.stream().forEach(o->{o.setProduct(productMap.get(o.getProductCode()));});
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public ProductTraceTask findById(Long id) {
        return productTraceTaskRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            productTraceTaskRepository.delete(tempId);
        }
    }

    @Override
    public void genTraceCode(Long[] id){

        List<ProductTraceTask> tasks = productTraceTaskRepository.findAll(Arrays.asList(id));
        tasks.stream().forEach(task->{

            Integer count = task.getEnd() - task.getStart() + 1;
            List<String> midStrs = generateStr(task.getMidCount(), task.getStart(), task.getEnd());
            List<String> endStrs = generateRandomStr(task.getEndCount(), count);
            List<String> scanStrs = generateRandomStr(task.getEndCount(), count);
            for (int i = task.getStart(); i <= task.getEnd(); i++){

                ProductTrace productTrace = new ProductTrace();
                productTrace.setProductCode(task.getProductCode());
                productTrace.setProductId(task.getProductId());
                productTrace.setTraceCode(task.getPrefix() + midStrs.get(i-task.getStart()) + endStrs.get(i-task.getStart()));
                productTrace.setScanCode(productTrace.getTraceCode() + scanStrs.get(i-task.getStart()));
                productTraceService.save(productTrace);
            }
            task.setIsGen(1);
            productTraceTaskRepository.save(task);
        });
    }

    @Override
    public ProductTraceTask findByProductTraceCode(String productCode, String traceCode) {

        return productTraceTaskRepository.findByProductTraceCode(productCode, traceCode);
    }

    private List<String> generateStr(Integer length, Integer start, Integer end) {

        List<String> rList = Lists.newArrayList();
        for (int i=start; i<=end; i++) {
            rList.add(String.format("%0" +length + "d", i));
        }
        return rList;
    }

    private List<String> generateRandomStr(Integer length, Integer count) {

        List<String> rList = Lists.newArrayList();
        double c=Math.pow(10,length);
        String cStr = String.valueOf(c);
        cStr = cStr.substring(0, cStr.indexOf("."));
        Random r= new Random();
        for (int i=0; i<=count; i++) {

            Integer result = r.nextInt(Integer.parseInt(cStr));
            rList.add(String.format("%0" +length + "d", result));
        }
        return rList;
    }

}
