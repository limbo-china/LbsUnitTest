package com.w.limbo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;

public class JettyServer {
	
	public static void main(String[] args) throws Exception{
		Server server = new Server(8080);
		server.setHandler(new HelloHandler());

		server.start();
		server.join();
	}
}


 
class HelloHandler extends AbstractHandler
{
	@Override
    public void handle( String target,
                        Request baseRequest,
                        HttpServletRequest request,
                        HttpServletResponse response ) throws IOException
    {
		ServletInputStream servletInputStream = null;	
		servletInputStream = baseRequest.getInputStream();
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] b = new byte[1024];
        byte[] req = null;
        int i = 0;
        while ((i = servletInputStream.read(b, 0, 1024)) > 0) {
            out.write(b, 0, i);
        }
        req = out.toByteArray();
        
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
 
        baseRequest.setHandled(true);
        
        System.out.print(new String(req));          
    }
}
