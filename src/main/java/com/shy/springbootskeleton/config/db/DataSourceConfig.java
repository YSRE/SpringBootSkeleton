package com.shy.springbootskeleton.config.db;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class DataSourceConfig {

    @Value("{dataSource.type}")
    private Class<? extends DataSource> dataSourceType;


    @Bean(name = "writeDataSource", destroyMethod = "close", initMethod = "init")
    @Primary
    @ConfigurationProperties(prefix = "datasource.write")
    public DataSource writeDataSource(){
        log.info("-------readDataSourceOne 初始化-------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /*
    每个数据库配置一个readDataSource方法
     */
    @Bean(name = "readDataSource1")
    @ConfigurationProperties(prefix = "dataSource.read1")
    public DataSource readDataSourceOne(){
        log.info("------dataSource1 初始化------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "readDataSource2")
    @ConfigurationProperties(prefix = "datasource.read2")
    public DataSource readDataSourceTwo() {
        log.info("-------------------- readDataSourceTwo init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean("readDataSource")
    public List<DataSource> readDataSource(){
        List<DataSource> dataSources = new ArrayList<>();
        dataSources.add(readDataSourceOne());
        dataSources.add(readDataSourceTwo());
        return dataSources;
    }

}
