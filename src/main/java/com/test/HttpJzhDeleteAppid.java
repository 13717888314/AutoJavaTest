package com.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

public class HttpJzhDeleteAppid {
    //Delete方法
    public JSONObject JzhDelete(String uu,String aa,String appid) throws IOException
    {

        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Delete请求
        HttpDelete httpDelete = new HttpDelete(uu);
        //添加请求头
        httpDelete.addHeader("Content-Type","application/json;charset=UTF-8");
        httpDelete.addHeader("X-Authorization","Bearer"+" "+aa);
        httpDelete.addHeader("Appid",appid);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpDelete);
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
