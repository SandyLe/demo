package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.UserRepository;
import com.sl.demo.server.service.UserService;
import com.sl.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
