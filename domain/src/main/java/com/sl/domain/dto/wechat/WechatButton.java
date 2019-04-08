package com.sl.domain.dto.wechat;

public class WechatButton {
    private String type;
    private String name;
    private WechatButton[] sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WechatButton[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(WechatButton[] sub_button) {
        this.sub_button = sub_button;
    }
}
