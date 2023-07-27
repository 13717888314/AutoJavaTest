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
public class TestKwwWzService {
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
    public  void  首页设计homeClassidGetDatasID() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/home-classid?appType=1";
        String aa = token;
        String apd= appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        classdataid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 4)
    public  void  classLayoutTypePut() throws Exception{
        Map map1 = new HashMap<>();
        map1.put("id",classdataid);
        map1.put("layoutType",1);
        map1.put("flowType",1);
        System.out.println("map::"+map1);
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class/layout-type";
        String aa = token;
        String apd= appid;
        JSONObject result = httpJzhPutAppidArryList.JzhPut(map1,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 5)
    public  void  classNavStyle() throws Exception{
        map.put("navStyleType",1);
        map.put("navStyleActiveBgColor","#387eff");
        map.put("navStyleActiveFontColor","#333333");
        map.put("id",classdataid);
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class/nav-style";
        String aa = token;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 6)
    public  void  classSearchBarOpen() throws Exception{
        map.put("id",classdataid);
        map.put("searchBarOpen",1);
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class/search-bar-open";
        String aa = token;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }



    @Test(priority = 7)
    public  void  classNavLayoutType() throws Exception{
        map.put("id",classdataid);
        map.put("navLayoutType",1);
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class/nav-layout-type";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 8)
    public  void  NewDesignerAllData() throws Exception{
        Map map1 = new HashMap();
        map1.put("itemId","1673903903707254784");
        map1.put("itemType",3);
        Map map2 = new HashMap();
        map2.put("itemId","1673600586984775680");
        map2.put("itemType",3);
        Map map3 = new HashMap();
        map3.put("itemId","1672841132362227712");
        map3.put("itemType",3);
        Map map4 = new HashMap();
        map4.put("itemId","1673232776717012992");
        map4.put("itemType",3);
        Map map5 = new HashMap();
        map5.put("itemId","1671408804736143360");
        map5.put("itemType",3);
        Map map6 = new HashMap();
        map6.put("itemId","1671408635533725696");
        map6.put("itemType",3);
        ArrayList arry1= new ArrayList();
        arry1.add(map1);
        arry1.add(map2);
        arry1.add(map3);
        arry1.add(map4);
        arry1.add(map5);
        arry1.add(map6);

        map.put("firstClassId",classdataid);
        map.put("dataList", arry1);

        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class/new-designer/all-data";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 9)
    public  void  NavLayoutType() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/apply/footer-category?appid="+appid+"&appType=1";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 10)
    public  void  headerNavGetId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/header-nav?appType=1";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        navid = (String) result.getJSONObject("datas").get("id");
    }


    @Test(priority = 11)
    public  void  footerCategor() throws Exception{
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

        String uu="https://open.openwhy.net/api/v1/weapp/apply/footer-category?appid="+appid+"&appType=1";
        JSONObject result = httpJzhPostAppidArryList.JzhPost(str,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 12)
    public  void  headerNav() throws Exception{
        map.put("id",navid);
        map.put("navOpen",0);
        map.put("appid","1670632752518664192");
        map.put("appType",1);
        map.put("link","");
        map.put("itemId","");
        map.put("itemName","");

        String uu="https://open.openwhy.net/api/v1/weapp/manager/header-nav";

        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }


    @Test(priority = 13)
    public  void  模板库homeClassid() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/home-classid?appType=1";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        homeid = (String) result.getJSONObject("datas").get("homeId");
    }

    @Test(priority = 14)
    public  void  模板templateStandardList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/template-standard/list";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }


    @Test(priority = 15)
    public  void  模板使用emplateStandard() throws Exception{
        map.put("id",1);
        map.put("homeId",homeid);
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/template-standard/use";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }


    @Test(priority = 16)
    public  void  userTypeList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/home-edu/user/type-list";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 17)
    public  void  noticeUnreadNum() throws Exception{
        String uu="https://openwhy.net/api/v2/wx/third-party/weapp/notice/unread-num";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 18)
    public  void  weappLeftMenuData() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/left-menu-data";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }


    @Test(priority = 19)
    public  void  skinList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/skin/list?homeId="+homeid;
        String tok = token;
        String apd = appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,tok,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 20)
    public  void  weappSetSkin() throws Exception{
        map.put("homeId",homeid);
        map.put("skinId","1016");
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/set-skin";
        String tok = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,tok,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 21)
    public  void  手机网站详情websiteAppidDetail() throws Exception{
        String apd = appid;
        String uu="https://open.openwhy.net/api/v1/website/"+apd+"/detail";
        String tok = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,tok,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 22)
    public  void  手机网站详情保存apiV1WebsiteNicknameAndImg() throws Exception{
        map.put("nickName","api创建test网站");
        map.put("appImg","//r.openwhy.cn/activity-top-img-synthesize-28.png");
        map.put("appSignature","api创建test网站api创建test网站api创建test网站");
        String uu="https://open.openwhy.net/api/v1/website/nickname-and-img?ver=2";
        String tok = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,tok,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }



    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


