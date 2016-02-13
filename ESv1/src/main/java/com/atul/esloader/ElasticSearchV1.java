package com.atul.esloader;

import java.util.Map;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.json.JSONObject;

public class ElasticSearchV1 implements ElasticSearchInterface {
	
	public static void main(String[] args) {
		new ElasticSearchV1().search(null);
	}

	public Object search(JSONObject query) {
		
		TransportClient transportClient = new TransportClient();
		transportClient = transportClient.addTransportAddress(new InetSocketTransportAddress(Configuration.IP_V1, Configuration.PORT));			
		Client client = (Client) transportClient;
		System.out.println("Connected to elasticsearch v1");
				
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
		transportClient.close();
		
		return null;
	}

}
