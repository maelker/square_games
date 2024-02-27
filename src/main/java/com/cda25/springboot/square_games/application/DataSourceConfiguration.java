package com.cda25.springboot.square_games.application;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("application-mysql.properties")
    public DataSourceProperties dataSourceMySQLProperties() {
        return new DataSourceProperties();
    }
    @Bean
    @Primary
    @Profile("mysql")
    public DataSource dataSourceMySQL() {
        return dataSourceMySQLProperties()
                .initializeDataSourceBuilder()
                .build();
    }
    @Bean
    @ConfigurationProperties("application-h2.properties")
    public DataSourceProperties dataSourceH2Properties() {
        return new DataSourceProperties();
    }
    @Bean
    @Profile("h2")
    public DataSource dataSourceH2() {
        return dataSourceH2Properties()
                .initializeDataSourceBuilder()
                .build();
    }
}
