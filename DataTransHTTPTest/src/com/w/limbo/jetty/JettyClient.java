package com.w.limbo.jetty;

import java.nio.ByteBuffer;

import org.eclipse.jetty.client.HttpClient;
import org.eclipse.jetty.client.api.ContentResponse;
import org.eclipse.jetty.client.util.ByteBufferContentProvider;
import org.eclipse.jetty.http.HttpMethod;

import com.w.limbo.config.ConfigUtil;
import com.w.limbo.testdata.RandomGenerateCDR;

public class JettyClient {

	public static void main(String[] args) throws Exception {
		HttpClient httpClient = new HttpClient();

		// Start HttpClient
		httpClient.start();

		int interval = ConfigUtil.getInt("sendInterval");
		while (true) {
			sendData(httpClient);
			Thread.sleep(interval);
		}
	}

	public static void sendData(HttpClient client) throws Exception {

		ContentResponse response = client
				.newRequest(ConfigUtil.getString("loadUrl"))
				.method(HttpMethod.POST)
				.content(
						new ByteBufferContentProvider("text/plain",
								ByteBuffer.wrap(RandomGenerateCDR
										.generateSerializedCDR()))).send();

	}
}
