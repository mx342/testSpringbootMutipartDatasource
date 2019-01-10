package com.cyjz.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement 
@Configuration
public class TransactionManagementConfig {
	
	//注意: @Qualifier 按名称在IOC容器中找指定名称的bean
	@Bean //或者 @Bean("myTransactionManager")
	public PlatformTransactionManager platformTransactionManager(
			@Qualifier("dataSource") DataSource myDataSource) {
		return new DataSourceTransactionManager(myDataSource);
	}
	
}
