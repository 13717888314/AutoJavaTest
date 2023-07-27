package com.test;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class JDBCTestSql {
        public String JDBCTestSql(String sql)  throws ClassNotFoundException, SQLException, IOException {
//使用驱动;
        Class.forName("com.mysql.cj.jdbc.Driver");
// 创建连接，涉及数据库IP，端口，数据库名，字符集，账号及密码
        String url = "jdbc:mysql://81.69.195.135:7709/openy?characterEncoding=UTF-8";
        Connection conn = DriverManager.getConnection(url,"zwtx","teb#Fn8y-3p");
        //System.out.println(conn);
        // 获取语句执行平台对象 Statement
        Statement smt = conn.createStatement();
//        // 创建表 executeUpdate方法
//        String  sql1  = "create table  if not exists test14(id int  primary key auto_increment ,name varchar(20),age int);";
//        smt.executeUpdate(sql1);
//        //  插入数据
//        String sql_i = "insert into  test14 values(1,'刘备',45),(2,'关羽',40),(3,'张飞',37),(4,'赵云',30),(5,'诸葛亮',27);";
//        smt.executeUpdate(sql_i);
//
//        // 更新数据
//        String sql_u= "update  test14 set age = 36 where name='张飞';";
//        smt.executeUpdate(sql_u);

        // 查询结果

        System.out.println("sql_q:::"+sql);
        ResultSet res = smt.executeQuery(sql);
        String id = null;
        while(res.next()){
                id = res.getString("id");
//                System.out.println("id:::"+id);

//            int    id = res.getInt(1);
//            String  name= res.getString("name");
//            int  age = res.getInt("age");
//            System.out.println("id:"+ id + "    name:" + name +"    age:"+age);
        }
        // 关闭流 (先开后关)
        res.close();
        smt.close();
        conn.close();
        return id;
    }
}
