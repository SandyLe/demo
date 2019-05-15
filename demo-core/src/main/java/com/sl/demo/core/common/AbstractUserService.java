package com.sl.demo.core.common;

import com.sl.domain.entity.User;

public interface AbstractUserService {
    public void save(User user);
    public User findByName(String userName);
    public User findByWechatOpenId(String wechatOpenId);
}
