package com.sl.demo.server.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.PolicyErrorCodeHelper;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

public class WechatUtil {
//
//    private static final String APPID = "wx48f795dc060968b8";
//    private static final String APPSECRET = "e223973fbd047d6fb4e47eb10650a992";

    private static final String APPID = "wxf18957ea0e74ea23";
    private static final String APPSECRET = "6a9ec538d48fe53f716b04b183d1f842";
    /**全局token 所有与微信有交互的前提 */
    public static String ACCESS_TOKEN;
    /**全局token上次获取事件 */
    public static long LASTTOKENTIME;

    public static synchronized  JSONArray getIp(){

        String url = "https://api.weixin.qq.com/cgi-bin/getcallbackip?access_token=";
        String token = getAccessToken();
        url = url + token;
        JSONObject json = doGet(url);
        JSONArray ip_list = json.getJSONArray("ip_list");
        return  ip_list;
    }

    public static synchronized String getAccessToken(){

        if(StringUtils.isEmpty(ACCESS_TOKEN) || System.currentTimeMillis() - LASTTOKENTIME > 7000*1000){
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APPID+"&secret="+APPSECRET;
            JSONObject jsonObject = doGet(url);
            ACCESS_TOKEN = (String) jsonObject.get("access_token");
            LASTTOKENTIME = System.currentTimeMillis();
            return ACCESS_TOKEN;
        }
        return ACCESS_TOKEN;
    }

    public static JSONObject doGet(String url){
        JSONObject jsonObject = null;
        if(!StringUtils.isEmpty(url)){
            try {
                HttpGet httpGet = new HttpGet(url);
                HttpClient httpClient = new DefaultHttpClient();
                HttpResponse response = httpClient.execute(httpGet);
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                    String str = EntityUtils.toString(response.getEntity(), "utf-8");
                    jsonObject = JSON.parseObject(str);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    public static JSONObject doPost(String url, JSONObject jsonObject) {
        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            post.addHeader("Content-type","application/json; charset=UTF-8");
            post.setHeader("Accept", "application/json");
            StringEntity s = new StringEntity(jsonObject.toString(),"UTF-8");
            s.setContentType("application/json");
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                HttpEntity entity = res.getEntity();
                String result = EntityUtils.toString(entity);
                response = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return response;
    }

    public static void main(String[] args){

        System.out.println(getAccessToken());
        System.out.println(getIp());
    }

}
