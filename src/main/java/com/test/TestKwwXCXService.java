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
public class TestKwwXCXService {
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
    public  void  apiV1WxThirdPartyWeappGetFailPop() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/get-fail-pop?appid="+appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 4)
    public  void  apiV1WxThirdPartyHomeClassid() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/home-classid?appType=2";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 5)
    public  void  Getv1WeappApplyFooterCategory() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/apply/footer-category?appid="+appid+"&appType=2";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 6)
    public  void  v1WeappManagerHeaderNav() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/header-nav?appType=2";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 7)
    public  void  Postv1WeappApplyFooterCategory() throws Exception{
        Map map1 = new HashMap();
        map1.put("name","首页");
        map1.put("iconName","iconbuttom-icon1");
        map1.put("orderIdx",0);
        map1.put("linkType",1000);
        map1.put("itemName","首页");

        Map map2 = new HashMap();
        map2.put("name","消息");
        map2.put("iconName","iconbuttom-icon3");
        map2.put("orderIdx",1);
        map2.put("linkType",40);
        map2.put("itemName","jzh新增专栏1111操作专栏设计起");
        map2.put("classId","1671402490182111232");

        Map map3 = new HashMap();
        map3.put("name","我的");
        map3.put("iconName","iconbuttom-icon4");
        map3.put("orderIdx",2);
        map3.put("linkType",40);
        map3.put("itemName","专栏44444");
        map3.put("classId","1671404616237060096");

        ArrayList arrayList = new ArrayList<>();
        arrayList.add(map1);
        arrayList.add(map2);
        arrayList.add(map3);

        JSONObject jsonString1 = new JSONObject(map1);
        JSONObject jsonString2 = new JSONObject(map2);
        JSONObject jsonString3 = new JSONObject(map3);

        String str= "["+jsonString1+","+jsonString2+","+jsonString3+"]";

        String uu="https://open.openwhy.net/api/v1/weapp/apply/footer-category?appid="+appid+"&appType=2";
        JSONObject result = httpJzhPostAppidArryList.JzhPost(str,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 8)
    public  void  v1WxThirdPartyHomeClassidAppType2() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/home-classid?appType=2";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 9)
    public  void  小程序详情v1WxThirdPartyWeappAppidDetail() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/"+appid+"/detail";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }



    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


