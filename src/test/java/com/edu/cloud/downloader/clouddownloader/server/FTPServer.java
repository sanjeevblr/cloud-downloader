package com.edu.cloud.downloader.clouddownloader.server;

import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.UserManager;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.apache.ftpserver.usermanager.impl.BaseUser;

public class FTPServer {

    private static FtpServer ftpServer;

    public static void start() throws FtpException {
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        UserManager userManager = userManagerFactory.createUserManager();
        BaseUser user = new BaseUser();
        user.setName("username");
        user.setPassword("password");
        user.setHomeDirectory("/Users/sanjeev.singh1/IdeaProjects/edureka/tutorials/cloud-downloader/ftpHomeDir");
        userManager.save(user);

        ListenerFactory listenerFactory = new ListenerFactory();
        listenerFactory.setPort(2221);

        FtpServerFactory factory = new FtpServerFactory();
        factory.setUserManager(userManager);
        factory.addListener("default", listenerFactory.createListener());

        System.out.println("Server Started At ==============");
        System.out.println("Home Name         ============== - " + "localhost");
        System.out.println("Host Port         ============== - " + 2221);
        System.out.println("UserName          ============== - " + "username");
        System.out.println("Password          ============== - " + "password");

        ftpServer = factory.createServer();
        ftpServer.start();
    }

    public static void stop(){
        ftpServer.stop();
    }


    public static void main(String ... args) throws FtpException {
        FTPServer.start();
    }
}
