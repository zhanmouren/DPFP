package com.korosoft.invoice.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
@MapperScan(basePackages = "com.korosoft.invoice.mapper.second", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondDataSourceConfig {
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "second.datasource")
    public DataSource getDateSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "secondSqlSessionFactory")
    public SqlSessionFactory test2SqlSessionFactory(@Qualifier("secondDataSource") DataSource datasource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(datasource);
        bean.setMapperLocations(
                new PathMatchingResourcePatternResolver().getResources("classpath*:com/korosoft/invoice/mapper/second/*.xml"));
        return bean.getObject();
    }
    @Bean("secondSqlSessionTemplate")
    public SqlSessionTemplate test2sqlsessiontemplate(
            @Qualifier("secondSqlSessionFactory") SqlSessionFactory sessionfactory) {
        return new SqlSessionTemplate(sessionfactory);
    }
}