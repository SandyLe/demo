package com.sl.demo.server.controller.wechat;

import com.sl.demo.server.util.Constant;
import com.sl.demo.server.util.Decript;
import com.sl.demo.server.util.MessageUtil;
import com.sl.domain.dto.wechat.TextMessage;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@ResponseBody
public class WechatJoinController {

    private final String token = "sandyLeeWeChat";

    @RequestMapping(value = "/sl/wechat/checkSignature", method = {RequestMethod.GET, RequestMethod.POST})
    public void checkJoin(HttpServletRequest request, HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String requestMethod = request.getMethod().toLowerCase();
        Boolean isGet = Constant.METHOD_GET.equals(requestMethod);
        if (isGet) {

            String signature = request.getParameter("signature");
            String timestamp = request.getParameter("timestamp");
            String nonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
//        @RequestParam(value = "signature") String signature,
//        @RequestParam(value = "timestamp") String timestamp,
//        @RequestParam(value = "nonce") String nonce,
//        @RequestParam(value = "echostr") String echostr

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
//                return echostr;
                response.getWriter().write(echostr);
            } else {
                System.out.println("校验签名失败-1");
//                return "-1";
            }
        } else {
            response.getWriter().write(wechatPost(request));
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

    public String wechatPost(HttpServletRequest request) {
        String respMessage = null;
        try {
            // xml请求解析
            Map<String, String> requestMap = MessageUtil.xmlToMap(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");
            // 消息内容
            String content = requestMap.get("Content");
            System.out.println("fromUserName:"+fromUserName);
            System.out.println("toUserName:"+toUserName);
            System.out.println("msgType:"+msgType);
            if (MessageUtil.REQ_MESSAGE_TYPE_TEXT.equals(msgType)){
                TextMessage text = new TextMessage();
                text.setContent("the text is" + content);
                text.setToUserName(fromUserName);
                text.setFromUserName(toUserName);
                text.setCreateTime(new Date().getTime() + "");
                text.setMsgType(msgType);

                respMessage = MessageUtil.textMessageToXml(text);
                System.out.println(respMessage);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respMessage;
    }
}
