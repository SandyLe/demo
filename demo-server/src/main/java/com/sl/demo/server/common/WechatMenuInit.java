package com.sl.demo.server.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sl.domain.dto.wechat.WechatButton;
import com.sl.domain.dto.wechat.WechatClickButton;
import com.sl.domain.dto.wechat.WechatMenu;
import com.sl.domain.dto.wechat.WechatViewButton;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;

public class WechatMenuInit {

    public static final String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";

    public static WechatMenu initMenu(){
        WechatMenu menu = new WechatMenu();
        WechatClickButton clickButton11 = new WechatClickButton();
        clickButton11.setKey("11");
        clickButton11.setName("关于我们");
        clickButton11.setType("click");

        WechatClickButton clickButton12 = new WechatClickButton();
        clickButton12.setKey("12");
        clickButton12.setName("加入我们");
        clickButton12.setType("click");

        WechatViewButton viewButton21 = new WechatViewButton();
        viewButton21.setUrl("http://www.qifou.net/test/test");
        viewButton21.setName("网站系统");
        viewButton21.setType("view");

        WechatViewButton viewButton22 = new WechatViewButton();
        viewButton22.setUrl("http://www.qifou.net/test/test");
        viewButton22.setName("企业ERP");
        viewButton22.setType("view");

        WechatViewButton viewButton31 = new WechatViewButton();
        viewButton31.setUrl("http://www.qifou.net/test/test");
        viewButton31.setName("APP");
        viewButton31.setType("view");

        WechatViewButton viewButton32 = new WechatViewButton();
        viewButton32.setUrl("http://www.qifou.net/test/test");
        viewButton32.setName("微信小程序");
        viewButton32.setType("view");

        WechatClickButton clickButton31 = new WechatClickButton();
        clickButton31.setKey("31");
        clickButton31.setName("小李");
        clickButton31.setType("click");

        WechatButton button1 = new WechatButton();
        button1.setName("小李软件");
        button1.setSub_button(new WechatButton[]{clickButton11,clickButton12});

        WechatButton button2 = new WechatButton();
        button2.setName("软件商城");
        button2.setSub_button(new WechatButton[]{viewButton21, viewButton22});

        WechatButton button3 = new WechatButton();
        button3.setName("移动端");
        button3.setSub_button(new WechatButton[]{viewButton31, viewButton32});

        menu.setButton(new WechatButton[]{button1,button2,button3});
        return menu;
    }

    public static int createMenu(String token,String menu) {

        int result = 0;
        String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WechatUtil.doPost(url, JSONObject.parseObject(menu));
        if(jsonObject != null){
            System.out.println(jsonObject);
            result = jsonObject.getIntValue("errcode");
        }
        return result;
    }

    public static void main(String[] args) {

        String token = WechatUtil.getAccessToken();
        System.out.println("Access_Token为:"+token);
        String menu = JSONObject.toJSONString(initMenu());
        System.out.println(menu);
        int result = createMenu(token, menu);
        if(result==0){
            System.out.println("菜单创建成功！");
        }else{
            System.out.println("菜单创建失败");
        }
    }
}
