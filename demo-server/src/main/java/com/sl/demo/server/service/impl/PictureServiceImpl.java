package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.PictureRepository;
import com.sl.demo.server.service.PictureService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.Picture;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public Picture findById(Long id) {
        return pictureRepository.findOne(id);
    }

    @Override
    public Pagination<Picture> findPage(Pagination<Picture> pagination) {
        Page<Picture> page = pictureRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public void delete(Long[] id) {
        for(Long tempId : id){
            pictureRepository.delete(tempId);
        }
    }

    @Override
    public List<Picture> findList(Integer rowSts, String albumCode) {
        Specification<Picture> specification = new Specification<Picture>() {
            @Override
            public Predicate toPredicate(Root<Picture> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<Predicate>();
                if(null != rowSts){
                    predicates.add(cb.equal(root.get("rowSts"), rowSts));
                }
                if(StringUtils.hasText(albumCode)){
                    predicates.add(cb.equal(root.get("albumCode"), albumCode));
                }
                query.where(predicates.toArray(new Predicate[]{}));
                query.orderBy(cb.asc(root.get("sortIndex")));
                return query.getRestriction();
            }
        };
        return pictureRepository.findAll(specification);
    }

    @Override
    public List<Picture> findList(Integer rowSts){
        return findList(rowSts,null);
    }
}
