package com.vseostroyke.upload.normalizer;

import com.vseostroyke.upload.ftp.FtpSession;
import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.util.ResourceUtil;
import java.io.IOException;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public class MebelNormalizer extends NormalizerBase {

    @Override
    public ContentItem normalize(ContentItem contentItem) throws IOException {
        ContentItem normalizedContentItem = super.normalize(contentItem);
        normalizedContentItem.setImg("http://mebel.vseostroyke.by/wp-content/upload" + contentItem.getImg());
        //TODO: replace домовой
        saveFiles(contentItem);
        return normalizedContentItem;
    }

    private void saveFiles(ContentItem contentItem) throws IOException {
        FtpSession ftpSession = new FtpSession(ResourceUtil.getMessage("ftp.url"), 21
                , ResourceUtil.getMessage("ftp.login")
                , ResourceUtil.getMessage("ftp.password"));

//        FTPFile[] listFtpFile = ftpSession.list("/wp-content/uploads");
//        for (FTPFile ftpFile1 : listFtpFile) {
//            System.out.println("Name - " + ftpFile1.getName() +
//                    "Size - " + ftpFile1.getSize() +
//                    "Link - " + ftpFile1.getLink() +
//                    "Type - " + ftpFile1.getType());
//        }
//        ftpSession.uploadToFTP(new File("d://graf_monte_kristo.pdf"));
    }
}
