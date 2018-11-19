package com.edu.cloud.downloader.clouddownloader.ftp;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class FTPDownloader{


    @Value( "${cloud-downloader.ftp.url}")
    private String url;
    @Value( "${cloud-downloader.ftp.username}")
    private  String username;
    @Value( "${cloud-downloader.ftp.password}")
    private  String password;
    @Value( "${cloud-downloader.ftp.port}")
    private  int port;
    @Value( "${cloud-downloader.ftp.fileType}")
    private int fileType;

    public FTPDownloader(){

    }

    public FTPDownloader(String url, String username, String password, int port, int fileType){
        this.url = url;
        this.username = username;
        this.password = password;
        this.port = port;
        this.fileType = fileType;
    }


    public boolean download(String remoteLocationFile, String localFile) {
        FTPClient ftpClient = new FTPClient();
        //FTPSClient ftpsClient = new FTPSClient();
        try {
            ftpClient.connect(url, port);
            ftpClient.login(username, password);

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(fileType);
            OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFile));
            boolean success = ftpClient.retrieveFile(remoteLocationFile, outputStream);
            outputStream.close();

            if (success) {
                System.out.println("Ftp file successfully download.");
            }

        } catch (IOException ex) {
            System.out.println("Error occurs in downloading files from ftp Server : " + ex.getMessage());
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;

            }
        }
        return true;
    }

    public static void main(String ... args){
        String remoteFile = "/Users/sanjeev.singh1/IdeaProjects/edureka/tutorials/cloud-downloader/ftpHomeDir/tobdownloaded.txt";
        String localFile = "/Users/sanjeev.singh1/IdeaProjects/edureka/tutorials/cloud-downloader/downloadDir/downloaded.txt";

        FTPDownloader ftpDownloader = new FTPDownloader("localhost","username","password", 2221, FTP.BINARY_FILE_TYPE);
        ftpDownloader.download(remoteFile,localFile);
    }
}
