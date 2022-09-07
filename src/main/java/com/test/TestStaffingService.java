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
 * @description Staffing测试用例
 */
@Test
public class TestStaffingService {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpJzhPost httpjzhpost = new HttpJzhPost();
    Map map = new HashMap();
    String bb = Sources.staffing_master_base_url;
    String Au = Sources.staffing_master_Authorization;
    @BeforeTest
    public void setup(){
        System.out.println("用例执行前执行");
    }
    @Test
    public void 我的报名信息() throws IOException {
        //构造登录参数
        map.put("pageindex",1);
        map.put("pagesize",15);
        map.put("status",0);
        map.put("pagetype",1);
        String uu=bb+"/v130/TaskSign/querytasksignlist";
        String aa = Au;
        JSONObject result = httpjzhpost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }
    @Test
    public void getadvertisementimage() throws Exception{
        //构造登录参数
        String uu= bb+"/freesalary/Advertisement/getadvertisementimage?adType=3";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }

    @Test
    public  void  协议gettaskagreements() throws Exception{
        String uu=bb+"/vAuthentication/Agreement/gettaskagreements";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  issubscribewxservice服务号() throws Exception{
        String uu=bb+"/Personal/issubscribewxservice?miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  个人信息() throws Exception{
        String uu=bb+"/v531/Personal/getinfo?miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  任务列表() throws Exception{
        String uu=bb+"/Task/getlist?page_index=1&page_size=15";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  任务详情() throws Exception{
        String uu=bb+"/Task/gettask?taskId=76864df2-dd7b-4b30-b86b-95a2a2bf88b2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  合作详情已结束() throws Exception{
        String uu=bb+"/v101/TaskSign/gettaskcooperatedetail?taskSignId=c616bc8e-c087-4d70-82fe-32c18a6cbcea";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public  void  我的签约信息() throws Exception{
        String uu=bb+"/v531/Agreement/getsignsettleagreementlist?miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public  void  v531Getinfo() throws Exception{
        String uu=bb+"/v531/Personal/getinfo?miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  报名详情() throws Exception{
        String uu=bb+"/TaskSign/gettasksigndetail?taskSignId=61272516-f8bf-48bf-9128-bfc67a72d58b";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getpersonalsummaryInfo() throws Exception{
        String uu=bb+"/freesalary/Personal/getpersonalsummaryInfo";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  gettaskfavorite() throws Exception{
        String uu=bb+"/freesalary/Personal/gettaskfavorite?page_index=1&page_size=15&latitude=0&longitude=0";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getbankcards() throws Exception{
        String uu=bb+"/freesalary/BankCardAuth/getbankcards";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }


    @Test
    public  void  addbankcards() throws Exception{
        map.put("cardNumber","6212260200086033656");
        map.put("isDefault","true");
        String uu=bb+"/freesalary/BankCardAuth/addbankcards";
        String aa = Au;
        JSONObject result = httpjzhpost.JzhPost(map,uu,aa);
        Assert.assertNotEquals(result.get("ActionResult"),"1","成功");
    }




    @AfterTest
    public void teardown(){

        System.out.println("用例执行完后执行");
    }
}
