package com.example.server;

import java.io.File;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.springframework.stereotype.Component;

@Component
public class Server {
    FtpServerFactory serverFactory = new FtpServerFactory();
    ListenerFactory factory = new ListenerFactory();
    FtpServer server = null;
    void startServer() throws FtpException {

        factory.setPort(2221);
        factory.setServerAddress("localhost");

        serverFactory.addListener("default", factory.createListener());


        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File(System.getProperty("user.dir") + "/src/main/resources/users.properties"));
        UserManager userManager = userManagerFactory.createUserManager();
        createUser(userManager);
        serverFactory.setUserManager(userManager);


        server = serverFactory.createServer();
        server.start();

    }

    static void createUser(UserManager userManager) throws FtpException {
        var user = new BaseUser();
        user.setName("user");
        user.setPassword("12");
        user.setHomeDirectory("E:\\");
        user.setEnabled(true);
        userManager.save(user);
    }
}
