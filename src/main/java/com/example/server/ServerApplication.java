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
		KeyboardListenerThread keyBoardListenerThread = app.getBean(KeyboardListenerThread.class);
		try {
			// nhập địa chỉ và port kết nối

			String address = null;
			int port = 0;


			System.out.println("Enter the address to initialize the server: ");
			address = System.console().readLine();
			System.out.println("Enter port number: ");
			port = Integer.parseInt(System.console().readLine());

			keyBoardListenerThread.start();

			ftpServer.startServer(address, port);
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}	
}
