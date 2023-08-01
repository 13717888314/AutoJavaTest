package com.test;

import com.alibaba.fastjson.JSONObject;
import com.utils.Sources;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.*;


@Test
public class TestKwwZyzSzService {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpFromPost httpKww = new HttpFromPost();
    HttpJzhGetAppid httpJzhGetAppid = new HttpJzhGetAppid();
    HttpJzhPut httpJzhPut = new HttpJzhPut();
    HttpJzhPostAppid httpJzhPostAppid = new HttpJzhPostAppid();
    HttpJzhDeleteAppid httpJzhDeleteAppid = new HttpJzhDeleteAppid();
    HttpJzhPutAppidArryList httpJzhPutAppidArryList = new HttpJzhPutAppidArryList();
    HttpJzhPostAppidArryList httpJzhPostAppidArryList = new HttpJzhPostAppidArryList();
    Date now = new Date();
    long time = now.getTime();
    Map map = new HashMap();
    HttpJzhPost httpJzhPost = new HttpJzhPost();
    public static String token;
    public static String appid;
    public static String classdataid;
    public static Object sysTime;
    public static String navid;
    public static String homeid;
    public static String goodsid;
    public static String xcid;
    public static String zlid;
    List<BasicNameValuePair> pairList= new ArrayList<BasicNameValuePair>();
    String bb = Sources.kww_test_base_url;
//    String Au = Sources.staffing_master_Authorization;
    @BeforeTest
    public void setup(){
        System.out.println("用例执行前执行");
    }

    @Test(priority = 1)
    public void getToken() throws IOException {
        //构造登录参数
        pairList.add(new BasicNameValuePair("username","13717888314"));
        pairList.add(new BasicNameValuePair("password","1q2w3e4r5t6y"));
        String uu=bb+"/api/auth/login";
        JSONObject result = httpKww.JzhPost(pairList,uu);
        Assert.assertEquals(result.get("code"),"000000","成功");
        token = (String) result.getJSONObject("datas").get("token");
        sysTime = result.get("sysTime");
        System.out.println("token:::"+token);
        System.out.println(result);
    }

    @Test(priority = 2)
    public  void applylistToGetappid() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/apply/list";
        String aa = token;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
        appid = (String)result.getJSONObject("datas").getJSONArray("applyList").getJSONObject(0).get("id");
    }

    @Test(priority = 3)
    public  void  志愿者ListweappManagerVolVolList() throws Exception{
        map.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/vol/vol-list?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 4)
    public  void  志愿活动ListweappManagerVolActivityList() throws Exception{
        map.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/vol/activity-list?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 5)
    public  void  志愿组织ListWeappManagerVolOrgList() throws Exception{
        map.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/vol/org-list?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 6)
    public  void  操作日志ListweappOperationLogSearch() throws Exception{
        map.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/operation-log/search?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


