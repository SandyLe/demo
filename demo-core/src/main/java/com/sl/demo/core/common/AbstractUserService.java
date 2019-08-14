package com.sl.demo.core.common;

import com.sl.domain.entity.User;

public interface AbstractUserService {
    void save(User user);
    User findByName(String userName);
    User findByWechatOpenId(String wechatOpenId);
}
