package com.sl.demo.server.controller.wechat;

import com.sl.demo.server.util.Decript;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@ResponseBody
public class WechatJoinController {

    private final String token = "sandyLeeWeChat";

    @RequestMapping(value = "/sl/wechat/checkSignature", method = RequestMethod.GET)
    public String checkJoin(@RequestParam(value = "signature") String signature,
                            @RequestParam(value = "timestamp") String timestamp,
                            @RequestParam(value = "nonce") String nonce,
                            @RequestParam(value = "echostr") String echostr){

        System.out.println("开始校验签名0");
        List<String> array = new ArrayList<String>();
        array.add(signature);
        array.add(timestamp);
        array.add(nonce);

        //排序
        String sortString = sort(token, timestamp, nonce);
        //加密
        String mytoken = Decript.SHA1(sortString);
        //校验签名
        if (StringUtils.hasText(mytoken) && mytoken.equals(signature)){
            System.out.println("校验签名成功1");
            return echostr;
        } else {
           System.out.println("校验签名失败-1");
           return "-1";
        }
    }

    public static String sort(String token, String timestamp, String nonce){

        String[] stringArray = {token, timestamp, nonce};
        Arrays.sort(stringArray);
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : stringArray){
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
