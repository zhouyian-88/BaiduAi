package com.atyian.baiduaiemotion.test;

import com.atyian.baiduaiemotion.commons.util.DBUtils;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

/**
 * @athor:zhouhaohui
 * @email:2873642764@qq.com
 * @desc:
 * @datetime:2022-09-19-19:54
 */
public class ConnectTest {

    @Test
    public void testConnect(){
        Connection connection = DBUtils.getConnection();
        System.out.println(connection);
    }
}
