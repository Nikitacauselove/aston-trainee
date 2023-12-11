package com.aston.trainee.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final String password = "password";
    private static final String username = "postgres";
    private static final String driverName = "org.postgresql.Driver";
    private static final String jdbcUrl = "jdbc:postgresql://localhost:5432/aston";
    private static final HikariDataSource dataSource;

    static {
        HikariConfig config = new HikariConfig();
        config.setPassword(password);
        config.setUsername(username);
        config.setDriverClassName(driverName);
        config.setJdbcUrl(jdbcUrl);

        dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
