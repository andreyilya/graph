package com.vseostroyke.upload.normalizer;

import com.vseostroyke.upload.ftp.FtpSession;
import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.util.ImageTransformer;
import com.vseostroyke.upload.util.ResourceUtil;
import com.vseostroyke.upload.util.Transliterator;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.StringUtils;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public class MebelNormalizer extends NormalizerBase {

    public static final String D_TEMP = "d:/temp";
    public static final String DOMOVOI = "Домовой";
    public static final String BIG = "/big/";
    public static final String ROOT_FOLDER = "http://mebel.vseostroyke.by/wp-content/uploads/";
    public static final String DELIMITER = "/";

    @Override
    public ContentItem normalize(ContentItem contentItem) throws IOException {
        ContentItem normalizedContentItem = super.normalize(contentItem);
        contentItem.setBigImg("http://www.domovoy.by" + contentItem.getBigImg());
        replaceDomovoi(contentItem);
        saveFile(contentItem);

        return normalizedContentItem;
    }

    private void replaceDomovoi(ContentItem contentItem) {
        contentItem.setHeader(contentItem.getHeader().replaceAll(DOMOVOI, StringUtils.EMPTY));
        contentItem.setTitle(contentItem.getTitle().replaceAll(DOMOVOI, StringUtils.EMPTY));
        contentItem.setKeywords(contentItem.getKeywords().replaceAll(DOMOVOI, StringUtils.EMPTY));
        contentItem.setContent(contentItem.getContent().replaceAll(DOMOVOI, StringUtils.EMPTY));
    }

    private String getFormatName(String formatName) {
        String[] split = formatName.split("\\.");
        return split[split.length-1];
    }

    private void saveFile(ContentItem contentItem) throws IOException {
        FtpSession ftpSession = new FtpSession(ResourceUtil.getMessage("ftp.url"), 21
                , ResourceUtil.getMessage("ftp.login")
                , ResourceUtil.getMessage("ftp.password"));

        URL imageURL = new URL(contentItem.getBigImg());
        RenderedImage img = ImageIO.read(imageURL);

        File outputfile = new File(D_TEMP +BIG + imageURL.getFile());
        outputfile.mkdirs();
        ImageIO.write(img, getFormatName(imageURL.getFile()), outputfile);

        RenderedImage smallImg = ImageTransformer.transformImage(outputfile) ;

        File outputfileSmall = new File(D_TEMP + imageURL.getFile());
        outputfileSmall.mkdirs();
        ImageIO.write(smallImg, getFormatName(imageURL.getFile()), outputfileSmall);


        String folderName = Transliterator.transliterate(contentItem.getTitle()).toLowerCase();
        ftpSession.uploadToFTP(outputfile, folderName + BIG + outputfile.getName());
        ftpSession.uploadToFTP(outputfileSmall, folderName + DELIMITER + outputfileSmall.getName());

        contentItem.setBigImg(ROOT_FOLDER + folderName + BIG + outputfile.getName());
        contentItem.setSmallImg(ROOT_FOLDER + folderName + DELIMITER + outputfile.getName());
    }
}
