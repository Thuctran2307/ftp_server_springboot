package com.example.server;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class KeyboardListenerThread extends Thread {
    private boolean isRunning = true;

    @Autowired
    private Server serverFTP = null;
    //contructor 
    public KeyboardListenerThread(Server server) {
        super("KeyboardListenerThread");
        this.serverFTP = server;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("n")) {
                System.out.println("Nhập username: ");
                String username = scanner.nextLine();
                System.out.println("Nhập password: ");
                String password = scanner.nextLine();
                System.out.println("Nhập đường dẫn thư mục: ");
                String homePath = scanner.nextLine();
                try {
                    serverFTP.createNewUser(username, password, homePath);
                    serverFTP.server = serverFTP.serverFactory.createServer();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        scanner.close();
    }

    public void stopListening() {
        isRunning = false;
    }
}

