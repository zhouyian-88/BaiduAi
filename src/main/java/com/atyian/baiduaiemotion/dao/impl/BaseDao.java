package com.atyian.baiduaiemotion.dao.impl;

import com.atyian.baiduaiemotion.commons.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-02-17-20:22
 */
public abstract class BaseDao {
    //使用DButils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * updata()方法用来执行：Insert\Updata\Selete语句
     * @return 如果返回-1，说明执行失败 ；返回其他表示影响的行数
     */
    public int update(String sql,Object ...args){
        Connection conn = DBUtils.getConnection();
        try {
           return queryRunner.update(conn,sql,args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 查询返回一个javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return 如果返回null，则说明查询失败，否则则返回一个结果
     */
    public <T>T queryForOne(Class<T> type,String sql,Object ...args){
        Connection conn = DBUtils.getConnection();
        try {
            return queryRunner.query(conn,sql,new BeanHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
    /**
     * 查询返回多个javaBean的sql语句
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T> 返回的类型的泛型
     * @return 如果返回null，则说明查询失败，否则则返回结果集
     */
    public <T> List<T> queryForList(Class<T> type,String sql,Object ...args){
        Connection conn = DBUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanListHandler<T>(type),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }

    /**
     * 执行返回一行一列的sql语句
     * @param sql 执行的sql语句
     * @param args sql对应的参数值
     * @return
     */
    public Object queryForSingleValue(String sql,Object ...args){
        Connection conn = DBUtils.getConnection();
        try {
           return queryRunner.query(conn,sql,new ScalarHandler(),args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException(throwables);
        }
    }
}
