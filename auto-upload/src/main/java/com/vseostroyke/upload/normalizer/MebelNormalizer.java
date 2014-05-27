package com.vseostroyke.upload.normalizer;

import com.vseostroyke.upload.ftp.FtpSession;
import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.util.ResourceUtil;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public class MebelNormalizer extends NormalizerBase {

    public static final String D_TEMP = "d:/temp";

    @Override
    public ContentItem normalize(ContentItem contentItem) throws IOException {
        ContentItem normalizedContentItem = super.normalize(contentItem);

        saveFile("http://www.domovoy.by" + contentItem.getImg());

        normalizedContentItem.setImg("http://mebel.vseostroyke.by/wp-content/uploads" + contentItem.getImg());
        //TODO: replace домовой
        return normalizedContentItem;
    }

    private String getFormatName(String formatName) {
        String[] split = formatName.split("\\.");
        return split[split.length-1];
    }

    private void saveFile(String path) throws IOException {
        FtpSession ftpSession = new FtpSession(ResourceUtil.getMessage("ftp.url"), 21
                , ResourceUtil.getMessage("ftp.login")
                , ResourceUtil.getMessage("ftp.password"));

        URL imageURL = new URL(path);
        RenderedImage img = ImageIO.read(imageURL);
        File outputfile = new File(D_TEMP + imageURL.getFile());
        outputfile.mkdirs();
        ImageIO.write(img, getFormatName(imageURL.getFile()), outputfile);
        ftpSession.uploadToFTP(outputfile,imageURL.getFile());
    }
}
