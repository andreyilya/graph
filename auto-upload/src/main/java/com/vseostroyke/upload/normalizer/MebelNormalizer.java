package com.vseostroyke.upload.normalizer;

import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.http.TemplateBuilder;
import freemarker.template.TemplateException;
import java.io.IOException;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public class MebelNormalizer extends NormalizerBase {
    public static final String LOGIN = "fftdfbzs29847";
    public static final String PASSWORD = "Q8ibY.y511((32";

    @Override
    public ContentItem normalize(ContentItem contentItem)  {
        ContentItem normalizedContentItem = super.normalize(contentItem);
        normalizedContentItem.setImg("http://www.domovoy.by" + contentItem.getImg());
        //TODO: replace домовой
        saveFiles(contentItem);
        return normalizedContentItem;
    }

    private void saveFiles(ContentItem contentItem) {
        // FtpSession ftpSession = new FtpSession("k29.hostenko.com", 21, LOGIN, PASSWORD);
        // FTPFile[] listFtpFile = ftpSession.list("/wp-content/uploads");
//        for (FTPFile ftpFile1 : listFtpFile) {
//            System.out.println("Name - " + ftpFile1.getName() +
//                    "Size - " + ftpFile1.getSize() +
//                    "Link - " + ftpFile1.getLink() +
//                    "Type - " + ftpFile1.getType());
//        }
//        ftpSession.uploadToFTP(new File("d://graf_monte_kristo.pdf"))e.e.;
    }
}
