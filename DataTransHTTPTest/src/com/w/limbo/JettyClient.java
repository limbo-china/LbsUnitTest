package com.w.limbo;

import java.nio.file.Paths;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.PathContentProvider;
import org.eclipse.jetty.http.HttpMethod;

public class JettyClient {

	public static void main(String[] args) throws Exception{
		HttpClient httpClient = new HttpClient();

		// Configure HttpClient, for example:
		httpClient.setFollowRedirects(false);

		// Start HttpClient
		httpClient.start();
		
		while(true){
			sendData(httpClient);
			Thread.sleep(1);
		}
	}
	
	
	public static void sendData(HttpClient client) throws Exception{
		ContentResponse response = client.newRequest("localhost",8080)
		        .method(HttpMethod.POST)
		        .content(new PathContentProvider(Paths.get("request.txt")), "text/plain")
		        .send();
	}
}

