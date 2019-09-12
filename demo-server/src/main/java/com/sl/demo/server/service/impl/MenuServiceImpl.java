package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.MenuRepository;
import com.sl.demo.server.service.MenuService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Menu;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void save(Menu menu) {
        menu.setRowSts(RowSts.EFFECTIVE.getId());
        Menu exist = findByCode(menu.getCode());
        Assert.isNull(exist, "编号已存在！");
        menuRepository.save(menu);
    }

    public Menu findByName(String name) {
        return menuRepository.findByName(name);
    }

    @Override
    public Pagination<Menu> findPage(Pagination<Menu> pagination) {
        Page<Menu> page = menuRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        List<Menu> datas = page.getContent();
        List<String> codeList = datas.stream().map(Menu::getParent).collect(Collectors.toList());
        codeList = codeList.stream().filter(o-> null != o).collect(Collectors.toList());
        if(CollectionUtils.isNotEmpty(codeList)){
            List<Menu> parents = findByCodes(codeList);
            Map<String, Menu> parentsMap = parents.stream().collect(Collectors.toMap(Menu::getCode,o->o));
            datas.forEach(o->{
                o.setParentDto(parentsMap.get(o.getParent()));
            });
        }
        pagination.setData(datas);
        return pagination;
    }

    @Override
    public Menu findById(Long id) {
        return menuRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            menuRepository.delete(tempId);
        }
    }

    private List<Menu> findByCodes(List<String> codeList){
        return menuRepository.findByCodes(codeList);
    }

    private Menu findByCode(String code){
        return menuRepository.findByCode(code);
    }

    @Override
    public List<Menu> findList(String level, String parent) {
        Specification<Menu> specification = new Specification<Menu>() {
            @Override
            public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = Lists.newArrayList();
                if(StringUtils.hasText(level)){
                    list.add(cb.equal(root.get("level"),level));
            }
                if(StringUtils.hasText(parent)){
                    list.add(cb.equal(root.get("parent"),parent));
                }
                query.where(list.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        return menuRepository.findAll(specification);
    }

}
