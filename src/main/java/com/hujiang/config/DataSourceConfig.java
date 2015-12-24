package com.hujiang.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;
import com.hujiang.repository.DruidRepository;


@Configuration
@MapperScan("com.hujiang.mapper")
public class DataSourceConfig implements EnvironmentAware{

	private RelaxedPropertyResolver propertyResolver;  
	
	
	@Override
	public void setEnvironment(Environment environment) {
		 this.propertyResolver = new RelaxedPropertyResolver(environment, "spring.datasource.");  
	}
	
	@Bean(name="dataSource")
	public DruidDataSource DataSource() {
		DruidDataSource datasource=new DruidDataSource();
	datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
	datasource.setUrl(propertyResolver.getProperty("jdbcUrl"));
	datasource.setUsername(propertyResolver.getProperty("username"));
	datasource.setPassword(propertyResolver.getProperty("password"));
		
	return datasource;
	}
	
	

	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean()throws Exception{
		
		 SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
	        sqlSessionFactoryBean.setDataSource(DataSource());
	        
	        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
	        
	        sqlSessionFactoryBean.setMapperLocations(resolver
	                .getResources("classpath:/mapper/*.xml"));
	        return sqlSessionFactoryBean.getObject();
	}



	
	
}
