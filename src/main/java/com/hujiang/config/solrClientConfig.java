package com.hujiang.config;

import java.io.IOException;

import javax.annotation.PreDestroy;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.hujiang.model.SolrConfig;


@Configuration
@EnableConfigurationProperties(SolrConfig.class)
public class solrClientConfig {

	@Autowired
	private SolrConfig solrConfig;
	
	
	
	private CloudSolrServer solrServer;
	
	@PreDestroy
	public void close() {
		if (this.solrServer != null) {
			try {
				this.solrServer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@Bean 
	public CloudSolrServer SolrServer(){
		if (StringUtils.hasText(this.solrConfig.getZkHost())) {
			//solrClient = new CloudSolrClient(this.solrConfig.getZkHost());
			solrServer = new CloudSolrServer(this.solrConfig.getZkHost());
			solrServer.setDefaultCollection(this.solrConfig.getDefaultCollection());
		}
		return this.solrServer;
	}
	
}
