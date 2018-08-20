package com.w.limbo.roam.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) throws IOException {

		int port = 5555;
		ServerSocket server = new ServerSocket(port);

		Socket socket = server.accept();

		InputStream inputStream = socket.getInputStream();
		byte[] bytes = new byte[1024];
		int len;
		while ((len = inputStream.read(bytes)) != -1) {
			System.out.println("len: " + len);
			System.out.println(bytes[0]);
		}

		inputStream.close();
		socket.close();
		server.close();
	}
}
