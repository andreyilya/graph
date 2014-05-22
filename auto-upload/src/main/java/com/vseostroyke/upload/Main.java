package com.vseostroyke.upload;

import com.vseostroyke.upload.http.ContentExtractor;
import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.sql.RemoteRepository;
import java.io.IOException;
import java.util.Arrays;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class Main {
    public static final String LOGIN = "fftdfbzs29847";
    public static final String PASSWORD = "Q8ibY.y511((32";

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {

       // FtpSession ftpSession = new FtpSession("k29.hostenko.com", 21, LOGIN, PASSWORD);
       // FTPFile[] listFtpFile = ftpSession.list("/wp-content/uploads");
//        for (FTPFile ftpFile1 : listFtpFile) {
//            System.out.println("Name - " + ftpFile1.getName() +
//                    "Size - " + ftpFile1.getSize() +
//                    "Link - " + ftpFile1.getLink() +
//                    "Type - " + ftpFile1.getType());
//        }
//        ftpSession.uploadToFTP(new File("d://graf_monte_kristo.pdf"));
        ContentExtractor contentExtractor = new ContentExtractor();
        ContentItem contentItem = contentExtractor.extract("http://www.domovoy.by/good/id/4973/900/901");
        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.generateSqlFile(Arrays.asList(contentItem), "d://dump.sql");
    }


}
