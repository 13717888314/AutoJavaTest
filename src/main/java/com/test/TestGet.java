package com.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.collections4.Get;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TestGet {
    public static void main(String[] args) throws Exception{
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
    //    GetValueByPath getValueByPath1= new GetValueByPath();

        HttpGet httpGet = new HttpGet("https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/list?auditStatus=1&page=1");
        //添加请求头
        httpGet.addHeader("Content-Type","application/json;charset=UTF-8");
        httpGet.addHeader("Connection","keep-alive");
        httpGet.addHeader("X-Authorization","Bearer Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMzcxNzg4ODMxNCIsImp0aSI6IjE2NzA2MzI1Mjk3NzM1NTE2MTYiLCJzY29wZXMiOlsiLzpHRVQiXSwiaXNzIjoiaHR0cHM6Ly93d3cub3BlbndoeS5jbiIsImlhdCI6MTY4ODEyMTEzMSwiZXhwIjoxNj" +
                "kwNzEzMTMxfQ.hEdwlGGxq5YkoJa-p44FazDVH7UxUR5LKwF18HDZ9-WEZMgQRjSmH2OcNrXEpfa_F6_BvLNhjMa01SLoRof2-g");
        httpGet.addHeader("appid","1670632752518664192");
//        JSONObject jsonString = new JSONObject(map);
//        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
//        //将封装的参数添加到Post请求中
//        httpPost.setEntity(entity);
//
//        URL url = new URL("https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/list?auditStatus=1&page=1");
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        conn.connect();
//        int code = conn.getResponseCode();
//        System.out.println("urlCo::"+conn);
//        System.out.println("code:::"+code);
        //执行请求
        CloseableHttpResponse response =  httpClient.execute(httpGet);

        System.out.println("response::"+response);

        int statuscode = response.getStatusLine().getStatusCode();
        System.out.println("statuscode::"+statuscode);

        //获取响应的实体
        HttpEntity responseEntity = response.getEntity();
        //转化成字符串
        String entityString = EntityUtils.toString(responseEntity);
        //转换成JSON格式输出
     //   JSONObject jsonObject =  JSONObject.parseObject(entityString);
        JSONObject jsonObject = JSON.parseObject(entityString);
        System.out.println("jsonObject::"+jsonObject);


        String ResBody = JSON.toJSONString(jsonObject,SerializerFeature.PrettyFormat);
     //   System.out.println(ResBody);
       // String str1 = (String)jsonObject.getJSONObject("datas").getJSONArray("applyList").getJSONObject(0).get("id");
    //    String str1 =  getValueByPath1.getValueByJPath(jsonObject,"datas.applyList[0]/id");
        //打印返回结果
//        jsonObject.get("code")
     //   System.out.println(jsonObject.get("ActionResult"));
       // System.out.println(str1);
    }
}