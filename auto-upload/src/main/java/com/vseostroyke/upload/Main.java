package com.vseostroyke.upload;

import java.io.IOException;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class Main {
    public static final String LOGIN = "fftdfbzs29847";
    public static final String PASSWORD = "Q8ibY.y511((32";

    public static void main(String[] args) throws IOException {

        FTPClient ftpClient = connectToFTP("k29.hostenko.com", 21, LOGIN, PASSWORD);
        FTPFile[] listFtpFile = ftpClient.listFiles("/wp-content/uploads");
        for (FTPFile ftpFile1 : listFtpFile) {
            System.out.println("Name - " + ftpFile1.getName() +
                    "Size - " + ftpFile1.getSize() +
                    "Link - " + ftpFile1.getLink() +
                    "Type - " + ftpFile1.getType());
        }
    }

    public static FTPClient connectToFTP(String address, int port, String username, String password)
            throws IOException {
        FTPClient ftpClient = new FTPClient();
        ftpClient.connect(address, port);
        ftpClient.login(username, password);

        return ftpClient;
    }
}
