package com.vseostroyke.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        FtpSession ftpSession = new FtpSession(ftpClient);
        FTPFile[] listFtpFile = ftpSession.list("/wp-content/uploads");
        for (FTPFile ftpFile1 : listFtpFile) {
            System.out.println("Name - " + ftpFile1.getName() +
                    "Size - " + ftpFile1.getSize() +
                    "Link - " + ftpFile1.getLink() +
                    "Type - " + ftpFile1.getType());
        }
    }



}
