package com.sl.demo.core.common;

import com.sl.domain.entity.User;

public interface AbstractUserService {
    public User findByName(String userName);
}
