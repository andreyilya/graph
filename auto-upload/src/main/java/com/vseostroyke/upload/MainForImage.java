package com.vseostroyke.upload;

import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.http.TemplateBuilder;
import com.vseostroyke.upload.sql.RemoteRepository;
import com.vseostroyke.upload.util.ImageTransformer;
import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class MainForImage {
    private static final String SOURCE_FOLDER = "D:\\Downloads\\foto\\tmp\\";
    private static final String DEST_FOLDER = "D:\\Downloads\\foto\\tmp\\new\\";
    public static final double WIDTH = 220;
    public static final double HEIGHT = 165;


    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TemplateException {

        File sourceFolder = new File(SOURCE_FOLDER);
        Collection<File> files = FileUtils.listFiles(sourceFolder, new String[]{"jpg"}, false);
        for (File file : files) {

            RenderedImage newBufferedImage =  ImageTransformer.transformImage(new File(SOURCE_FOLDER + file.getName()),WIDTH,HEIGHT);
                ImageIO.write(newBufferedImage, "jpg", new File(DEST_FOLDER
                        + file.getName()));

        }
    }

}
