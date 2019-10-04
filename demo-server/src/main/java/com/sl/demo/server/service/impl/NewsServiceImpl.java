package com.sl.demo.server.service.impl;

import com.google.common.collect.Lists;
import com.sl.demo.server.repository.NewsRepository;
import com.sl.demo.server.repository.NewsTypeRepository;
import com.sl.demo.server.service.NewsService;
import com.sl.demo.server.util.LoginUtils;
import com.sl.domain.dto.NewsPagination;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.News;
import com.sl.domain.entity.NewsType;
import com.sl.domain.enums.RowSts;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private NewsTypeRepository newsTypeRepository;

    @Override
    public void save(News news) {
        news.setRowSts(RowSts.NEW.getId());
        if(null == news.getId()){
            news.setCreateDate(new Date());
            news.setCreateUserId(LoginUtils.getLoginUser().getId());
        }
        news.setUpdateDate(new Date());
        news.setUpdateUserId(LoginUtils.getLoginUser().getId());
        newsRepository.save(news);
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public NewsPagination findPage(NewsPagination pagination) {
        Specification<News> specification = new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if(StringUtils.hasText(pagination.getNewsTypeCode())){
                    predicates.add(cb.equal(root.get("newsTypeCode"), pagination.getNewsTypeCode()));
                }
                query.where(predicates.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };

        Page<News> pageData = newsRepository.findAll(specification, pagination);
        List<News> data = pageData.getContent();
        List<String> codeList = data.stream().map(News::getNewsTypeCode).collect(Collectors.toList());
        codeList = codeList.stream().filter(o->StringUtils.hasText(o)).collect(Collectors.toList());
        List<NewsType> typeList = Lists.newArrayList();
        if(CollectionUtils.isNotEmpty(codeList)){
            typeList = newsTypeRepository.findList(codeList);
        }
        Map<String, NewsType> typeMap = typeList.stream().collect(Collectors.toMap(o -> o.getCode(), o->o));
        data.stream().forEach(o->{
            o.setNewsType(typeMap.get(o.getNewsTypeCode()));
            String content = o.getContent();
            List<String> imgUrls = getPics(content);
            content = imgUrls.remove(imgUrls.size() -1 );
            if(!StringUtils.hasText(o.getMainImgUrl())){
                if(imgUrls.size() > 0 ){
                    int imgNum = 0;
                    if(null != o.getMainImgNum() && o.getMainImgNum() <= imgUrls.size()){
                        imgNum = o.getMainImgNum();
                    }
                    o.setMainImgUrl(imgUrls.get(0 == imgNum ? 0 : imgNum-1));
                    o.setContent(content);
                }
            }
        });
        pagination.setData(data);
        pagination.setTotalRecords(pageData.getTotalElements());
        return pagination;

    }

    @Override
    public List<News> findList(String newsTypeCode, Integer rowSts, Integer topN) {
        Specification<News> specification = new Specification<News>() {
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = Lists.newArrayList();
                if (StringUtils.hasText(newsTypeCode)){
                    list.add(cb.equal(root.get("newsTypeCode"), newsTypeCode));
                }
                if (null != rowSts){
                    list.add(cb.equal(root.get("rowSts"), rowSts));
                }
                query.where(list.toArray(new Predicate[]{}));
                return query.getRestriction();
            }
        };
        Pageable pageable = new PageRequest(0, topN, new Sort(Sort.Direction.DESC, "updateDate"));
        Page<News> newsPage = newsRepository.findAll(specification, pageable);
        List<News> data = newsPage.getContent();
        List<String> codeList = data.stream().map(News::getNewsTypeCode).collect(Collectors.toList());
        List<NewsType> typeList = newsTypeRepository.findList(codeList);
        Map<String, NewsType> typeMap = typeList.stream().collect(Collectors.toMap(o -> o.getCode(), o->o));
        data.stream().forEach(o->{o.setNewsType(typeMap.get(o.getNewsTypeCode()));});
        return data;
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId: id){
            newsRepository.delete(tempId);
        }
    }

    @Override
    public void updateSts(Long id, Integer rowSts) {
        News news = findById(id);
        news.setRowSts(rowSts);
        newsRepository.save(news);
    }

    private List<String> getPics(String content){

        List<String> pics = Lists.newArrayList();
        if(StringUtils.hasText(content)){
            String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
            Pattern pattern  = Pattern.compile(regEx_img, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(content);
            String img = "";
            while (matcher.find()) {
                img = img + "," + matcher.group();

                Pattern patternImg = Pattern.compile("<img.*?>");
                Matcher matcherImg = patternImg.matcher(img);
                while (matcherImg.find()){
                    content = content.replace(matcherImg.group(),"");
                }

                Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
                while (m.find()) {
                    pics.add(m.group(1));
                }
            }
        }
        pics.add(content);
        return pics;
    }
}
