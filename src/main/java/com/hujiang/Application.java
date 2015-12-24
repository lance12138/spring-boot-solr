package com.hujiang;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;

@SpringBootApplication(exclude = {SolrAutoConfiguration.class})
public class Application {

	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
