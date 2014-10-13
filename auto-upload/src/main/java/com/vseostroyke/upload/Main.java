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
    public static final Long CATEGORY_ID = 35L;
    public static final double PRICE = 170000D;
    public static final String SIZE = "19,6 х 6,2 х 1,0-1,4";
    public static final String COUNT = "46";
    public static final String COLLECTION = "Кирпич Венеция";
    public static final String COLLECTION_LINK = "kirpich-venecia";
    public static final String WEIGHT = "12.3";
    public static final String PACKSIZE = "0.68";
    public static final String UGOL = "есть";


    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TemplateException {
//        ResourceUtil.getDynamicProperties();
//        ContentCopier contentCopier = new ContentCopier();
//        contentCopier.copy(Arrays.asList(
//                "http://www.domovoy.by/good/id/4973/900/901"
//                , "http://www.domovoy.by/good/id/2047/700/709"), new MebelNormalizer());

        Map<String, String> codes = new HashMap<>();
        codes.put("2600П", "2600");
        codes.put("2605П", "2605");





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
            contentItem.setTitle("Высококачественный декоративный камень «" + COLLECTION + " " + key + "» - купите по низкой цене!");
            contentItem.setKeywords(COLLECTION + " " + key);
            contentItem.setDescription("декоративный камень «" + COLLECTION + " " + key + "» покупают в лучшем интернет-магазине  \"Линия Камня\". Наличие на складе. Скидки на обьем. Доставка по Минску и РБ");
            contentItem.setWide("true");
            contentItem.setItemName(COLLECTION + " " + key);
            contentItem.setPrice(PRICE);
            contentItem.setSmallImg("http://liniyakamnya.by/wp-content/uploads/" + COLLECTION_LINK + "/" + codes.get(key) + ".jpg");
            contentItems.add(contentItem);
        }
        return contentItems;
    }


}
