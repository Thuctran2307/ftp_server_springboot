package com.example.server;

import org.apache.ftpserver.ftplet.FtpException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		ApplicationContext app =  SpringApplication.run(ServerApplication.class, args);
		Server ftpServer = app.getBean(Server.class);
		try {
			ftpServer.startServer();
		} catch (FtpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
