package com.hujiang.controller;

import org.apache.solr.client.solrj.impl.CloudSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hujiang.model.Demo;
import com.hujiang.service.testService;


@RestController
public class HelloController {

	@Autowired
	private CloudSolrServer solrserver;
	@Autowired
	private testService teService;
	
	public String hello(){
		return"say hello";
	}
	@RequestMapping("/test")
	public void test(){
		//CloudSolrServer server=new CloudSolrServer("192.168.219.134:2181,192.168.219.140:2181");
	//	server.setDefaultCollection("demo");
		/*SolrQuery query=new SolrQuery();
		query.add("q","demo:父亲");
		 query.add("start","0");
         query.add("rows", "10");*/
		ModifiableSolrParams params =new ModifiableSolrParams();
		params.add("q","demo:父亲");
		params.add("ws","json");
		params.add("start","0");
		params.add("rows","10");
		QueryResponse response=null;
		
		try{
			response=solrserver.query(params);
			SolrDocumentList results = response.getResults();
			for (SolrDocument document:results) {
			System.out.println(	document.getFieldValue("demo"));
			System.out.println(document.getFieldValue("id"));
			}
		}catch(Exception e){
			e.getStackTrace();
		}
		System.out.println(response.toString());
	}
	@RequestMapping("/testMapper")
	public void testMapper(){
		Demo d=teService.findById("testdddd");
		System.out.println(d);
	}
	
	
}
