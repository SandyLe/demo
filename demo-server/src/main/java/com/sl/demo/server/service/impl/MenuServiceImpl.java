package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.MenuRepository;
import com.sl.demo.server.service.MenuService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Menu;
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

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    public Menu findByName(String name) {
        return menuRepository.findByName(name);
    }

    @Override
    public Pagination<Menu> findPage(Pagination<Menu> pagination) {
        Page<Menu> page = menuRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
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
