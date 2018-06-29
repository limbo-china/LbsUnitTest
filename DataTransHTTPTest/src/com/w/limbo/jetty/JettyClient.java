package com.w.limbo.jetty;

import java.nio.ByteBuffer;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.ByteBufferContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.w.limbo.testdata.*;

public class JettyClient {

	public static void main(String[] args) throws Exception{
		HttpClient httpClient = new HttpClient();

		// Start HttpClient
		httpClient.start();
		
		while(true){
			sendData(httpClient);
			Thread.sleep(50);
		}
	}
		
	public static void sendData(HttpClient client) throws Exception{
		ContentResponse response = client.newRequest("http://localhost:8080/dataload/")
		        .method(HttpMethod.POST)
		        .content(new ByteBufferContentProvider("text/plain", ByteBuffer.wrap(RandomGenerateCDR.generateSerializedCDR())))
		        .send();
		
	}
}


