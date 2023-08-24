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
public class TestKwwNRService {
    HttpJzhGet httpjzhget = new HttpJzhGet();
    HttpFromPost httpKww = new HttpFromPost();
    HttpJzhGetAppid httpJzhGetAppid = new HttpJzhGetAppid();
    HttpJzhPut httpJzhPut = new HttpJzhPut();
    HttpJzhPostAppid httpJzhPostAppid = new HttpJzhPostAppid();

    HttpClientGetCode  httpClientGetCode = new HttpClientGetCode();
    HttpJzhDeleteAppid httpJzhDeleteAppid = new HttpJzhDeleteAppid();
    HttpJzhPutAppidArryList httpJzhPutAppidArryList = new HttpJzhPutAppidArryList();
    HttpJzhPostAppidArryList httpJzhPostAppidArryList = new HttpJzhPostAppidArryList();
    Date now = new Date();
    long time = now.getTime();
    Map map = new HashMap();
    HttpJzhPost httpJzhPost = new HttpJzhPost();
    public static String token;
    public static String appid;
    public static String tpuuid;
    public static Object sysTime;
    public static String tpid;
    public static String spuuid;
    public static String spid;
    public static String ypuuid;
    public static String ypid;
    public static String wduuid;

    public static String wdid;
    public static String dhid;

    public static String h5id;
    public static String copyh5id;

    public static String cyid;
    public static String copycyid;
    public static String wzid;
    public static String copywzid;

    public static String yyid;

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
    public  void 资料中心v1WeappMangerFileTotalSize() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manger/file/totalSize";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 4)
    public  void 获取uuidWeappUploadToken() throws Exception{
        String uu="https://open.openwhy.net/api/v1/wx/third-party/weapp/upload-token?configId=1";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        tpuuid=(String)result.getJSONObject("datas").get("id");

    }

    @Test(priority = 5)
    public  void 上传图片v1WeappManagerFile() throws Exception{
        map.put("url","https://img.openwhy.net/"+tpuuid);
        map.put("fileType","jpg");
        map.put("fileName","api创建图片");
        map.put("fileSize",36038);
        map.put("bucket","openwhy-test");
        map.put("type",3);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 6)
    public  void 获取图片idv1WeappManagerFileList() throws Exception{
        map.put("keywords","");
        map.put("type",3);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/list?page=1&size=20";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        tpid = (String)result.getJSONArray("datas").getJSONObject(0).get("id");
    }

    @Test(priority = 7)
    public  void 图片详情v1WeappManagerFileTPid() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/"+tpid;
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 8)
    public  void 图片删除v1WeappManagerFileDelete() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/"+tpid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }
    @Test(priority = 9)
    public  void 获取视频uuidV1WeappManagerFileUploadToken() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/upload-token";
        String aa = token;
        JSONObject result = httpJzhGetAppid.JzhGet(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        spuuid = (String)result.getJSONObject("datas").get("id");
    }

    @Test(priority = 10)
    public  void 上传视频v1WeappManagerFile() throws Exception{
        map.put("url","https://video.openwhy.net/"+spuuid);
        map.put("fileName","apitest视频上传");
        map.put("fileType","mp4");
        map.put( "fileSize", 666045);
        map.put("bucket","openwhy-video-test");
        Map mapExtObj = new HashMap<>();
        mapExtObj.put("duration",3.333);
        mapExtObj.put("videoHeight",1280);
        mapExtObj.put("videoWidth",720);
        map.put("extObj",mapExtObj);
        map.put("type",1);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        spid = (String)result.getJSONObject("datas").get("id");
    }

    @Test(priority = 11)
    public  void 视频Listidv1WeappManagerFileLis() throws Exception{
        map.put("keywords","");
        map.put("type",1);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/list?page=1&size=20";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 12)
    public  void 删除视频v1WeappManagerFile() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/"+spid;
        String aa = token;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 13)
    public  void 音频Listv1WeappManagerFileList() throws Exception{
        map.put("keywords","");
        map.put("type",2);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/list?page=1&size=20";
        String aa = token;
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,aa,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 14)
    public  void 音频获取uuidv1WeappManagerFileUploadToken() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/upload-token";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        ypuuid = (String)result.getJSONObject("datas").get("id");
    }

    @Test(priority = 15)
    public  void 提交音频V1WeappManagerFile() throws Exception{
        map.put("url","https://video.openwhy.net/"+ypuuid);
        map.put("fileName","api上传音频");
        map.put("fileType","mp3");
        map.put("fileSize",6588281);
        map.put("bucket","openwhy-video-test");
        Map extmap = new HashMap<>();
        extmap.put("duration",164.649796);
        extmap.put("videoHeight",150);
        extmap.put("videoWidth",300);
        extmap.put("videoImg","");
        map.put("extObj",extmap);
        map.put("type",2);
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        ypid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 16)
    public  void 删除音频文件V1WeappManagerFile() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/"+ypid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }

    @Test(priority = 17)
    public  void 获取文档uuidv1WeappManagerFileUploadToken() throws Exception{
        String uu="https://open.openwhy.net/api/v1/weapp/manager/file/upload-token";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        wduuid = (String)result.getJSONObject("datas").get("id");
    }

    @Test(priority = 18)
    public void 上传文档v1WeappManagerFile() throws Exception{
        map.put("url","https://img.openwhy.net/"+wduuid);
        map.put("fileName","api上传文档1");
        map.put("fileType","xlsx");
        map.put("fileSize",18593);
        map.put("bucket","openwhy-test");
        map.put("type",4);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/file";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        wdid= (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 19)
    public void 文档PageListv1WeappManagerFileList() throws Exception{
        map.put("keywords","");
        map.put("type",4);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/file/list?page=1&size=20";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 20)
    public void 文档删除v1WeappManagerFile() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/file/"+wdid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 21)
    public void 快捷导航PageListv1WeappManagerQuickNavSearch() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/quick-nav/search";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 22)
    public void 创建快捷导航v1WeappManagerQuickNav() throws Exception{
        map.put("navName","api创建快捷导航");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/quick-nav";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        dhid = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 23)
    public void 导航重命名v1WeappManagerQuickNavName() throws Exception{
        map.put("id",dhid);
        map.put("navName","api创建快捷导航77777");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/quick-nav/name";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 24)
    public void 编辑快捷导航v1WeappManagerQuickNavSetting() throws Exception{
        map.put("id", dhid);
        map.put("navStyleType", 1);
        ArrayList navArraylist = new ArrayList<>();
        Map mapn1 = new HashMap<>();
        mapn1.put("groupName", "apibianjitest");
        mapn1.put("orderIndex", 1);
        mapn1.put("isShow", 1);
        ArrayList itemArraylist = new ArrayList<>();
        Map mapit = new HashMap<>();
        mapit.put("navName", "apibianjitest");
        mapit.put("imageUrl", "");
        mapit.put("linkType", "");
        mapit.put("itemId", "");
        mapit.put("itemName", "");
        mapit.put("isShow", 1);
        itemArraylist.add(mapit);
        mapn1.put("itemList", itemArraylist);
        navArraylist.add(mapn1);
        map.put("navGroupList", navArraylist);
        ArrayList cc = new ArrayList<>();
        map.put("classIdList", cc);

        String uu ="https://open.openwhy.net/api/v1/weapp/manager/quick-nav/setting";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 25)
    public void 删除快捷导航v1WeappManagerQuickNav() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/quick-nav?id="+dhid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 26)
    public void 授权内容已授权tabLISTminiProgramCoManagerAuthApplyAuditStatus() throws Exception{
        String uu ="https://openwhy.net/api/v1/mini-program/co/manager/auth-apply?auditStatus=1&page=1";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 27)
    public void 授权搜索LISTweappManageAuthApplyActivitySearch() throws Exception{
        map.put("keywords","7");
        String uu ="https://openwhy.net/api/v1/weapp/manage/auth-apply/activity/search?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 28)
    public void 内容待授权miniProgramCoManagerAuthApplyAuditStatus() throws Exception{
        String uu ="https://openwhy.net/api/v1/mini-program/co/manager/auth-apply?auditStatus=0&page=1";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 29)
    public void 微页面获取模板weappManagerMicroPagesSysTemplates() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/sys-templates?page=1";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 30)
    public void 微页面创建H5v1WeappManagerMicroPages() throws Exception{
        map.put("pageMode",1);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        h5id = (String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 31)
    public void H5设计创建v1WeappManagerMicroPages() throws Exception{
        map.put("id",h5id);
        map.put("title","未命名");
        map.put("pages","[{\"name\":\"\",\"uuid\":\"919b545b-7780-401d-9497-eae0b667df89\",\"config\":{},\"elements\":[{\"uuid\":\"494de6af-27b5-49fd-b3c8-2f99a4d4b117\",\"elName\":\"qk-text\",\"animations\":[],\"commonStyle\":{\"position\":\"absolute\",\"width\":375,\"height\":40,\"top\":58,\"left\":3,\"rotate\":0,\"paddingTop\":0,\"paddingLeft\":0,\"paddingRight\":0,\"paddingBottom\":0,\"marginTop\":0,\"marginLeft\":0,\"marginRight\":0,\"marginBottom\":0,\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"solid\",\"borderRadius\":0,\"boxShadow\":\"\",\"fontSize\":16,\"fontWeight\":500,\"lineHeight\":1.4,\"letterSpacing\":0,\"textAlign\":\"center\",\"textStrokeWidth\":0,\"textStrokeColor\":\"\",\"textIndent\":0,\"fontFamily\":\"\",\"color\":\"#000000\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"backgroundSize\":\"cover\",\"display\":\"block\",\"opacity\":1,\"zIndex\":1},\"events\":[],\"propsValue\":{\"text\":\"这是一段文字\"},\"valueType\":\"String\"},{\"uuid\":\"c1bfcc18-4c2b-4819-a97e-04dfdb3305c2\",\"elName\":\"qk-button\",\"animations\":[],\"commonStyle\":{\"position\":\"absolute\",\"width\":140,\"height\":40,\"top\":200,\"left\":0,\"rotate\":0,\"paddingTop\":10,\"paddingLeft\":0,\"paddingRight\":0,\"paddingBottom\":10,\"marginTop\":0,\"marginLeft\":0,\"marginRight\":0,\"marginBottom\":0,\"borderWidth\":1,\"borderColor\":\"#999999\",\"borderStyle\":\"solid\",\"borderRadius\":4,\"boxShadow\":\"\",\"fontSize\":16,\"fontWeight\":500,\"lineHeight\":1.4,\"letterSpacing\":0,\"textAlign\":\"center\",\"textStrokeWidth\":0,\"textStrokeColor\":\"\",\"textIndent\":0,\"fontFamily\":\"\",\"color\":\"#000000\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"backgroundSize\":\"cover\",\"display\":\"block\",\"opacity\":1,\"zIndex\":2},\"events\":[],\"propsValue\":{\"text\":\"按 钮\"},\"valueType\":\"String\"}]," +
                "\"commonStyle\":{\"backgroundSize\":\"cover\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"height\":644}}]");
        map.put("width",375);
        map.put("height",644);
        map.put("pageMode",1);
        map.put("flipType",0);
        map.put("slideNumber",0);
        map.put("isPublish",1);
        map.put("isTemplate",0);
        map.put("creatorId","1670632529773551616");
        map.put("createTime","1688967876000");
        map.put("appid",appid);
        map.put("deleted",0);
        map.put("isValid",1);
        map.put("pageType",1);
        map.put("description","推荐你一个丰富多彩的内容，快点击查看！");
        map.put("coverImage","https://img.openwhy.net/b59b9e6c-05ca-445a-8568-9cba8a557617");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+h5id;
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 32)
    public void 微页面H5复制weappManagerMicroPages() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+h5id+"/copy";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        copyh5id =(String) result.getJSONObject("datas").get("id");
    }

    @Test(priority = 33)
    public void 删除微页面H5v1WeappManagerMicroPages() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+h5id;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 33)
    public void 发布微页面H5weappManagerMicroPagesPublish() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+copyh5id+"/publish";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 34)
    public void 删除复制H5weappManagerMicroPages() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+copyh5id;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 35)
    public void 创建微页面长页v1WeappManagerMicroPages() throws Exception{
        map.put("pageMode",0);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        cyid = (String)result.getJSONObject("datas").get("id");
    }


    @Test(priority = 36)
    public void 微页面创建发布长页v1WeappManagerMicroPages() throws Exception{
        map.put("id",cyid);
        map.put("title","未命名");
        map.put("pages","[{\"name\":\"\",\"uuid\":\"d6b12e26-91de-4981-b56c-2d0803d550b8\",\"config\":{},\"elements\":[{\"uuid\":\"d5a3e1ca-671d-421d-aab3-7baaf0cd8c53\",\"elName\":\"qk-text\",\"animations\":[],\"commonStyle\":{\"position\":\"absolute\",\"width\":375,\"height\":40,\"top\":200,\"left\":0,\"rotate\":0,\"paddingTop\":0,\"paddingLeft\":0,\"paddingRight\":0,\"paddingBottom\":0,\"marginTop\":0,\"marginLeft\":0,\"marginRight\":0,\"marginBottom\":0,\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"solid\",\"borderRadius\":0,\"boxShadow\":\"\",\"fontSize\":16,\"fontWeight\":500,\"lineHeight\":1.4,\"letterSpacing\":0,\"textAlign\":\"center\",\"textStrokeWidth\":0,\"textStrokeColor\":\"\",\"textIndent\":0,\"fontFamily\":\"\",\"color\":\"#000000\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"backgroundSize\":\"cover\",\"display\":\"block\",\"opacity\":1,\"zIndex\":1},\"events\":[],\"propsValue\":{\"text\":\"这是一段文字\"},\"valueType\":\"String\"},{\"uuid\":\"3a2b429c-8b02-46aa-860a-63ff3a95da3b\",\"elName\":\"qk-button\",\"animations\":[],\"commonStyle\":{\"position\":\"absolute\",\"width\":140,\"height\":40,\"top\":200,\"left\":0,\"rotate\":0,\"paddingTop\":10,\"paddingLeft\":0,\"paddingRight\":0,\"paddingBottom\":10,\"marginTop\":0,\"marginLeft\":0,\"marginRight\":0,\"marginBottom\":0,\"borderWidth\":1,\"borderColor\":\"#999999\",\"borderStyle\":\"solid\",\"borderRadius\":4,\"boxShadow\":\"\",\"fontSize\":16,\"fontWeight\":500,\"lineHeight\":1.4,\"letterSpacing\":0,\"textAlign\":\"center\",\"textStrokeWidth\":0,\"textStrokeColor\":\"\",\"textIndent\":0,\"fontFamily\":\"\",\"color\":\"#000000\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"backgroundSize\":\"cover\",\"display\":\"block\",\"opacity\":1,\"zIndex\":2},\"events\":[],\"propsValue\":{\"text\":\"按 钮\"},\"valueType\":\"String\"}],\"commonStyle\":" +
                "{\"backgroundSize\":\"cover\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"height\":644}}]");
        map.put("width",375);
        map.put("height",644);
        map.put("pageMode",1);
        map.put("flipType",0);
        map.put("slideNumber",0);
        map.put("isPublish",1);
        map.put("isTemplate",0);
        map.put("creatorId","1670632529773551616");
        map.put("createTime","1688967876000");
        map.put("appid",appid);
        map.put("deleted",0);
        map.put("isValid",1);
        map.put("pageType",1);
        map.put("description","推荐你一个丰富多彩的内容，快点击查看！");
        map.put("coverImage","https://img.openwhy.net/28c6ead5-bb02-46e5-9a91-8c123aa9fea2");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+cyid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");

    }


    @Test(priority = 37)
    public void 微页面复制长页weappManagerMicroPagesCopy() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+cyid+"/copy";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        copycyid = (String)result.getJSONObject("datas").get("id");
    }


    @Test(priority = 38)
    public void 删除微页面长页v1WeappManagerMicroPagesCYID() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+cyid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 39)
    public void 微页面长页发布v1WeappManagerMicroPagesPublish() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+copycyid+"/publish";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 40)
    public void 删除微页面复制长页weappManagerMicroPagesCopycyid() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+copycyid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 41)
    public void 微页面创建电脑网站weappManagerMicroPages() throws Exception{
        map.put("pageMode",2);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        wzid = (String)result.getJSONObject("datas").get("id");
    }


    @Test(priority = 42)
    public void 发布微页面网站v1WeappManagerMicroPages() throws Exception{
        map.put("id",wzid);
        map.put("title","未命名");
        map.put("pages","[{\"name\":\"\",\"uuid\":\"f9036e27-2f4f-49cb-ae1b-3fe4f3010c25\",\"config\":{},\"elements\":[{\"uuid\":\"f9a13f7e-451d-43d1-a533-20c8ae949c98\",\"elName\":\"qk-text\",\"animations\":[],\"commonStyle\":{\"position\":\"absolute\",\"width\":1200,\"height\":40,\"top\":200,\"left\":0,\"rotate\":0,\"paddingTop\":0,\"paddingLeft\":0,\"paddingRight\":0,\"paddingBottom\":0,\"marginTop\":0,\"marginLeft\":0,\"marginRight\":0,\"marginBottom\":0,\"borderWidth\":0,\"borderColor\":\"\",\"borderStyle\":\"solid\",\"borderRadius\":0,\"boxShadow\":\"\",\"fontSize\":16,\"fontWeight\":500,\"lineHeight\":1.4,\"letterSpacing\":0,\"textAlign\":\"center\",\"textStrokeWidth\":0,\"textStrokeColor\":\"\",\"textIndent\":0,\"fontFamily\":\"\",\"color\":\"#000000\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"backgroundSize\":\"cover\",\"display\":\"block\",\"opacity\":1,\"zIndex\":1},\"events\":[],\"propsValue\":{\"text\":\"这是一段文字\"},\"valueType\":\"String\"}],\"commonStyle\":" +
                "{\"backgroundSize\":\"cover\",\"backgroundColor\":\"\",\"backgroundImage\":\"\",\"height\":644}}]");
        map.put("width",1200);
        map.put("height",644);
        map.put("pageMode",2);
        map.put("flipType",0);
        map.put("slideNumber",0);
        map.put("isPublish",1);
        map.put("isTemplate",0);
        map.put("creatorId","1670632529773551616");
        map.put("createTime","1688967876000");
        map.put("appid",appid);
        map.put("deleted",0);
        map.put("isValid",1);
        map.put("pageType",1);
        map.put("description","推荐你一个丰富多彩的内容，快点击查看！");
        map.put("coverImage","https://img.openwhy.net/f09a7f97-8bfd-4c13-93c4-29eaff8ee610");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+wzid;
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 43)
    public void 微页面网站复制weappManagerMicroPagesCopy() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+wzid+"/copy";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        copywzid = (String)result.getJSONObject("datas").get("id");
    }

    @Test(priority = 44)
    public void 删除网站页面v1WeappManagerMicroPages() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+wzid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 45)
    public void 微页面网站发布weappManagerMicroPagesPublish() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+copywzid+"/publish";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 46)
    public void 删除复制网站页weappManagerMicroPages() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/micro-pages/"+copywzid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 47)
    public void 预约列表managerReservationListPage() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation/list?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 48)
    public void 获取预约类型v1WeappManagerReservationTemplateClass() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation/template-class";
        JSONObject result = httpJzhGetAppid.JzhGet(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 49)
    public void 预约创建weappManagerReservation() throws Exception{
        map.put("baseInfo","");
        Map mapbaseInfo = new HashMap<>();
        mapbaseInfo.put("name","api测试创建");
        mapbaseInfo.put("titleImg","//r.openwhy.cn/activity-top-img-recreation-7.png");
        mapbaseInfo.put("content","<p>api测试创建</p>");
        mapbaseInfo.put("notice","");
        mapbaseInfo.put("address","");
        mapbaseInfo.put("contactInfo","");
        mapbaseInfo.put("btnName","立即预约");
        mapbaseInfo.put("contactType",1);
        map.put("baseInfo",mapbaseInfo);

        Map maptimeInfo = new HashMap<>();
        maptimeInfo.put("timeOpen",0);
        maptimeInfo.put("timeModel",1);
        maptimeInfo.put("futureDays",7);
        maptimeInfo.put("timeSelectMode",1);
        maptimeInfo.put("minNum",1);
        maptimeInfo.put("maxNum",-1);
        maptimeInfo.put("isRepeatSelect",1);
        map.put("timeInfo",maptimeInfo);

        Map mapcollectionInfo = new HashMap<>();
        mapcollectionInfo.put("collectionOpen","1");


        ArrayList itemList = new ArrayList<>();
        Map itemmap1 = new HashMap<>();
        itemmap1.put("itemName","姓名");
        itemmap1.put("type",30);
        itemmap1.put("required",1);
        itemmap1.put("autoNo",1);

        ArrayList optionlist = new ArrayList<>();
        Map ma1 = new HashMap();
        ma1.put("text","选项1");
        ma1.put("isSelected",0);
        Map ma2 = new HashMap();
        ma2.put("text","选项2");
        ma2.put("isSelected",0);
        optionlist.add(ma1);
        optionlist.add(ma2);
        itemmap1.put("optionList",optionlist);
        itemmap1.put("score",0);

        ArrayList blankslist = new ArrayList<>();
        Map mapblanks = new HashMap<>();
        mapblanks.put("keys","");
        blankslist.add(mapblanks);
        itemmap1.put("blanks",blankslist);
        itemmap1.put("num",1);
        itemmap1.put("itemType",30);

        Map itemmap2 = new HashMap<>();
        itemmap2.put("itemName","手机号码");
        itemmap2.put("type",30);
        itemmap2.put("required",1);
        itemmap2.put("autoNo",1);
        ArrayList optionlist2 = new ArrayList<>();
        Map ma3 = new HashMap();
        ma1.put("text","选项1");
        ma1.put("isSelected",0);
        Map ma4 = new HashMap();
        ma2.put("text","选项2");
        ma2.put("isSelected",0);
        optionlist.add(ma3);
        optionlist.add(ma4);
        itemmap2.put("optionList",optionlist2);
        itemmap2.put("score",0);

        ArrayList blankslist2 = new ArrayList<>();
        Map mapblanks2 = new HashMap<>();
        mapblanks2.put("keys","");
        blankslist2.add(mapblanks2);
        itemmap2.put("blanks",blankslist2);
        itemmap2.put("num",1);
        itemmap2.put("itemType",30);

        itemList.add(itemmap1);
        itemList.add(itemmap2);
        mapcollectionInfo.put("itemList",itemList);
        map.put("collectionInfo",mapcollectionInfo);

        Map mapitemInfo = new HashMap<>();
        mapitemInfo.put("itemOpen","0");
        mapitemInfo.put("itemModel","1");
        mapitemInfo.put("itemModelTxt","通用类型");
        mapitemInfo.put("subjectName","");
        mapitemInfo.put("isRequired","0");
        map.put("itemInfo",mapitemInfo);


        Map mapmoreInfo = new HashMap<>();
        mapmoreInfo.put("payType","0");
        mapmoreInfo.put("reservationLimit","1");
        mapmoreInfo.put("isAudit","0");
        mapmoreInfo.put("isCancel","0");
        mapmoreInfo.put("periodRange",1);
        mapmoreInfo.put("periodRangeLimit",3);
        map.put("moreInfo",mapmoreInfo);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 50)
    public void 获取预约idListPage() throws Exception{
        map.put("","");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation/list?page=1";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
        yyid = (String)result.getJSONArray("datas").getJSONObject(0).get("id");
    }

    @Test(priority = 51)
    public void 预约详情ManagerReservationReservationUserSearch() throws Exception{
        map.put("id",yyid);
        map.put("auditStatus","-1");
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation/reservation-user/search";
        JSONObject result = httpJzhPostAppid.JzhPost(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 52)
    public void 预约详情导出ReservationUserExport() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/reservation/"+yyid+"/reservation-user/export";
        Integer result = httpClientGetCode.GetClientGetCode(uu,token,appid);
        Assert.assertEquals(result.toString(),"200","成功");
    }

    @Test(priority = 53)
    public void 预约暂停ReservationSuspend() throws Exception{
        map.put("id",yyid);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation/"+yyid+"/suspend";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }


    @Test(priority = 54)
    public void 预约发布ReservationPublish() throws Exception{
        map.put("id",yyid);
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation/"+yyid+"/publish";
        JSONObject result = httpJzhPut.JzhPut(map,uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @Test(priority = 55)
    public void 删除预约ReservationId() throws Exception{
        String uu ="https://open.openwhy.net/api/v1/weapp/manager/reservation?id="+yyid;
        JSONObject result = httpJzhDeleteAppid.JzhDelete(uu,token,appid);
        Assert.assertEquals(result.get("code"),"000000","成功");
    }

    @AfterTest
    public void teardown(){
        System.out.println("用例执行完后执行");
    }
}


