package com.sl.demo.server.service;

import com.sl.demo.core.common.AbstractUserService;
import com.sl.domain.dto.util.Pagination;
import com.sl.domain.entity.User;

public interface UserService extends AbstractUserService {
    void save(User user);
    Pagination<User> findPage(Pagination<User> pagination);
    User findById(Long id);
    void delete(Long[] id);
}
