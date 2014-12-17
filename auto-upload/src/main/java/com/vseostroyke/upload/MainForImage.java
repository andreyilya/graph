package com.vseostroyke.upload;

import com.vseostroyke.upload.util.ImageTransformer;
import freemarker.template.TemplateException;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
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

            RenderedImage newBufferedImage = ImageTransformer.transformImage(new File(SOURCE_FOLDER + file.getName()), WIDTH, HEIGHT);
            //DO NOT USE ImageIo directly  !!!
            ImageWriter writer;
            FileImageOutputStream output;

            writer = ImageIO.getImageWritersByFormatName("jpeg").next();
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(1);
            output = new FileImageOutputStream(new File(DEST_FOLDER
                    + file.getName()));
            writer.setOutput(output);
            IIOImage iioImage = new IIOImage(newBufferedImage, null, null);
            writer.write(null, iioImage, param);

        }
    }

}
