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
public class TestKwwCYService {
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
    public static String userid;
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
    public  void v1WeappManagerApplyContentLimit() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/apply/content-limit?id="+appid+"&type=12";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 4)
    public  void 邀请v1WeappInviteMember() throws Exception{
        map.put("invitePhone","13717888312");
        map.put("role",4);
        String uu="https://open.openwhy.net/api/v1/weapp/invite-member";
        String aa = token;
        JSONObject result= httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 5)
    public  void v1MobileWeappUserSearch() throws Exception{
        map.put("keywords","");
        String uu="https://openwhy.net/api/v1/mobile/weapp/user/search?page=1";
        String aa = token;
        JSONObject result= httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        userid = (String) result.getJSONArray("datas").getJSONObject(2).get("id");
    }

    @Test(priority = 6)
    public  void 权限变更v1WeappManagerUserUpdateRole() throws Exception{
        System.out.println("userid;::"+userid);
        map.put("userId",userid);
        map.put("roleId",3);
        String uu="https://openwhy.net/api/v1/weapp/manager/user/update_role";
        String aa = token;
        JSONObject result= httpJzhPut.JzhPut(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 7)
    public  void 删除成员v1WeappManagerDeleteUser() throws Exception{
        String uu="https://openwhy.net/api/v1/weapp/manager/delete-user?userId="+userid;
        String aa = token;
        JSONObject result= httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


