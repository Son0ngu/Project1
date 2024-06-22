package com.auction.Project1.Main;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;


@PropertySource("classpath:application.properties")
@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://NGUYENNGUYEN\\sqlexpress:1433;databaseName=Project1;integratedSecurity=true;encrypt=true;trustServerCertificate=false;trustStore=C:\\Program Files\\Java\\jdk-14.0.2\\lib\\security\\cacert;trustStorePassword=changeit");
        dataSource.setUsername("sa");
        dataSource.setPassword("Nguyen2004Fg");
        

        //(●'◡'●)
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}