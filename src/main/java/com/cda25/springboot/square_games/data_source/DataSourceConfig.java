package com.cda25.springboot.square_games.data_source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("app.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("app.datasource.url"));
        dataSource.setUsername(env.getProperty("app.datasource.username"));
        dataSource.setPassword(env.getProperty("app.datasource.password"));
        return dataSource;
    }

//    @Bean(name = "mysqlProperties")
//    @ConfigurationProperties("application-mysql.properties")
//    @Profile("mysql")
//    public DataSourceProperties dataSourcePropertiesMySQL() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "mysqlDataSource")
//    @Profile("mysql")
//    public DataSource dataSourceMySQL(@Qualifier("mysqlProperties") DataSourceProperties properties) {
//        return properties
//                .initializeDataSourceBuilder()
//                .build();
//    }
//    @Bean(name = "h2Properties")
//    @ConfigurationProperties("application-h2.properties")
//    @Profile("h2")
//    public DataSourceProperties dataSourcePropertiesH2() {
//        return new DataSourceProperties();
//    }
//
//    @Bean(name = "h2DataSource")
//    @Profile("h2")
//    public DataSource dataSourceH2(@Qualifier("h2Properties") DataSourceProperties properties) {
//        return properties
//                .initializeDataSourceBuilder()
//                .build();
//    }
}
