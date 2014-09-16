package com.vseostroyke.upload;

import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.http.TemplateBuilder;
import com.vseostroyke.upload.sql.RemoteRepository;
import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class Main {
    private static final String DUMP_FILE = "dump.file";
    public static final long CATEGORY_ID = 15L;
    public static final double PRICE = 305000D;
    public static final String SIZE = "20,30,40 x 10 x 3";
    public static final String COUNT = "35";
    public static final String COLLECTION = "Слоистый кварц";
    public static final String COLLECTION_LINK = "sloisty-kvarz";
    public static final String WEIGHT = "45";
    public static final String UGOL = "есть";


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TemplateException {
//        ResourceUtil.getDynamicProperties();
//        ContentCopier contentCopier = new ContentCopier();
//        contentCopier.copy(Arrays.asList(
//                "http://www.domovoy.by/good/id/4973/900/901"
//                , "http://www.domovoy.by/good/id/2047/700/709"), new MebelNormalizer());

        Map<String, String> codes = new HashMap<>();
        codes.put("100П", "100");
        codes.put("101П", "101");
        codes.put("102П", "102");
        codes.put("103П", "103");
        codes.put("107П", "107");
        codes.put("108П", "108");
        codes.put("109П", "109");
        codes.put("118П", "118");
        codes.put("119П", "119");



        List<ContentItem> contentItems = createContentItems(codes);

        TemplateBuilder.build(contentItems);
        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.generateSqlFile(contentItems, ResourceUtil.getMessage(DUMP_FILE));


    }

    private static List<ContentItem> createContentItems(Map<String, String> codes) {
        List<ContentItem> contentItems = new ArrayList<>();
        for (String key : codes.keySet()) {
            ContentItem contentItem = new ContentItem();

            contentItem.setCategoryId(CATEGORY_ID);

            contentItem.setCodeFull(key);
            contentItem.setCode(codes.get(key));

            contentItem.setHeader("Декоративный камень «" + COLLECTION + " " + key + "»");
            contentItem.setTitle("Купить декоративный искусственный камень «" + COLLECTION + " " + key + "» - цена, фото");
            contentItem.setKeywords(COLLECTION + " " + key);
            contentItem.setDescription("Предлагаем купить декоративный камень «" + COLLECTION + " " + key + "»  в Минске и РБ с Доставкой. Высокое качество форм! Литовский цемент Portland DO! Низкие цены!");
            contentItem.setWide("true");
            contentItem.setItemName(COLLECTION + " " + key);
            contentItem.setPrice(PRICE);
            contentItem.setSmallImg("http://liniyakamnya.by/wp-content/uploads/" + COLLECTION_LINK + "/" + codes.get(key) + ".jpg");
            contentItems.add(contentItem);
        }
        return contentItems;
    }


}
