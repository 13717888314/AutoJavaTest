package com.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class jzhapi2 {
    //POST登录方法
    public JSONObject JzhGet(String uu,String aa) throws IOException
    {

        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpGet httpGet = new HttpGet(uu);
        //添加请求头
        httpGet.addHeader("Content-Type","application/json;charset=UTF-8");
        httpGet.addHeader("Authorization",aa);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpGet);
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