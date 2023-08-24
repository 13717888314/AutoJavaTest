package com.test;

import com.alibaba.fastjson.JSONObject;
import com.utils.Sources;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@Test
public class TestKwwYYService {

    HttpClientGetCode httpClientGetCode = new HttpClientGetCode();
    JDBCTestSql jdbc = new JDBCTestSql();
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
    Map map = new HashMap<>();

    Map mapNew = new HashMap<>();
    HttpJzhPost httpJzhPost = new HttpJzhPost();
    public static String token;
    public static String appid;
    public static String classdataid;
    public static Object sysTime;
    public static String AIid;
    public static String NRid;
    public static String tgnrid;

    public static String hykid;
    public static String ffzkspid;
    public static String cxid;

    public static String idd = null;
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
        try {
            idd = jdbc.JDBCTestSql("select * from sys_user where nickname = 'jzh';");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("idd:::"+idd);
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
    public  void AI助手LISTweappManagerAiAssistantList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/list";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 4)
    public  void 创建AI助手weappManagerAiAssistant() throws Exception{
        map.put("name","apitest创建助手");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        AIid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 5)
    public  void AI内容库添加内容weappManagerAiAssistantLibrary() throws Exception{
        map.put("assistantId",AIid);
        map.put("itemType",1);
        ArrayList tiList = new ArrayList<>();
        tiList.add("1679743558184968192");
        map.put("itemIdList",tiList);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/library";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 6)
    public  void AI助手内容库ListWeappManagerAiAssistantLibraryList() throws Exception{
        map.put("page",1);
        map.put("id",AIid);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/library/list?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

        NRid = (String) result.getJSONArray("datas").getJSONObject(0).get("id");

    }

    @Test(priority = 7)
    public  void AI助手内容库内容同步managerAiAssistantUnsyncCountAssistantId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/unsync-count?assistantId="+AIid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 8)
    public  void 内容库删除managerAiAssistantLibraryNRID() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/library?id="+NRid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 9)
    public  void AI助手提问v2AiQuestionAnswer() throws Exception{
        map.put("sendMsg","你好");
        map.put("assistantId",AIid);
        String uu="https://open.openwhy.net/api/v2/ai-question-answer/msg-auth";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 10)
    public  void AI助手设置weappManagerAiAssistantDetail() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/"+AIid+"/detail";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 11)
    public  void 修改AI助手weappManagerAiAssistant() throws Exception{
        map.put("id",AIid);
        map.put("name","api创建助手2");
        map.put("avatar","https://r.openwhy.cn/ai-assistant-def.png");
        map.put("weclome","您好，欢迎使用AI助手功能，请问有什么可以帮助您？");
        map.put("questionTemplate","请基于我提供给你的内容来回答问题,如果提供的内容不清楚, 请诚实的回复不知道.我提供给你的内容是:  $KNOWLEDGE.我的问题是: $QUESTION");
        map.put("roleName","你是企业知识智能AI机器人");
        map.put("temperature",0.5);
        map.put("topP",0.8);
        map.put("createTime","1689325747000");
        map.put("updateTime","2023-07-14 17:09:07");
        map.put("creatorId","1670632529773551616");
        map.put("appid",appid);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant";
        String aa = token;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 12)
    public  void AI助手查看分析数据weappManagerAiAssistantQuestionAnswerList() throws Exception{
        map.put("page",1);
        map.put("id",AIid);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/question-answer/list?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 13)
    public  void AI助手回答点赞踩统计weappManagerAiAssistantQuestionAnswerLikeNumber() throws Exception{
        map.put("id",AIid);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant/question-answer/like-number";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 14)
    public  void 删除AI助手weappManagerAiAssistant() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/ai-assistant?id="+AIid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 15)
    public  void 订单查询managerMallOrdersSearch() throws Exception{
        map.put("orderStatus",null);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/mall/orders/search?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 16)
    public  void 推广员已通过审核LISTmanagerMarketCenterSalesListAuditStatus1() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/list?auditStatus=1&page=1";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 17)
    public  void 推广员待审核LISTmanagerMarketCenterSalesListAuditStatus2() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/list?auditStatus=1&page=1";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 18)
    public  void 推广内容LISTweappMarketCenterProductList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/market-center/product/list?page=1";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 19)
    public  void 添加推广内容weappManagerMarketCenterProduct() throws Exception{
        Map map1 = new HashMap<>();
        map1.put("itemType",40);
        map1.put("itemId","1680819760482357248");
        map1.put("firstScale","1");
        map1.put("secondScale","");
        map1.put("bindMode",0);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/product";
        System.out.println("token:::"+token);
        System.out.println("appid:::"+appid);
        System.out.println("map:::"+map1);
        JSONObject result = httpJzhPostAppid.JzhPost(map1,uu,token,appid);
        System.out.println("resu::"+result);
        Assert.assertEquals(result.get("code"),"000000","成功");
        tgnrid = (String)result.getJSONObject("datas").get("id");
    }

    @Test(priority = 20)
    public  void 编辑推广内容weappManagerMarketCenterProduct() throws Exception{
        Map map1 = new HashMap<>();
        map1.put("itemType",40);
        map1.put("itemId","1680819760482357248");
        map1.put("firstScale","2");
        map1.put("secondScale","");
        map1.put("bindMode",0);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/product";
        String aa = token;
        System.out.println("map1::"+map1);
        JSONObject result = httpJzhPostAppid.JzhPost(map1,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 21)
    public  void 删除推广内容managerMarketCenterProductId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/product?id="+tgnrid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 22)
    public  void 招募计划确认编辑weappManagerMarketCenterRecruitment() throws Exception{
        map.put("recruitment","<p>小伙伴您好，欢迎申请成为本小程序的推广员。<br/>成为推广员后，您在将优质内容分享给他人的同时，您可以获得一定的奖励。<br/><br/><b>一、奖励说明</b><br/>用户点击您分享的推广链接即会绑定成为您的客户，在有效期内购买成功，您将获得对应比例的佣金奖励。<br/>用户通过您分享的推广链接申请成为推广员的，其将成为您的推广团队成员，其获得销售佣金的同时，您也会获得奖励。<br/><br/><b>二、结算说明</b><br/>1.订单完成后，推广员可进行提现；<br/>2.您可以在我的推广中查看您的收益数据，并进行提现操作。<br/><br/><b>三、其他</b><br/>1.成为推广员需先注册为本小程序用户。<br/>2.申请及推广过程中与管理团队的联系方式：暂无。<br/>3.禁止传播扩散任何关于政治、色情等任何违法内容。一经发现．您将被直接移除推广员" +
                "身份。触犯任何法律相关问题，本小程序运营团队不负任何责任。<br/>4.以上所述内容解释权归本小程序运营团队所有,由小程序管理者代表行使。</p>");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/recruitment";
        String aa = token;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 23)
    public  void 提现管理未打款LISTweappManagerMarketCenterSalesWithdrawSearch() throws Exception{
        map.put("auditStatus",0);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/withdraw/search?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 24)
    public  void 提现管理已打款weappManagerMarketCenterSalesWithdrawSearchPage1() throws Exception{
        map.put("auditStatus",1);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/withdraw/search?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 25)
    public  void 提现管理已拒绝weappManagerMarketCenterSalesWithdrawSearchPage1() throws Exception{
        map.put("auditStatus",2);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/market-center/sales/withdraw/search?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 26)
    public  void 钱包流水记录LISTweappManagerWalletFlowSearch() throws Exception{
        map.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/wallet/flow/search?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 27)
    public  void 用户分类管理用户列表weappManagerCollectionResultSearch() throws Exception{
        map.put("auditStatus","-1");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/collection-result/search?page=1";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 28)
    public  void 用户列表导出数据weappManagerCollectionResultExport() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/collection-result/export";
        String aa = token;
        int code = httpClientGetCode.GetClientGetCode(uu,aa,appid);
        Assert.assertEquals(code,200,"成功");
    }
    @Test(priority = 29)
    public  void 采集信息设置weappManagerCollectionConfig() throws Exception{
        map.put("isAudit",0);
        map.put("desc","<p>api测试采集</p>");
        ArrayList arrayList = new ArrayList<>();
        Map map1 = new HashMap<>();
        map1.put("id","1679765241128423424");
        map1.put("itemType",30);
        map1.put("itemName","姓名");
        map1.put("orderIndex",1);
        map1.put("required",1);
        map1.put("autoNo",1);
        map1.put("type",30);
        map1.put("num",1);
        arrayList.add(map1);
        map.put("itemList",arrayList);
        ArrayList arrayList1 = new ArrayList<>();
        Map map2 = new HashMap<>();
        map2.put("id","1681609945134534656");
        map2.put("appid",appid);
        map2.put("itemType",40);
        map2.put("itemId","1671402819728576512");
        map2.put("itemName","jzh新增专栏2");
        map2.put("orderIndex",1);
        arrayList1.add(map2);
        map.put("sceneList",arrayList1);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/collection-config";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 30)
    public  void 添加会员卡weappManagerPaySetting() throws Exception{
        map.put("paySettingName","api创建金钻会员");
        ArrayList arrayList1 = new ArrayList();
        Map map1 = new HashMap();
        map1.put("timeUnit","year");
        map1.put("quantity","1");
        map1.put("price",1);
        map1.put("giftAmount",0);
        arrayList1.add(map1);
        map.put("productList",arrayList1);
        map.put("htmlIntro","<p>api创建金钻会员<span style=\\\"font-size: 1em;\\\">api创建金钻会员</span></p>");
        ArrayList arrayList2 = new ArrayList();
        Map map2 = new HashMap();
        map2.put("itemType",40);
        map2.put("itemId","1671402490182111232");
        map2.put("itemName","jzh新增专栏1111操作专栏设计起");
        arrayList2.add(map2);

        map.put("sceneList",arrayList2);
        map.put("styleId","1");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/pay-setting";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 31)
    public  void 会员卡设置LISTweappManagerPaySettingList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/pay-setting/list";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        hykid= (String)result.getJSONArray("datas").getJSONObject(1).get("id");
    }

    @Test(priority = 32)
    public  void 删除会员卡weappManagerPaySettingId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/pay-setting?id="+hykid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 33)
    public  void 付费折扣权益添加商品weappManagerPaySettingDiscountItemBatchAdd() throws Exception{
        map.put("itemType",40);
        map.put("itemId","1683773463812247552");
        JSONObject jsonString = new JSONObject(map);
        String str1 = "[" + jsonString + "]";
        String uu="https://open.openwhy.net/api/v1/weapp/manager/pay-setting/discount/item/batch-add";
        JSONObject result = httpJzhPostAppidArryList.JzhPost(str1,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 34)
    public  void 付费折扣权益LISTmanagerPaySettingDiscountItemList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/pay-setting/discount/item/list?page=1";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        ffzkspid = (String)result.getJSONArray("datas").getJSONObject(0).get("id");
    }

    @Test(priority = 35)
    public  void 付费折扣权益商品移除weappManagerPaySettingDiscountItem() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/pay-setting/discount/item?id="+ffzkspid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 36)
    public  void 查询列表listweappManagerQueryInfoSearch() throws Exception{
        mapNew.put("","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/query-info/search?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(mapNew,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 37)
    public  void 创建查询v1WeappManagerQueryInfo() throws Exception{
        mapNew.put("name","api创建查询new");
        mapNew.put("introHtml","");
        mapNew.put("titleImg","");
        mapNew.put("queryType","1");

        ArrayList queryConditionList = new ArrayList<>();
        Map queryConditionListMap = new HashMap<>();
        queryConditionListMap.put("sourceColId","1694541306262589440");
        queryConditionListMap.put("colName","美食名字");
        queryConditionListMap.put("isRequired",1);
        queryConditionListMap.put("searchType","2");
        queryConditionList.add(queryConditionListMap);
        mapNew.put("queryConditionList",queryConditionList);

        ArrayList resultColIdList = new ArrayList<>();
        resultColIdList.add("1694541306262589441");
        resultColIdList.add("1694541306262589442");
        resultColIdList.add("1694541306262589443");
        mapNew.put("resultColIdList",resultColIdList);

        mapNew.put("phoneColId","");

        ArrayList colList = new ArrayList<>();
        Map colListMap1 = new HashMap<>();
        colListMap1.put("id","1694541306258395136");
        colListMap1.put("sourceId","1694541302902951936");
        colListMap1.put("fieldName","field1");
        colListMap1.put("colName","序号");
        colListMap1.put("orderIndex",1);
        Map colListMap2 = new HashMap<>();
        colListMap2.put("id","1694541306262589440");
        colListMap2.put("sourceId","1694541302902951936");
        colListMap2.put("fieldName","field2");
        colListMap2.put("colName","美食名字");
        colListMap2.put("orderIndex",2);
        colListMap2.put("checked1",true);
        Map colListMap3 = new HashMap<>();
        colListMap3.put("id","1694541306262589441");
        colListMap3.put("sourceId","1694541302902951936");
        colListMap3.put("fieldName","field3");
        colListMap3.put("colName","美食价格");
        colListMap3.put("orderIndex",3);
        colListMap3.put("checked1",true);
        Map colListMap4 = new HashMap<>();
        colListMap4.put("id","1694541306262589442");
        colListMap4.put("sourceId","1694541302902951936");
        colListMap4.put("fieldName","field4");
        colListMap4.put("colName","备注");
        colListMap4.put("orderIndex",4);
        colListMap4.put("checked1",true);
        Map colListMap5 = new HashMap<>();
        colListMap5.put("id","1694541306262589443");
        colListMap5.put("sourceId","1694541302902951936");
        colListMap5.put("fieldName","field5");
        colListMap5.put("colName","猜你喜欢");
        colListMap5.put("orderIndex",5);
        colListMap5.put("checked1",true);
        colList.add(colListMap1);
        colList.add(colListMap2);
        colList.add(colListMap3);
        colList.add(colListMap4);
        colList.add(colListMap5);
        mapNew.put("colList",colList);
        mapNew.put("sourceId","1694541302902951936");
        System.out.println("mapNew::"+mapNew);
        System.out.println("37map::"+map);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/query-info";
        JSONObject result = httpJzhPostAppid.JzhPost(mapNew,uu,token,appid);
        System.out.println("restu:::"+result);
        Assert.assertEquals(result.get("code"),"000000","成功");
        cxid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 38)
    public  void 查询分享managerApply() throws Exception{

        String uu="https://open.openwhy.net/api/v1/weapp/manager/apply/"+appid+"/detail";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 39)
    public  void 删除查询weappManagerQueryInfoId() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/query-info?id="+cxid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


