package com.example.server;

import java.io.File;
import java.util.List;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;
import org.apache.ftpserver.usermanager.impl.ConcurrentLoginPermission;
import org.apache.ftpserver.usermanager.impl.WritePermission;
import org.springframework.stereotype.Component;

@Component
public class Server {
    public FtpServerFactory serverFactory = new FtpServerFactory();
    ListenerFactory factory = new ListenerFactory();

    public FtpServer server = null;
    public void startServer(String address, int port) throws FtpException {

        factory.setPort(port);
        factory.setServerAddress(address);  
    
        serverFactory.addListener("default", factory.createListener());
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        userManagerFactory.setFile(new File(System.getProperty("user.dir") + "/src/main/resources/users.properties"));
        
        UserManager userManager = userManagerFactory.createUserManager();

        serverFactory.setUserManager(userManager);
        server = serverFactory.createServer();
        server.start();

    }

    public void createNewUser(String username, String password, String homePath) throws FtpException {
        var user = new BaseUser();
        user.setName(username);
        user.setPassword(password);
        user.setHomeDirectory(homePath);
        user.setAuthorities(List.of(new WritePermission(), new ConcurrentLoginPermission(10, 10)));
        user.setEnabled(true);
        serverFactory.getUserManager().save(user);
    }
}
