package com.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestPost {
    public static void main(String[] args) throws Exception{
        //创建一个获取连接客户端的工具
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建Post请求
        HttpPost httpPost = new HttpPost("https://wsapis-ts.wetax.com.cn/api/Account/getUrl");
        //添加请求头
        httpPost.addHeader("Content-Type","application/json;charset=UTF-8");
        httpPost.addHeader("Connection","keep-alive");
//        httpPost.addHeader("Authorization","bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjE1MDU1ODIsImV4cCI6MTY2MjUwNTU4MiwiaXNzIjoiaHR0cDovLzEwLjMwLjUuMTk6MzcwMDMiLCJhdWQiOiJhcGkxIiwic3ViIjoiZWRiOTlhYWYtMjc4MC00NmIzLTgzMzgtYWI3NDdkYzBjMDUxIiwidXNlcmlkIjoiZWRiOTlhYWYtMjc4MC00NmIzLTgzMzgtYWI3NDdkYzBjMDUxIiwic2Vzc2lvbl9rZXkiOiJSbGpqakZUcVYwUC83UDRFZHRNMnd3PT0ifQ.b7sAQePK1cteSgFx8nKm04hTZJFY-NAw9nvJaoRtDW0dtcRPcysNcZ1cE6sp8XLHshSYvuGK11zuoKRaKoppEn-J_2FaQZSm71HHj6oFq1K6QiHsclB1tQnIV2qzhL2M8PoacqbeaecKP_EJ8qDzDAs-mNmDPnd_lSZtc7UeZyUZZbrMq8FzV1-hqIcGV63E1LkPEeHqi2sOUrrhU9kFt1ioSL2LLxmKsVmt3gjLbGIuJr_HPVHhYmrlCr5kMW1lNj0VdwV42DBJEE6_ljCkL4bM02U6yuRcSKZb5OgZEVbD9EmCwxOAFO0U0Za31O4H-taHwtofWnPb_W-TYgym9Q");

        //封装请求参数
        Map map = new HashMap();
        map.put("name","贾子涵");
        map.put("idNumber","110102198707132374");
        map.put("phoneNumber","13717888314");
        map.put("merchantId","0c949a6c-1daa-4a27-b021-b4af60352108");
        map.put("serialNum","12345671289976578");
        map.put("certificateType",1);
        map.put("orderRandomCode","d6e6700a042e4f788df02aec5b809760");

        JSONObject jsonString = new JSONObject(map);
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
        JSONObject jsonObject =  JSONObject.parseObject(entityString);
        String ResBody = JSON.toJSONString(jsonObject,SerializerFeature.PrettyFormat);
        //打印返回结果
//        jsonObject.get("code")
        System.out.println(jsonObject.get("ActionResult"));
        System.out.println(ResBody);

    }
}