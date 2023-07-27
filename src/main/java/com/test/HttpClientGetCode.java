package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpClientGetCode {
    public Integer GetClientGetCode(String url, String token, String appid) throws IOException
    {
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpGet httpGet = new HttpGet(url);
        //添加请求头
        httpGet.addHeader("Content-Type","application/json;charset=UTF-8");
        httpGet.addHeader("Connection","keep-alive");
        httpGet.addHeader("X-Authorization","Bearer"+" "+token);
        httpGet.addHeader("Appid",appid);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpGet);

        int code = response.getStatusLine().getStatusCode();

        response.close();
        httpClient.close();
        return code;
    }

    public Integer PostClientGetCode(Map<String, Object> map, String url, String token, String appid) throws IOException
    {

        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost(url);
        //添加请求头
        httpPost.addHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.addHeader("X-Authorization","Bearer"+" "+token);
        //   httpPost.addHeader("Host","open.openwhy.net");
        httpPost.addHeader("Appid",appid);
        //封装请求参数，将map集合转换成json格式
        JSONObject jsonString = new JSONObject(map);
        //使用StringEntity转换成实体类型
        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
        //将封装的参数添加到Post请求中
        httpPost.setEntity(entity);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpPost);
        int code = response.getStatusLine().getStatusCode();
        response.close();
        httpClient.close();
        return code;
    }
}
