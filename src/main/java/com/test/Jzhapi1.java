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

public class Jzhapi1 {
    public static void main(String[] args) throws Exception{
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String uu= "https://gateway-service-ts.wetax.com.cn/walkerfestivalapi-service/api/ReceiverAddress/getallreceiveraddress";
        String aa = "bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjQ1MjkxMzQsImV4cCI6MTY2NTUyOTEzNCwiaXNzIjoiaHR0cDovLzEwLjMwLjExMi4zOSIsImF1ZCI6ImFwaTEiLCJzdWIiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJ1c2VyaWQiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJzZXNzaW9uX2tleSI6Ijg1UU1vbkQ3NDc2QXpaQVJCWC9BN2c9PSJ9.IhJ69t20MW6pX7tfnDX2FplLCHPrw6BHzRTPT_OcZppKT1OFRLfOLHELDi6geJrcI0YcNgVq2a0VNMSpGugn_SmBjjgy251xUWpblqAiPOsyjYgOZXr4RH3uYeOhgrpCZIU99mlm1bN-kBRZcSmup3pg-ua1zVqdb94aLMfT-ncphCt6NzJByXWU_AcZbrVIbF" +
                "Lii_49yETVjiufdUWEpkm6np2fUwyJ2PUXuQiX2LTHSpjasOdWCOo0l3Dm2yoOgZWXiRn-wYft_W0E-UXwZ2UFJpiEUbLwwzZ5fpLUJbjnW4_5i5qx0ybyY49jY4qF_EkE-r9IsKCIaoLfjDgONw";
        //创建Post请求
        HttpGet httpGet = new HttpGet(uu);
        //添加请求头
        httpGet.addHeader("Content-Type","application/json;charset=UTF-8");
        httpGet.addHeader("Connection","keep-alive");
        httpGet.addHeader("Authorization",aa);

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
        System.out.println("接口返回结果"+ResBody);
        String ass = JSON.toJSONString(jsonObject.getJSONArray("Data").getJSONObject(0).get("id"));
        System.out.println(ass);
    }
}