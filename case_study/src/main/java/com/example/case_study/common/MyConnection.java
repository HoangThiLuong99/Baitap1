package com.example.case_study.common;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
@Configuration
public class MyConnection {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:mysql://localhost:6033/sample_db");
        dataSourceBuilder.username("root");
        dataSourceBuilder.password("123456789");
        return dataSourceBuilder.build();
    }
}
