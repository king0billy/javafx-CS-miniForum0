/**
 * 提供静态方法块获取properties文件数据，getConnection链接数据库，release释放
 */
package com.ljl.www.dao;


import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/**
 * @className DriverUtils
 * @description 数据库连接工具类
 * @author  22427(king0liam)
 * @date 2021/5/12 14:28
 * @version 1.0
 * @since version-0.0
 */
public class DriverUtils {
    private static String driver=null;
    private static String url=null;
    private static String username=null;
    private static String password=null;
    /**
     * @description 注册MySQL驱动的静态代码块//不用缓冲区直接用输入流读取配置文件
     * @exception Exception
     * @param
     * @return
     * @since version-1.0
     * @author 22427(king0liam)
     * @date 2021/5/12 14:30
     */
    static{
        try{

            InputStream input =DriverUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(input);
            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");

            Class.forName(driver);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    public static void release(Connection connection, Statement statement, ResultSet resultSet){
        /**
         * @description 释放连接的方法
         * @exception
         * @param [connection, statement, resultSet]
         * @return [java.sql.Connection, java.sql.Statement, java.sql.ResultSet]
         * @since version-1.0
         * @author 22427(king0liam)
         * @date 2021/5/12 14:30
         */
        if(resultSet!=null){
            try {
                resultSet.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(statement!=null){
            try {
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        if(connection!=null){
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
