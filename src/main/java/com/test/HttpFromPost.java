package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpFromPost {
    //POST登录方法
    public JSONObject JzhPost(List<BasicNameValuePair> pairList,String uu) throws IOException
    {

        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost(uu);
        //添加请求头
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.addHeader("Origin","https://openwhy.net");
        httpPost.addHeader("Host","openwhy.net");
        httpPost.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 ");
        httpPost.addHeader("Accept-Language","zh-CN,zh;q=0.9");


        //httpPost.addHeader("Authorization",aa);
        //封装请求参数，将map集合转换成json格式
//        pairList.add(new BasicNameValuePair("username","13717888314"));
//        pairList.add(new BasicNameValuePair("password","1q2w3e4r5t6y"));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList,"UTF-8"));


//        JSONObject jsonString = new JSONObject(map);
//        //使用StringEntity转换成实体类型
//        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
        //将封装的参数添加到Post请求中
//        httpPost.setEntity(entity);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpPost);
        //获取响应的实体
        HttpEntity responseEntity = response.getEntity();
        //转化成字符串
        String entityString = EntityUtils.toString(responseEntity);
        //转换成JSON格式输出
        JSONObject result =  JSONObject.parseObject(entityString);
        response.close();
        httpClient.close();
        return result;
    }
}

