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
public class TestKwwJXService {
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
    public static String bjid;
    public static String jsid;
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
    public  void 班级管理创建weappManagerClassCreateInfo() throws Exception{
        map.put("classNo","001");
        map.put("educationLevel","小学");
        map.put("gradeName","一年级");
        map.put("className","一班");
        map.put("classNum","13");
        map.put("appid",appid);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/create-info";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        bjid = (String) result.getJSONObject("datas").get("id");
    }


    @Test(priority = 4)
    public  void 班级添加班主任weappManagerClassisManager1() throws Exception{
        map.put("isManager",1);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("1679696781681758208");

        map.put("teacherIdList",arrayList);

        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/"+bjid+"/teachers";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 5)
    public  void 班级添加教师weappManagerClassisManager0() throws Exception{
        map.put("isManager",0);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("1683754511056900096");
        map.put("teacherIdList",arrayList);

        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/"+bjid+"/teachers";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 6)
    public  void 班级中角色listManagerClassAllMembers() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/"+bjid+"/all-members";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 7)
    public  void 班级管理通知weappClassNotifys() throws Exception{
        map.put("page",1);
        map.put("role",2);
        map.put("type",3);
        map.put("queryType","0");
        map.put("dateType","0");
        ArrayList arrayList = new ArrayList<>();
        arrayList.add(bjid);
        map.put("classInfoIdList",arrayList);

        String uu="https://open.openwhy.net/api/v1/weapp/class/notifys?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 8)
    public  void 班级管理ListManagerClassClassInfosPage() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/class-infos?page=1&appid="+appid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 9)
    public  void 班级管理删除班级weappManagerClassClassInfoId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/class-info?id="+bjid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 10)
    public  void 教师管理添加教师weappManagerClassTeacher() throws Exception{
        map.put("code","09");
        map.put("name","小黑");
        map.put("phone","13717555695");
        map.put("appid",appid);
        ArrayList arrayList = new ArrayList<>();
        arrayList.add("劳动");
        map.put("subjectList",arrayList);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/teacher";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        jsid = (String)result.getJSONObject("datas").get("id");
    }
    @Test(priority = 11)
    public  void 教师管理删除教师weappManagerClassTeacherId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/teacher?id="+jsid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 12)
    public  void 教师管理LISTmanagerClassTeachers() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/teachers?page=1&appid="+appid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 13)
    public  void 发布通知weappManagerClassNotifyNotice() throws Exception{
        map.put("title","发布通知api");
        map.put("content","发布通知api发布通知api发布通知api");
        map.put("expireTime","1724428740000");
        ArrayList arrayList1 = new ArrayList<>();
        map.put("classInfoIdList",arrayList1);
        ArrayList arrayList2 = new ArrayList<>();
        arrayList2.add("1679696781681758208");
        arrayList2.add("1683754609996337152");
        arrayList2.add("1679696781681758208");
        arrayList2.add("1683754511056900096");
        arrayList2.add("1679696781681758208");
        map.put("teacherIdList",arrayList2);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/notify/notice";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 14)
    public  void 发布通知ListWeappManagerClassNotifyList() throws Exception{
        map.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/class/notify/list?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


