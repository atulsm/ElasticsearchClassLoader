package com.atul.esloader;

import java.net.InetAddress;
import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.json.JSONObject;

public class ElasticSearchV2 implements ElasticSearchInterface {

	public static void main(String[] args) {
		new ElasticSearchV2().search(null);
	}

	public Object search(JSONObject query) {
		try{
			Settings settings = Settings.settingsBuilder().put("cluster.name", "atul-es").build();
			Client client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(Configuration.IP_V2), Configuration.PORT));

			System.out.println("Connected to elasticsearch v2");
					
			//Get all Data
			SearchResponse response = client.prepareSearch(Configuration.INDEX).setQuery(QueryBuilders.matchAllQuery()).execute().actionGet();        
			
			//Query by term
			//SearchResponse response = client.prepareSearch(Configuration.INDEX).setQuery(QueryBuilders.termQuery("pos", "1")).execute().actionGet();  
			
			SearchHit[] results = response.getHits().getHits();
	        System.out.println("Got " + results.length + " hits") ;
	        for (SearchHit hit : results) {
	            Map<String,Object> result = hit.getSource();   
	            System.out.println(result);
	        }
		
			// on shutdown
			client.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

}
