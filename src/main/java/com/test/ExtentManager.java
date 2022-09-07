//package com.test;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentKlovReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//import java.io.File;
//
///**
// * @author nan
// * @title: ExtentManager
// * @projectName ISTP_new_dev
// * @description: TODO
// * @date 2021/11/2510:51
// */
//public class ExtentManager {
//    //生成的路径以及文件名
//    private static final String OUTPUT_FOLDER = "test-output/";
//    private static final String OUTPUT_F2="C:\\Users\\zihan.jia\\.jenkins\\workspace\\MasterAutoTest-C-Api\\";
//    private static final String FILE_NAME = "C-api-master-AutoTest.html";
//    private static ExtentReports extent;
//
//    public static ExtentReports getInstance() {
//        if (extent == null)
//            createInstance();
//        return extent;
//    }
//
//    public static void createInstance() {
//        //文件夹不存在的话进行创建
//        File reportDir = new File(OUTPUT_FOLDER);
//        if (!reportDir.exists() && !reportDir.isDirectory()) {
//            reportDir.mkdir();
//        }
//        extent = new ExtentReports();
//        extent.attachReporter(createSparkReporter(OUTPUT_FOLDER + FILE_NAME));
//        extent.attachReporter(createSparkReporter(OUTPUT_F2 + FILE_NAME));
//        //extent.setSystemInfo("os", "Linux");
//        extent.setReportUsesManualConfiguration(true);
//    }
//
//    private static ExtentSparkReporter createSparkReporter(String filePath) {
//        ExtentSparkReporter spark = new ExtentSparkReporter(filePath);
//        spark.config(
//                ExtentSparkReporterConfig.builder()
//                        .theme(Theme.DARK)
//                        .reportName("C-api-master-AutoTest自动化测试报告")
//                        .documentTitle("C-api-master-AutoTest自动化测试报告")
//                        .build());
//        return spark;
//    }


//    private static ExtentKlovReporter createKlovReporter() {
//        //  创建一个KlovReporter对象
//        ExtentKlovReporter klov = new ExtentKlovReporter("zuozewei-test");
//        klov
//                .initKlovServerConnection("10.16.55.95:8081")
//                .initMongoDbConnection("10.16.55.95", 27017);
//        return klov;
//    }
//}