package com.test;
import com.alibaba.fastjson.JSONObject;
import com.utils.Sources;
import org.apache.xmlbeans.impl.jam.JSourcePosition;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author  贾子涵
 * @date 2022/9
 * @description 行者节测试用例
 */
@Test
public class TestGateWayService {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpJzhPost httpjzhpost = new HttpJzhPost();
    Map map = new HashMap();
    String bb = Sources.xz_master_base_url;
    String Au = Sources.xz_master_Authorization;
    @BeforeTest
    public void setup(){
        System.out.println("用例执行前执行");
    }
    @Test
    public void savereceiveraddress() throws IOException {
        //构造登录参数
        map.put("id",0);
        map.put("consignee","贾子涵1");
        map.put("consigneePhone","13717888314");
        map.put("province",110000);
        map.put("provinceName","北京市");
        map.put("city",110100);
        map.put("cityName","北京市");
        map.put("county",110101);
        map.put("countyName","东城区");
        map.put("addressDetail","子涵api新区(东城区北京瑞安宾馆东北(正义路)");
        map.put("isDefault",true);
        map.put("consignee_phone","13717888314");
        String uu = bb+"/ReceiverAddress/savereceiveraddress";
        String aa = Au;
        JSONObject result = httpjzhpost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }
    @Test
    public void getadvertisementimage() throws Exception{
        //构造登录参数
        String uu= bb+"/Advertisement/getadvertisementimage?advertisementImageType=7";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }

    @Test
    public  void  getinfo兑换详情() throws Exception{

        String uu= bb+"/Personal/getinfo";
        String aa = Au;
        JSONObject result =httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }

    @Test
    public  void  gettaskinfo() throws Exception{
        String uu=bb+"/Contribution/gettaskinfo";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getactivityurl() throws Exception{
        String uu=bb+"/Activity/getactivityurl";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getjoblist() throws Exception{
        String uu=bb+"/Personal/getjoblist";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getquestions() throws Exception{
        String uu=bb+"/FeedBack/getquestions?page_index=1&page_size=20";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getexchangelist() throws Exception{
        String uu=bb+"/WelfareGoods/getexchangelist";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getmyapplycourselist() throws Exception{
        String uu=bb+"/Skill/getmyapplycourselist";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public  void  getallcity() throws Exception{
        String uu=bb+"/ReceiverAddress/getallcity";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public  void  getallreceiveraddress() throws Exception{
        String uu=bb+"/ReceiverAddress/getallreceiveraddress";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getcontributiondeaillist() throws Exception{
        String uu=bb+"/Contribution/getcontributiondeaillist?page_index=1&page_size=20";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @AfterTest
    public void teardown(){

        System.out.println("用例执行完后执行");
    }
}