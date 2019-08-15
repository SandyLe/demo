package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.UserRepository;
import com.sl.demo.server.service.UserService;
import com.sl.demo.server.util.PasswordHelper;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        PasswordHelper.encryptPassword(user);
        userRepository.save(user);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User findByWechatOpenId(String wechatOpenId){
        return userRepository.findByWechatOpenId(wechatOpenId);
    }

    @Override
    public Pagination<User> findPage(Pagination<User> pagination) {
        Page<User> page = userRepository.findAll(pagination);
        pagination.setTotalRecords(page.getTotalElements());
        pagination.setData(page.getContent());
        return pagination;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public void delete(Long[] id){
        for (Long tempId : id){
            userRepository.delete(tempId);
        }
    }
}
