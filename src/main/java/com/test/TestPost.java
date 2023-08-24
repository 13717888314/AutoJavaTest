package com.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.record.Record;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPost {
    public static void main(String[] args) throws Exception{
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost("https://openwhy.net/api/auth/login");
        //添加请求头
        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.addHeader("Origin","https://openwhy.net");
        httpPost.addHeader("Host","openwhy.net");
        httpPost.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 ");
//        httpPost.addHeader("appid","1670632752518664192");
//        httpPost.addHeader("X-Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzcxNzg4ODMxNCIsImp0aSI6IjE2NzA2MzI1Mjk3NzM1NTE2MTYiLCJzY29wZXMiOlsiLzpHRVQiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbndoeS5jbiIsImlhdCI6MTY4NzMyNTYyOCwiZXhwIjoxNjg5OTE3NjI4fQ.kmSAsnRRnwjE-z0n1yr_BFAPvZpysQvOWGxRMx1DH1m07SUvwkczG1aWLko7SyGCZq3NGlaqGygBtrbZx5Y9pg");

        //表单请求传参
        List<BasicNameValuePair> pairList= new ArrayList<BasicNameValuePair>();
        pairList.add(new BasicNameValuePair("username","13717888314"));
        pairList.add(new BasicNameValuePair("password","1q2w3e4r5t6y"));
        httpPost.setEntity(new UrlEncodedFormEntity(pairList,"utf-8"));

       // 封装请求参数
//        Map map = new HashMap();
//        map.put("username","13717888314");
//        map.put("password","1q2w3e4r5t6y");
//        JSONObject jsonString = new JSONObject(map);
//        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
//        //将封装的参数添加到Post请求中
//        entity.setContentEncoding("UTF-8");
//        httpPost.setEntity(entity);
        //执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //获取响应的实体
        HttpEntity responseEntity = response.getEntity();
        //转化成字符串
        String entityString = EntityUtils.toString(responseEntity);
        //转换成JSON格式输出
        JSONObject jsonObject = JSONObject.parseObject(entityString);
        String ResBody = JSON.toJSONString(jsonObject,SerializerFeature.PrettyFormat);
        //打印返回结果
//      jsonObject.get("code")
        System.out.println(jsonObject.get("code"));
        System.out.println("Http状态码："+response.getStatusLine().getStatusCode());
        System.out.println(ResBody);
        //System.out.println(jsonObject.get("datas"));

//        String token = (String) jsonObject.getJSONObject("datas").get("token");
//        System.out.println("token:"+token);

//        Object sysTime = jsonObject.get("sysTime");
//        System.out.println("systime:"+sysTime);
//
//        String url1 = "https://openwhy.net/api/v1/persons/isRealnameAuth?_="+sysTime;
//        System.out.println(url1);

//        String appid = (String) jsonObject.getJSONObject("datas").getJSONArray("applyList[0]").get(Integer.parseInt("id"));
//        System.out.println(appid);

  //      List<Record> rlist = JSON.parseArray(jsonObject.getString("token"),Record.class);
//        getValueByPath gbj = new getValueByPath();
//        String str1 = gbj.getValueByJPath(jsonObject, "datas[0]/token");
//        System.out.println(str1);
    }
}