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

public class TestGet {
    public static void main(String[] args) throws Exception{
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpGet httpGet = new HttpGet("https://gateway-service-ts.wetax.com.cn/walkerfestivalapi-service/api/Advertisement/getadvertisementimage?advertisementImageType=5");
        //添加请求头
        httpGet.addHeader("Content-Type","application/json;charset=UTF-8");
        httpGet.addHeader("Connection","keep-alive");
        httpGet.addHeader("Authorization","bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjI0MjYwNDYsImV4cCI6MTY2MzQyNjA0NiwiaXNzIjoiaHR0cDovLzEwLjMwLjExMi4zOSIsImF1ZCI6ImFwaTEiLCJzdWIiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJ1c2VyaWQiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJzZXNzaW9uX2tleSI6IlR0UVBtY3U3QVM0cmNBM0IwbkZ1R3c9PSJ9.PbdHWSxdbpix7iXUDBp3LJuxHwutZ3TQVNux-eqy2PA66TMLTR3rEVwwr7IAtcZc3cHFMUkPjNgGDLcu_qbSbcH9C_wLUGEyDjXNuyk3WhMjoGKUwPnVu9Q8mjAfc9k04CGE4EJRPdyvnJsYAGgGbMDs5WZz9LqV4mNIe87fmCkSzwwqvreDgE5dbfkM7a44wmbkArCSVV-Y9XwOSiidAMwMW1KTewhg7yw4xFiOOhPvv4GONqClYvggBD" +
                "ffXPXUAlDoLI-etRoWh6vNLtQGO3y7rzBwo4aWpdFVxDPcDLdg4aGg3geseztG-vWg-79qAtnxRBCN4ZmtOXXs118AfQ");

//        JSONObject jsonString = new JSONObject(map);
//        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
//        //将封装的参数添加到Post请求中
//        httpPost.setEntity(entity);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpGet);
        //获取响应的实体
        HttpEntity responseEntity = response.getEntity();
        //转化成字符串
        String entityString = EntityUtils.toString(responseEntity);
        //转换成JSON格式输出
        JSONObject jsonObject =  JSONObject.parseObject(entityString);
        String ResBody = JSON.toJSONString(jsonObject,SerializerFeature.PrettyFormat);
        //打印返回结果
//        jsonObject.get("code")
        System.out.println(jsonObject.get("ActionResult"));
        System.out.println(ResBody);

    }
}