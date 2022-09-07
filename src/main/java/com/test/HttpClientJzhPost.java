package com.test;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.Map;
public class HttpClientJzhPost {
    //POST登录方法
    public JSONObject JzhPost(Map<String, Object> map) throws IOException
    {

        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost("https://wsapis-ts.wetax.com.cn/api/Account/getUrl");
        //添加请求头
        httpPost.addHeader("Content-Type","application/json;charset=UTF-8");
//        httpPost.addHeader("Authorization","bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjE1MDU1ODIsImV4cCI6MTY2MjUwNTU4MiwiaXNzIjoiaHR0cDovLzEwLjMwLjUuMTk6MzcwMDMiLCJhdWQiOiJhcGkxIiwic3ViIjoiZWRiOTlhYWYtMjc4MC00NmIzLTgzMzgtYWI3NDdkYzBjMDUxIiwidXNlcmlkIjoiZWRiOTlhYWYtMjc4MC00NmIzLTgzMzgtYWI3NDdkYzBjMDUxIiwic2Vzc2lvbl9rZXkiOiJSbGpqakZUcVYwUC83UDRFZHRNMnd3PT0ifQ.b7sAQePK1cteSgFx8nKm04hTZJFY-NAw9nvJaoRtDW0dtcRPcysNcZ1cE6sp8XLHshSYvuGK11zuoKRaKoppEn-J_2FaQZSm71HHj6oFq1K6QiHsclB1tQnIV2qzhL2M8PoacqbeaecKP_EJ8qDzDAs-mNmDPnd_lSZtc7UeZyUZZbrMq8FzV1-hqIcGV63E1LkPEeHqi2sOUrrhU9kFt1ioSL2LLxmKsVmt3gjLbGIuJr_HPVHhYmrlCr5kMW1lNj0VdwV42DBJEE6_ljCkL4bM02U6yuRcSKZb5OgZEVbD9EmCwxOAFO0U0Za31O4H-taHwtofWnPb_W-TYgym9Q");

        //封装请求参数，将map集合转换成json格式
        JSONObject jsonString = new JSONObject(map);
        //使用StringEntity转换成实体类型
        StringEntity entity = new StringEntity(jsonString.toString(),"UTF-8");
        //将封装的参数添加到Post请求中
        httpPost.setEntity(entity);
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