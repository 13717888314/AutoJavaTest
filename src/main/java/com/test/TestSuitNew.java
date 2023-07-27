package com.test;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.utils.Sources;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * @author  贾子涵
 * @date 2022/9
 * @description 行者节测试用例
 */
@Test
public class TestSuitNew {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpJzhPost httpjzhpost = new HttpJzhPost();
    Map map = new HashMap();
    String bb = Sources.xz_test_base_url;
    String Au = Sources.xz_test_Authorization;
    public static String ass;
    public static String bss;

    @BeforeTest
    public void setup(){
        System.out.println("用例执行前执行");
    }


    @Test
    public void savereceiveraddress() throws IOException {
        //构造登录参数
        map.put("id",0);
        map.put("consignee","贾子涵2");
        map.put("consigneePhone","13717888314");
        map.put("province",110000);
        map.put("provinceName","北京市");
        map.put("city",110100);
        map.put("cityName","北京市");
        map.put("county",110101);
        map.put("countyName","西城区");
        map.put("addressDetail","子涵apinewnew(正义路)");
        map.put("isDefault",true);
        map.put("consignee_phone","13717888314");
        String uu = bb+"/ReceiverAddress/savereceiveraddress";
        String aa = Au;
        JSONObject result = httpjzhpost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }


    @Test
    public void getallreceiveraddress() throws IOException{
        //构造登录参数
        String uu= bb+"/ReceiverAddress/getallreceiveraddress";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
        ass = JSON.toJSONString(result.getJSONArray("Data").getJSONObject(0).get("id"));
        System.out.println("getadvertisementimage接口中ass:"+ass);
    }

    public String getBss() throws IOException{
        String uu= bb+"/ReceiverAddress/getallreceiveraddress";
        String aa = Au;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");
        bss = JSON.toJSONString(result.getJSONArray("Data").getJSONObject(0).get("id"));
        System.out.println("getBss接口中bss:"+bss);
        return bss;
    }


    @Test
    public void deletereceiveraddress() throws IOException {
        //构造登录参数

        System.out.println("deletereceiver接口中bss为："+getBss());
        map.put("id",getBss());
        System.out.println("deletereceiver接口中ass为："+map);
        String uu = bb+"/ReceiverAddress/deletereceiveraddress";
        String aa = Au;
        JSONObject result = httpjzhpost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("ActionResult"),"1","成功");

    }



    @AfterTest
    public void teardown(){

        System.out.println("用例执行完后执行");
    }
}