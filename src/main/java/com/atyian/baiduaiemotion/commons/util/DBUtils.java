package com.atyian.baiduaiemotion.commons.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-02-14-16:32
 */
public class DBUtils {
    /**
     * 使用druid数据库连接池进行操作
     */
    /**
     * 获取数据库连接池连接
     */
    private static DataSource dataSource;
    private static ThreadLocal<Connection> conns = new ThreadLocal<Connection>();

    static {
        try {
            Properties pros = new Properties();
            InputStream is = DBUtils.class.getClassLoader().getResourceAsStream("database.properties");
            pros.load(is);
            dataSource = DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取数据库连接池中的连接
     * @return 如果返回null，说明获取连接失败<br/>有值就是获取连接成功
     */
    public static Connection getConnection() {

        Connection conn = conns.get();
        if(conn == null){
            try {
                conn = dataSource.getConnection();//从数据库连接池中获取连接
//                conns.set(conn);//保存到ThreadLocal对象中，供后面的jdbc操作使用
//                conn.setAutoCommit(false);//设置为手动管理事务
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

    /**
     * 提交事务，并关闭释放连接
     */
    public static void commitAndClose(){
        Connection conn = conns.get();
        if(conn != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.commit();//提交事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();//关闭连接，资源释放
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

    /**
     * 回滚事务，并关闭释放连接
     */
    public static void rollbackAndClose(){
        Connection conn = conns.get();
        if(conn != null){//如果不等于null，说明之前使用过连接，操作过数据库
            try {
                conn.rollback();//回滚事务
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }finally {
                try {
                    conn.close();//关闭连接，资源释放
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        //一定要执行remove操作，否则就会出错。（因为Tomcat服务器底层使用了线程池技术）
        conns.remove();
    }

}
