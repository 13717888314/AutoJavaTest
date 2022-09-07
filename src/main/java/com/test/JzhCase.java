package com.test;
import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lh
 * @date 2020/6/8
 * @description 登录测试用例
 */
@Test
public class JzhCase {    //调用post请求
    HttpClientJzhPost httpClientJzhPost = new HttpClientJzhPost();
    HttpClientJzhGet httpClientJzhGet = new HttpClientJzhGet();
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpJzhPost httpjzhpost = new HttpJzhPost();
    Map map = new HashMap();
    @BeforeTest
    public void setup(){
        System.out.println("用例执行前执行");
    }
    @Test
    public void test1() throws IOException {
        //构造登录参数
        map.put("name","贾子涵");
        map.put("idNumber","110102198707132374");
        map.put("phoneNumber","13717888314");
        map.put("merchantId","0c949a6c-1daa-4a27-b021-b4af60352108");
        map.put("serialNum","12345671289976578");
        map.put("certificateType",1);
        map.put("orderRandomCode","d6e6700a042e4f788df02aec5b809760");
        String uu = "";
        String aa= "";
        JSONObject result = httpjzhpost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public void test2() throws Exception{
        //构造登录参数
        JSONObject result = httpClientJzhGet.JzhGet();
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  test3() throws Exception{

        String uu= "https://gateway-service-ts.wetax.com.cn/walkerfestivalapi-service/api/Advertisement/getadvertisementimage?advertisementImageType=5";
        String aa= "bearer eyJhbGciOiJSUzI1NiIsImtpZCI6IkJFM0Q0NDE1NDE5RjA1MjdCNUJGNzIzRTZBQzMwNTUxMEE3N0QzOTYiLCJ0eXAiOiJKV1QiLCJ4NXQiOiJ2ajFFRlVHZkJTZTF2M0ktYXNNRlVRcDMwNVkifQ.eyJuYmYiOjE2NjI0MjYwNDYsImV4cCI6MTY2MzQyNjA0NiwiaXNzIjoiaHR0cDovLzEwLjMwLjExMi4zOSIsImF1ZCI6ImFwaTEiLCJzdWIiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBj" +
                "NTEwNDQiLCJ1c2VyaWQiOiI4MTNkMWRmZC0zMmNjLTQ2ZmQtYjZkOS1mYjcxNzBjNTEwNDQiLCJzZXNzaW9uX2tleSI6IlR0UVBtY3U3QVM0cmNBM0IwbkZ1R3c9PSJ9.PbdHWSxdbpix7iXUDBp3LJuxHwutZ3TQVNux-eqy2PA66TMLTR3rEVwwr7IAtcZc3cHFMUkPjNgGDLcu_qbSbcH9C_wLUGEyDjXNuyk3WhMjoGKUwPnVu9Q8mjAfc9k04CGE4EJRPdyvnJsYAGgGbMDs5WZz9LqV4mNIe87fmCkSzwwqvreDgE5dbfkM7a44wmbkArCSVV-Y9XwOSiidAMwMW1KTewhg7yw4xFiOOhPvv4GONqClYvggBDffXPXUAlDoLI-etRoWh6vNLtQGO3y7rzBwo4aWpdFVxDPcDLdg4aGg3geseztG-vWg-79qAtnxRBCN4ZmtOXXs118AfQ";
        JSONObject result =httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }


    @AfterTest
    public void teardown(){

        System.out.println("用例执行完后执行");
    }
}