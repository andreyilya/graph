package com.vseostroyke.upload;

import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.http.TemplateBuilder;
import com.vseostroyke.upload.sql.RemoteRepository;
import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.awt.Color;
import java.awt.image.BufferedImage;
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
public class Main {
    private static final String DUMP_FILE = "dump.file";
    public static final Long CATEGORY_ID = 35L;
    public static final String TYPE = "interer";
    public static final String BASE = "polukolonny";
    public static final String COLLECTION = "Дополнительные элементы полуколонн";
    public static final String COLLECTION_LINK = "polukolonny";
    public static final String WITH_PHOTO = "false";


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TemplateException {
//        ResourceUtil.getDynamicProperties();
//        ContentCopier contentCopier = new ContentCopier();
//        contentCopier.copy(Arrays.asList(
//                "http://www.domovoy.by/good/id/4973/900/901"
//                , "http://www.domovoy.by/good/id/2047/700/709"), new MebelNormalizer());

//        Map<String, String> codes = new HashMap<>();


        List<String> codes = new ArrayList<>();
        codes.add("1.18.002");
        codes.add("1.18.001");
        codes.add("1.18.003");
        codes.add("1.17.600");
        codes.add("1.15.100");
        codes.add("1.15.300");
        codes.add("1.15.200");












        List<ContentItem> contentItems = createContentItems(codes);

        TemplateBuilder.build(contentItems);
        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.generateSqlFile(contentItems, ResourceUtil.getMessage(DUMP_FILE));

        /*String pathname = "d:\\Downloads\\foto\\lepnina\\";
        File file1 = new File(pathname);
        Collection<File> files = FileUtils.listFiles(file1, new String[]{"png"}, false);
        for (File file : files) {
            if (file.getName().contains("-1")) {
                File destFile = new File(pathname + file.getName().replace("-1", "-photo1"));
                FileUtils.copyFile(file, destFile);

                BufferedImage img = ImageIO.read(new File(pathname + file.getName().replace("-1", "-photo1")));

                BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
                        img.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
                ImageIO.write(newBufferedImage, "jpg", new File(pathname + file.getName().replace("-1", "-photo1").replace("png", "jpg")));
                 FileUtils.forceDelete(file);
                FileUtils.forceDelete(destFile);
            } else if (file.getName().contains("-2")) {
                File destFile = new File(pathname + file.getName().replace("-2", "-photo2"));
                FileUtils.copyFile(file, destFile);

                BufferedImage img = ImageIO.read(new File(pathname + file.getName().replace("-2", "-photo2")));

                BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
                        img.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
                ImageIO.write(newBufferedImage, "jpg", new File(pathname + file.getName().replace("-2", "-photo2").replace("png", "jpg")));
                 FileUtils.forceDelete(file);
                FileUtils.forceDelete(destFile);
            } else if (file.getName().contains("-s")) {
                File destFile = new File(pathname + file.getName().replace("-s", "-sechenie"));
                FileUtils.copyFile(file, destFile);

                BufferedImage img = ImageIO.read(new File(pathname + file.getName().replace("-s", "-sechenie")));
                BufferedImage newBufferedImage = new BufferedImage(img.getWidth(),
                        img.getHeight(), BufferedImage.TYPE_INT_RGB);
                newBufferedImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
                ImageIO.write(newBufferedImage, "jpg", new File(pathname + file.getName().replace("-s", "-sechenie").replace("png", "jpg")));
                  FileUtils.forceDelete(file);
                FileUtils.forceDelete(destFile);
            }


        }  */
    }

    private static List<ContentItem> createContentItems(List<String> codes) {
        List<ContentItem> contentItems = new ArrayList<>();
        for (String code : codes) {
            ContentItem contentItem = new ContentItem();

            contentItem.setCategoryId(CATEGORY_ID);

            contentItem.setCodeFull(code);
            contentItem.setCode(code);

            contentItem.setHeader(COLLECTION + " из полиуретана " + code);
            contentItem.setTitle(COLLECTION + " из полиуретана " + code + " - Европласт");
            contentItem.setKeywords(COLLECTION + " " + code);
            contentItem.setDescription("Купить " + COLLECTION + " " + code + " из полиуретана в Минске и РБ. В наличии. Дешевая доставка по РБ. Бесплатный выезд с образцами по Минску! Есть дизайнеры и монтажники");
            contentItem.setWide("true");
            contentItem.setItemName(code);
            contentItem.setSmallImg("http://lepnina.vseostroyke.by/wp-content/uploads/" + BASE + "/" + code + ".jpg");
            contentItems.add(contentItem);
        }
        return contentItems;
    }


}
