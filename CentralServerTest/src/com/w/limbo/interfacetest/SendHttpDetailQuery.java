package com.w.limbo.interfacetest;


import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.util.ssl.SslContextFactory;

public class SendHttpDetailQuery {
	
	public static void main(String[] args) throws Exception{
		SslContextFactory cf = new SslContextFactory();
        cf.setKeyStorePath("keystore");
        cf.setKeyStorePassword("123456");
        cf.setKeyManagerPassword("123456");
		
        HttpClient httpClient = new HttpClient(cf);

		//Start HttpClient
		httpClient.start();
		
		sendQuery(httpClient);
	}
	
	public static void sendQuery(HttpClient client) throws Exception{
		
		String msisdn = GetMsisdnFromFile.getInstance("msisdn.txt").getMsisdnBySize(100);
		
		long starttime = System.currentTimeMillis();
		ContentResponse response = client.newRequest("https://localhost:8009/detailquery/")
				.param("token", "msisdn")
				.param("querytype", "msisdn")
				.param("index", msisdn)
				.send();
		
		System.out.println(response.getStatus());
		System.out.println(response.getContentAsString());
		long endtime = System.currentTimeMillis();
		long elapsedtime = endtime - starttime; 
		System.out.println("elapsed time: "+ elapsedtime + " ms");
	}
	
}
