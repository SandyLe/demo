package com.sl.demo.server.service;

import com.sl.domain.dto.wechat.AccessToken;

public interface WechatService {
    AccessToken wechatLogin(String code);
}
