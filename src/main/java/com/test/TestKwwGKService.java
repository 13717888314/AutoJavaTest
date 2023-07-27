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
public class TestKwwGKService {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpFromPost httpKww = new HttpFromPost();
    HttpJzhGetAppid httpJzhGetAppid = new HttpJzhGetAppid();
    HttpJzhPut httpJzhPut = new HttpJzhPut();
    HttpJzhPostAppid httpJzhPostAppid = new HttpJzhPostAppid();
    HttpJzhDeleteAppid httpJzhDeleteAppid = new HttpJzhDeleteAppid();
    Date now = new Date();
    long time = now.getTime();
    Map map = new HashMap();
    HttpJzhPost httpJzhPost = new HttpJzhPost();
    public static String token;
    public static String appid;
    public static String qzid;
    public static Object sysTime;
    public static String zdyid;
    public static String wjid;
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
    public void LoginKwauthLogin() throws IOException {
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
    public  void 写文章入口isRealnameAuth() throws Exception{
        String uu=bb+"/api/v1/persons/isRealnameAuth?_="+sysTime;
        String aa = token;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 3)
    public  void  私密文章发布apiV2Articles() throws Exception{
        map.put("id","1670980850887237632");
        map.put("firstClassId","441");
        map.put("secondClassId","");
        map.put("title","AutoApi私密文章发布articles"+time);
        map.put("content","<p>私密文章发布articles</p>");
        map.put("isSecreted","1");
        map.put("articleImg","");
        map.put("intro","AutoApi私密文章发布articles"+time);
        map.put("applyPromotion",1);
        String uu=bb+"/api/v2/articles";
        String aa = token;
        JSONObject result = httpJzhPost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 4)
    public  void  公开文章发布articles() throws Exception{
        map.put("id","1670989443225165824");
        map.put("firstClassId","441");
        map.put("secondClassId","");
        map.put("title","AutoApi公开文章发布articles"+time);
        map.put("content","<p>公开文章发布articles</p>");
        map.put("isSecreted","2");
        map.put("articleImg","");
        map.put("intro","AutoApi公开文章发布articles"+time);
        map.put("applyPromotion",1);
        String uu=bb+"/api/v2/articles";
        String aa = token;
        JSONObject result = httpJzhPost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 5)
    public  void  线上中心项目v1WeappManagerApplylist() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/apply/list";
        String aa = token;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
        appid = (String)result.getJSONObject("datas").getJSONArray("applyList").getJSONObject(0).get("id");
        System.out.println("appid:::"+appid);
    }

    @Test(priority = 6)
    public  void  开问线上中心登录项目v1PersonsInformationbasic() throws Exception{
        String uu=bb+"/api/v1/persons/information/basic";
        String aa = token;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }



    @Test(priority = 7)
    public  void  线上中心文章listArticlePublicSearch() throws Exception{
        map.put("page",1);
        String uu=bb+"/api/v1/weapp/belong/article/public/search?page=1";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 8)
    public  void  在线中心创建文章v1WeappManagerArticles() throws Exception{
        map.put("id","1671450667855482880");
        map.put("title","XSZXAutoTest文章"+time);
        map.put("content","<p>XSZXAutoTest文章XSZXAutoTest文章XSZXAutoTest文章");
        map.put("isSecreted","2");
        map.put("articleImg","//r.openwhy.cn/activity-top-img-synthesize-9.png");
        map.put("intro","XSZXAutoTest文章XSZXAutoTest文章XSZXAutoTest文章"+time);
//        map.put("classList","[]");
        map.put("applyOpen",1);
        map.put("applyPromotion",1);
        String uu=bb+"/api/v1/weapp/manager/article";
        String aa = token;
        JSONObject result = httpJzhPost.JzhPost(map,uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 9)
    public  void 在线中心文章编辑页v1ArticlesEdit() throws Exception{
        String uu=bb+"/api/v1/articles/edit/1671450667855482880";
        String aa = token;
        JSONObject result = httpjzhget.JzhGet(uu,aa);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 10)
    public  void  线上中心活动v1WeappManagerActivitylist() throws Exception{
        map.put("page",1);
        map.put("searchType",1);
        String uu=bb+"/api/v1/weapp/manager/activity/list?page=1";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 11)
    public  void  线上中心活动v1CommonsJudgesSensitivewords() throws Exception{
        map.put("content","<p>apiAuto创建活动apiAuto创建活动apiAuto创建活动apiAuto创建活动apiAuto创建活动</p>");
        String uu=bb+"/api/v1/commons/judges/sensitive-words";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 12)
    public  void  在线中心云协作v1WeappManagerProjectlsit() throws Exception{
        map.put("page",1);
        map.put("searchType",1);
        String uu=bb+"/api/v1/weapp/manager/project/list?page=1";
        String aa = token;

        String apd = appid;
        System.out.println("12apd:"+apd);
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 13)
    public  void  在线中心圈子v1WeappManagerCirclelsit() throws Exception{
        map.put("page",1);
        map.put("searchType",1);
        String uu="https://openwhy.net/api/v1/weapp/manager/circle/list?page=1";
        String aa = token;
        String apd = appid;
        System.out.println("13apd:"+apd);
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        String oo = (String) result.get("code");
        System.out.println("code:::"+oo);
        System.out.println("result:::"+result);
       // qzid = result.getJSONArray("datas").getJSONObject(0).getString("id");
      //  System.out.println("qzid::::11111:"+qzid);
    }

    @Test(priority = 14)
    public  void  在线中心创建圈子apiV1circle() throws Exception{
        map.put("circleName","apiTest创建圈子"+time);
        map.put("htmlIntro","<p>apiTest创建圈子<span style=\"font-size: 15px;\">apiTest创建圈子</span><span style=\"font-size: 15px;\">apiTest创建圈子</span><span style=\"font-size: 15px;\">apiTest创建圈子</span></p>");
        map.put("headImg","//r.openwhy.cn/activity-top-img-synthesize-30.png");
        map.put("isSecreted","2");
        map.put("isAudit","0");
        map.put("timelineSecreted","2");
        map.put("isCost","0");
        map.put("amount",0);
        map.put("privateMessageType",1);
        String uu=bb+"/api/v1/circle";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        qzid = (String) result.getJSONObject("datas").get("id");
        System.out.println("qzid:::"+qzid);
        System.out.println("result14::"+result);
    }

    @Test(priority = 15)
    public  void  圈子移出coManagerunbindAuthContent() throws Exception{
        String qqzid = qzid;
        System.out.println("15qzid::::"+qqzid);
        map.put("itemId",qqzid);
        map.put("itemType",10);
        String uu= bb+"/api/v1/mini-program/co/manager/unbind-auth-content";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 16)
    public  void  表单v1WeappBlongSurveylsit() throws Exception{
        map.put("page",1);
        map.put("keywords","");
        map.put("searchType",1);
        String uu="https://open.openwhy.net/api/v1/weapp/blong/survey/search?page=1";
        String aa = token;
        String apd = appid;
       // System.out.println("12apd:"+apd);
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 17)
    public  void 行业分类tpyeV1SurveyTemplateClass() throws Exception{
        String uu="https://open.openwhy.net/api/v1/survey/template-class/list?type=0";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        System.out.println("result16::"+result);
    }

    @Test(priority = 18)
    public  void  表单模板v1SurveyTemplateList() throws Exception{
        map.put("type",0);
        String uu="https://open.openwhy.net/api/v1/survey/template/list?page=1";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 19)
    public  void  创建空白自定义v1SurveyCreateBlank() throws Exception{
        String apd = appid;
        map.put("appid",appid);
        map.put("type",10);
        String uu="https://open.openwhy.net/api/v1/survey/create-blank";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        zdyid= (String) result.getJSONObject("datas").get("id");
    }


    @Test(priority = 20)
    public  void  自动定义控件v1SurveyUpdate() throws Exception{
        String zzdyid = zdyid;
        System.out.println("zzdyid20::::"+zzdyid);
        map.put("id",zzdyid);
        map.put("titleHtml","<p>ApiAuto创建jzh</p>");
        map.put("description","<p>Auto创建欢迎使用，这是一个自定义创建jzh</p>");
        String uu= "https://open.openwhy.net/api/v1/survey/update";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 21)
    public  void  自定义表单添加控件v1SurveyQuestion() throws Exception{
        Map mapa = new HashMap();
        mapa.put("text","选项1");
        mapa.put("isSelected",0);
        mapa.put("blankOpen",0);
        mapa.put("blankIsRequired",0);

        Map mapb = new HashMap();
        mapb.put("text","选项2");
        mapb.put("isSelected",0);
        mapb.put("blankOpen",0);
        mapb.put("blankIsRequired",0);

        Map mapc = new HashMap();
        mapc.put("keys","");

        List listoption = new ArrayList<>();
        listoption.add(mapa);
        listoption.add(mapb);

        List blankslist = new ArrayList<>();
        blankslist.add(mapc);

        String zzdyid = zdyid;
        map.put("surveyDirId",zzdyid);
        map.put("title","请选择一个选项");
        map.put("type",10);
        map.put("required",1);
        map.put("autoNo",1);
        map.put("optionList",listoption);
        map.put("score",0);
        map.put("blanks",blankslist);
        String uu="https://open.openwhy.net/api/v1/survey/question";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 22)
    public  void  自定义表单发布v1SurveyPublish() throws Exception{
        String zzdyid = zdyid;
       // System.out.println("zzdyid20::::"+zzdyid);
        map.put("id",zzdyid);
        String uu= "https://open.openwhy.net/api/v1/survey/publish";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 23)
    public  void 表单删除apiV1SurveyDelete() throws Exception{
        String zzdyid = zdyid;
        String uu="https://open.openwhy.net/api/v1/survey/"+zzdyid+"/delete";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 24)
    public  void  线上中心表单已授权v1WeappAuthedSurveytabList() throws Exception{
        map.put("page",1);
        map.put("keywords","");
        String uu="https://open.openwhy.net/api/v1/weapp/authed/survey/search?page=1";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 25)
    public  void  创建商品v1WeappManagerMallgoods() throws Exception{
        Map map25 = new HashMap();
        map25.put("url","https://img.openwhy.net/5b977a00-3587-42ba-9a52-4ff781308c88");
        map25.put("name","api创建商品规格");
        map25.put("price",100);
        map25.put("origPrice",200);
        map25.put("num","-1");
        List productList25 = new ArrayList();
        productList25.add(map25);

        map.put("name","API创建商品22");
        map.put("brief","API创建商品API创建商品API创建商品1");
        map.put("detail","<p>API创建商品<span style=\\\"font-size: 1em;\\\">API创建商品</span><span style=\\\"font-size: 1em;\\\">API创建商品</span><span style=\\\"font-size: 1em;\\\">API创建商品</span></p>");
        map.put("picUrl","//r.openwhy.cn/project_img_11.png");
        map.put("buyButtonText","立即购买");
        map.put("logisticsOpen",1);
        map.put("collectionOpen",0);
        map.put("isOnSale",1);
        map.put("productList",productList25);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/mall/goods";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        goodsid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 26)
    public  void  商品下架v1WeappManagerMallisOnSale() throws Exception{
        String ggoodsid = goodsid;
        map.put("id",ggoodsid);
        map.put("isOnSale",0);
        String uu= "https://open.openwhy.net/api/v1/weapp/manager/mall/is-on-sale";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 27)
    public  void 删除商品v1WeappManagerMalldelete() throws Exception{
        String ggoodsid = goodsid;
        String uu="https://open.openwhy.net/api/v1/weapp/manager/mall/goods/"+ggoodsid;
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 28)
    public  void  商品操作日志mallGoodsLogspageList() throws Exception{
        map.put("page",1);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/mall/goods/logs/list?page=1";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 29)
    public  void  在线中心文集v1WeappManagerPageList() throws Exception{
        map.put("keywords","");
        String uu="https://open.openwhy.net/api/v1/weapp/manager/column-info/list?page=1";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 30)
    public  void  创建文集v1WeappManagerColumnInfo() throws Exception{
        map.put("name","api创建文件bbbb");
        map.put("brief","api创建文件1api创建文件1api创建文件1api创建文件2");
        map.put("detail","<p>api创建文件1<span style=\\\"font-size: 1em;\\\">api创建文件2</span></p>");
        map.put("picUrl","//r.openwhy.cn/activity-top-img-synthesize-29.png");
        map.put("price",700);
        map.put("isOnSale", "1");


        String uu="https://open.openwhy.net/api/v1/weapp/manager/column-info";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        wjid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 31)
    public  void 文集详情v1WeappManagerColumnInfodetail() throws Exception{
        String wwjid = wjid;
        String uu="https://open.openwhy.net/api/v1/weapp/manager/column-info/"+wwjid+"/detail";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 32)
    public  void  文集下架v1WeappManagerisOnSale() throws Exception{
        String wwjid = wjid;
        map.put("id",wwjid);
        map.put("isOnSale",0);
        String uu= "https://open.openwhy.net/api/v1/weapp/manager/column-info/is-on-sale";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 33)
    public  void  文集编辑PUTcolumnInfo() throws Exception{
        String wwjid = wjid;
        map.put("name","api创建文件99998");
        map.put("brief","api创建文件1api创建文件1api创建文件1api创建文件2");
        map.put("detail","<p>sdfsdfsdfsdfd</p>");
        map.put("picUrl","//r.openwhy.cn/activity-top-img-synthesize-29.png");
        map.put("price",300);
        map.put("isOnSale",1);
        map.put("id",wwjid);
        String uu= "https://open.openwhy.net/api/v1/weapp/manager/column-info";
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 34)
    public  void 删除文集v1WeappManagerdelete() throws Exception{
        String wwjid = wjid;
        String uu="https://open.openwhy.net/api/v1/weapp/manager/column-info/"+wwjid;
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 35)
    public  void  相册list() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/album-info/list";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 36)
    public  void  创建相册albumInfo() throws Exception{
        map.put("name","api创建相册22");
        map.put("style",1);
        map.put("showImgName",1);

        String uu="https://open.openwhy.net/api/v1/weapp/manager/album-info";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        xcid = (String) result.getJSONObject("datas").get("id");
    }


    @Test(priority = 37)
    public  void 删除相册delete() throws Exception{
        String xxcid = xcid;
        String uu="https://open.openwhy.net/api/v1/weapp/manager/album-info?id="+xxcid;
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 38)
    public  void  专栏v1WxThirdPartyWeappFirstClasspageList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/first-class/list?searchType=1";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 39)
    public  void  专栏模板templateList() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/class/template-standard/list";
        String apd = appid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 40)
    public  void  专栏空白创建v1WxThirdPartyWeappclass() throws Exception{
        String apd = appid;
        map.put("name","api创建专栏"+sysTime);
        map.put("parentId","0");
        map.put("appid",apd);
        map.put("classTemplateId","3");
        map.put("imageUrl","//r.openwhy.cn/activity-top-img-synthesize-7.png");
        map.put("intro","api创建专栏 简介  推荐你一个丰富多彩的内容，快点击查看！");
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
        zlid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 41)
    public  void 删除专栏v1WxThirdPartyWeappdelete() throws Exception{
        String zzlid = zlid;
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/class?id="+zzlid;
        String aa = token;
        String apd = appid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,apd);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


