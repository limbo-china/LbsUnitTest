package com.w.limbo.jetty;

import java.nio.ByteBuffer;
import java.nio.file.Paths;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.ByteBufferContentProvider;
import org.eclipse.jetty.client.util.PathContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.w.limbo.testdata.*;

public class JettyClient {

	public static void main(String[] args) throws Exception{
		HttpClient httpClient = new HttpClient();

		// Configure HttpClient, for example:
		httpClient.setFollowRedirects(false);

		// Start HttpClient
		httpClient.start();
		
		while(true){
			sendData(httpClient);
			Thread.sleep(2000);
		}
	}
		
	public static void sendData(HttpClient client) throws Exception{
		ContentResponse response = client.newRequest("http://localhost:8080/dataload/")
		        .method(HttpMethod.POST)
		        .content(new ByteBufferContentProvider("text/plain", ByteBuffer.wrap(RandomGenerateCDR.generateOneGroup().getBytes())))
		        .send();
	}
}


