package com.example.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.example.jdbc.connection.ConnectionConst.*;

public class ConnectionTest {


    @Test
    void driverManger() throws SQLException {
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        System.out.println("connection=" + con1 + " class={}" + con1.getClass());
        System.out.println("connection=" + con2 + " class={}" + con2.getClass());
    }

    @Test
    void dataSourceDriverManger() throws SQLException {
//         DriverMangerDataSource - 항상 새로운 커넥션을 획득
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        useDataSource(dataSource);
    }

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        // 커넥션 풀링
        HikariDataSource dataSource = new HikariDataSource();

        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("myPool");

        useDataSource(dataSource);
        Thread.sleep(1000);
    }


    private void useDataSource(DataSource dataSource) throws SQLException {
        Connection con1 = dataSource.getConnection(); // pool 1개
        Connection con2 = dataSource.getConnection(); // 기다림

        System.out.println("connection=" + con1 + " class={}" + con1.getClass());
        System.out.println("connection=" + con2 + " class={}" + con2.getClass());
    }


}
