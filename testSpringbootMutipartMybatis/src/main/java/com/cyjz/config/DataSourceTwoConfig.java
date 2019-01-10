package com.cyjz.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan(basePackages = "com.cyjz.dao.mapper2", sqlSessionTemplateRef = "sqlSessionTemplateTwo")
public class DataSourceTwoConfig {

    @Bean(name = "dataSourceTwo")
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource DataSourceOne(){
        return new DruidDataSource();
    }

    @Bean(name = "sqlSessionFactoryTwo")
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceTwo") DataSource dataSource)throws Exception{
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper2/*.xml"));
        return bean.getObject();
    }
    
    @Bean(name = "sqlSessionTemplateTwo")
    public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sqlSessionFactory)throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
