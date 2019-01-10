package com.cyjz.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;

import tk.mybatis.spring.annotation.MapperScan;


@Configuration
@MapperScan(basePackages = "com.cyjz.dao.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class DataSourceOneConfig {
	

	  @Bean
	  @ConfigurationProperties(prefix = "spring.datasource")
	  @Primary //设置主数据源
	  public DataSource dataSource(){
	      return new DruidDataSource();
	  }
	
	  @Bean(name = "sqlSessionFactory")
	  @Primary
	  public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource)throws Exception{
	      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
	      bean.setDataSource(dataSource);
	      bean.setConfigLocation(new DefaultResourceLoader().getResource("classpath:mybatis/mybatis-config.xml"));
	      bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/*.xml"));
	      return bean.getObject();
	  }
	  
	  @Bean(name = "sqlSessionTemplate")
	  @Primary
	  public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory)throws Exception{
	      return new SqlSessionTemplate(sqlSessionFactory);
	  }
	  
}
