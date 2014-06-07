package com.vseostroyke.upload;

import com.vseostroyke.upload.http.TemplateBuilder;
import com.vseostroyke.upload.normalizer.MebelNormalizer;
import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TemplateException {
//        ResourceUtil.getDynamicProperties();
//        ContentCopier contentCopier = new ContentCopier();
//        contentCopier.copy(Arrays.asList(
//                "http://www.domovoy.by/good/id/4973/900/901"
//                , "http://www.domovoy.by/good/id/2047/700/709"),new MebelNormalizer());
        BufferedImage image = ImageIO.read(new File("d:\\temp\\i\\items\\stol-obed_Georgia-ext.jpg"));

        int imageWidth  = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double)500/imageWidth;
        double scaleY = (double)375/imageHeight;
        if(scaleX>scaleY) {
            AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleY, scaleY);
            AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
            image = bilinearScaleOp.filter(
                    image,
                    new BufferedImage((int) (imageWidth * scaleY), 375, image.getType()));


            BufferedImage resizedImage = new BufferedImage(500, 375, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setColor(Color.WHITE);

            g.fillRect(0, 0, 500, 375);
            g.drawImage(image, (500 - (int) (imageWidth * scaleY)) / 2, 0, (int) (imageWidth * scaleY), 375, null);
            ImageIO.write(resizedImage, "jpg", new File("d:\\temp\\i\\items\\podstavka_pod_cvety_4-small.jpg"));
        }else{
            AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleX);
            AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
            image = bilinearScaleOp.filter(
                    image,
                    new BufferedImage(500, (int) (imageHeight * scaleX), image.getType()));


            BufferedImage resizedImage = new BufferedImage(500, 375, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setColor(Color.WHITE);

            g.fillRect(0, 0, 500, 375);
            g.drawImage(image, 0, (375-(int) (imageHeight * scaleX))/2, 500, (int) (imageHeight * scaleX), null);
            ImageIO.write(resizedImage, "jpg", new File("d:\\temp\\i\\items\\stol-obed_Georgia-ext-small.jpg"));

        }

    }


}
