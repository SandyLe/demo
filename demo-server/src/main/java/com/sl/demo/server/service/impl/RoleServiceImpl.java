package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.RoleRepository;
import com.sl.demo.server.repository.UserRepository;
import com.sl.demo.server.service.RoleService;
import com.sl.demo.server.service.UserService;
import com.sl.domain.entity.Role;
import com.sl.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
