package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpJzhPut {
    //POST登录方法
    public JSONObject JzhPut(Map<String, Object> map,String uu,String aa,String appid) throws IOException
    {

        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Put请求
        HttpPut httpPut = new HttpPut(uu);
        //添加请求头
        httpPut.addHeader("Content-Type","application/json;charset=UTF-8");
        httpPut.addHeader("X-Authorization","Bearer"+" "+aa);
        httpPut.addHeader("Appid",appid);
        //封装请求参数，将map集合转换成json格式
        JSONObject jsonString = new JSONObject(map);
        //使用StringEntity转换成实体类型
        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
        //将封装的参数添加到Post请求中
        httpPut.setEntity(entity);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpPut);
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
