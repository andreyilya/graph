package com.vseostroyke.upload.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * User: a.radkov
 * Date: 17.06.2014
 */
public final class ImageTransformer {

    public static final Double IMAGE_WIDTH = Double.parseDouble(ResourceUtil.getMessage("photo.width"));
    public static final Double IMAGE_HEIGHT = Double.parseDouble(ResourceUtil.getMessage("photo.height"));

    private ImageTransformer() {
    }

    public static RenderedImage transformImage(File path) throws IOException {
        BufferedImage image = ImageIO.read(path);

        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = IMAGE_WIDTH / imageWidth;
        double scaleY = IMAGE_HEIGHT / imageHeight;
        if (scaleX > scaleY) {
            AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleY, scaleY);
            AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
            image = bilinearScaleOp.filter(
                    image,
                    new BufferedImage((int) (imageWidth * scaleY), IMAGE_HEIGHT.intValue(), image.getType()));


            BufferedImage resizedImage = new BufferedImage(IMAGE_WIDTH.intValue(), IMAGE_HEIGHT.intValue(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setColor(Color.WHITE);

            g.fillRect(0, 0, IMAGE_WIDTH.intValue(), IMAGE_HEIGHT.intValue());
            g.drawImage(image, (IMAGE_WIDTH.intValue() - (int) (imageWidth * scaleY)) / 2, 0, (int) (imageWidth * scaleY), IMAGE_HEIGHT.intValue(), null);
            return resizedImage;
        } else {
            AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleX);
            AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);
            image = bilinearScaleOp.filter(
                    image,
                    new BufferedImage(IMAGE_WIDTH.intValue(), (int) (imageHeight * scaleX), image.getType()));


            BufferedImage resizedImage = new BufferedImage(IMAGE_WIDTH.intValue(), IMAGE_HEIGHT.intValue(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = resizedImage.createGraphics();
            g.setColor(Color.WHITE);

            g.fillRect(0, 0, IMAGE_WIDTH.intValue(), IMAGE_HEIGHT.intValue());
            g.drawImage(image, 0, (IMAGE_HEIGHT.intValue() - (int) (imageHeight * scaleX)) / 2, IMAGE_WIDTH.intValue(), (int) (imageHeight * scaleX), null);
            return resizedImage;
        }
    }
}
