package com.busanit501.helloworld;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectTest {
    //단위테스트 동작 확인
    @Test
    public void test(){
        int v1 = 100;
        int v2 = 100;
        Assertions.assertEquals(v1, v2);
    }

    //디비 연결 확인 테스트
    @Test
    public void test2() throws ClassNotFoundException, SQLException {
        Class.forName("org.mariadb.jdbc.Driver");

        Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/webdb","webuser","webuser");
    Assertions.assertNotNull(con);
    con.close();
    }
}
