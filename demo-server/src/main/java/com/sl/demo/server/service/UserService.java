package com.sl.demo.server.service;

import com.sl.demo.core.common.AbstractUserService;
import com.sl.domain.entity.User;

public interface UserService extends AbstractUserService {
    public void save(User user);
}
