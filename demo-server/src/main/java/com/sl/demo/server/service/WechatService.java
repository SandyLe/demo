package com.sl.demo.server.service;

import com.alibaba.fastjson.JSONObject;
import com.sl.domain.dto.wechat.WechatAccountDto;

import java.util.Map;

public interface WechatService {

    /**
     * 获取微信登录token
     * @Since 2020-12-19 15:33:00
     * @auth sandylee
     * @param code
     * @return
     */
    JSONObject getOpenIdMsg(String code);

    /**
     * 微信免密登录
     * @Since 2020-12-19 16:56:00
     * @auth sandylee
     * @param accountDto
     * @return
     */
    Map<String, Object> wechatLogin (WechatAccountDto accountDto);
}
