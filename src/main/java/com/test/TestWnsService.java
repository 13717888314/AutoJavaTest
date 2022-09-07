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
 * @description Wns测试用例
 */
@Test
public class TestWnsService {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpJzhPost httpjzhpost = new HttpJzhPost();
    Map map = new HashMap();
    String bb = Sources.wns_master_base_url;
    String Au = Sources.wns_master_Authorization;
    @BeforeTest
    public void setup(){
        System.out.println("用例执行前执行");
    }

    @Test
    public void 服务号ssubscribewxservice() throws Exception{
        //构造登录参数
        String uu= bb+"/Personal/issubscribewxservice";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }

    @Test
    public  void  getcontent() throws Exception{

        String uu= bb+"/vEWallet/Personal/getcontent?miniProgram=1&version=V581";
        String aa = Au;
        JSONObject result =httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }

    @Test
    public  void  获取个人信息() throws Exception{
        String uu=bb+"/vEWallet/Personal/getinfo";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  结算收入() throws Exception{
        String uu=bb+"/PersonalSettlement/getbillbasicinfo?miniProgram=1";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getnotsettleinfo() throws Exception{
        String uu=bb+"/PersonalSettlement/getnotsettleinfo?miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getsettlementscount() throws Exception{
        String uu=bb+"/PersonalSettlement/getsettlementscount?miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  打款失败订单() throws Exception{
        String uu=bb+"/PersonalSettlement/getlist?month=&type=1&status=6&pageIndex=1&pageSize=15&miniProgram=2";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  我的签约wns() throws Exception{
        String uu=bb+"/vQingDao/Agreement/getsignsettleagreementlist";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public  void  getidcardinfo() throws Exception{
        String uu=bb+"/v1806/Personal/getidcardinfo";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @Test
    public  void  批量结算列表() throws Exception{
        String uu=bb+"/PersonalSettlement/getbatchsettlelist?payway=0&miniProgram=1";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  getmobiles() throws Exception{
        String uu=bb+"/Mobile/getmobiles";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }

    @Test
    public  void  Merchant合作商户() throws Exception{
        String uu=bb+"/Merchant/getteamworks?page_index=1&page_size=10";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
    }
    @AfterTest
    public void teardown(){

        System.out.println("用例执行完后执行");
    }
}