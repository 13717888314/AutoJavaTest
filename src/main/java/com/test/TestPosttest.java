package com.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.gson.JsonArray;
import org.apache.commons.collections4.ListValuedMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.collections.ListMultiMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestPosttest {
    public static void main(String[] args) throws Exception{
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost("https://open.openwhy.net/api/v1/weapp/manager/query-info/search?page=1");

    //    HttpPut httpPut = new HttpPut("https://open.openwhy.net/api/v1/weapp/manager/quick-nav/setting");

        //添加请求头
//        httpPost.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
//        httpPost.addHeader("Origin","https://openwhy.net");
//        httpPost.addHeader("Host","openwhy.net");
//        httpPost.addHeader("Accept","application/json, text/javascript, */*; q=0.01");
//        httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 ");
 //       httpPost.addHeader("appid","1670632752518664192");
        httpPost.addHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.addHeader("X-Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzcxNzg4ODMxNCIsImp0aSI6IjE2NzA2MzI1Mjk3NzM1NTE2MTYiLCJzY29wZXMiOlsiLzpHRVQiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbndoeS5jbiIsImlhdCI6MTY5MjAwMjg0NSwiZXhwIjoxNjk0NTk0ODQ1fQ.ah4Uux_U1vscfJmp0ekbmdvs9xAiOcK5a-B4gQthLHDedPMiN3O9Bi5bmt2Iw9TKsLnMiah_ZIZDL1WaNj_GoQ");
        httpPost.addHeader("Appid", "1670632752518664192");
        //表单请求传参
//        List<BasicNameValuePair> pairList= new ArrayList<BasicNameValuePair>();
//        pairList.add(new BasicNameValuePair("username","13717888314"));
//        pairList.add(new BasicNameValuePair("password","1q2w3e4r5t6y"));
//        httpPost.setEntity(new UrlEncodedFormEntity(pairList,"utf-8"));
        Map map = new HashMap<>();
        map.put("","");

        JSONObject jsonString = new JSONObject(map);

        System.out.println("jsonsss::" + jsonString);
//        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
        //使用StringEntity转换成实体类型
        StringEntity entity = new StringEntity(jsonString.toString(), "UTF-8");
        System.out.println("entity::" + entity);
        //将封装的参数添加到Post请求中
        httpPost.setEntity(entity);
        //执行请求
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //获取响应的实体
        HttpEntity responseEntity = response.getEntity();
        //转化成字符串
        String entityString = EntityUtils.toString(responseEntity);
        //转换成JSON格式输出
        JSONObject result = JSONObject.parseObject(entityString);

        String ResBody = JSON.toJSONString(result, SerializerFeature.PrettyFormat);

        // String qzid = (String)result.getJSONArray("datas").getJSONObject(0).get("id");

        System.out.println(result);

       // System.out.println("qzid:::"+qzid);
       // System.out.println("resbody"+ResBody);
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