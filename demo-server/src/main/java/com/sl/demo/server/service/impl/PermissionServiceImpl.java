package com.sl.demo.server.service.impl;

import com.sl.demo.server.repository.PermissionRepository;
import com.sl.demo.server.service.PermissionService;
import com.sl.domain.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public void save(Permission permission) {
        permissionRepository.save(permission);
    }
}
