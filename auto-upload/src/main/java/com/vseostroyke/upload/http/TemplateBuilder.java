package com.vseostroyke.upload.http;

import com.vseostroyke.upload.Main;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: a.radkov
 * Date: 23.05.2014
 */
public class TemplateBuilder {
    public static String build(ContentItem contentItem) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(TemplateBuilder.class, "/");
//        Template template = cfg.getTemplate("mebelTemplate.ftl");
        Template template = cfg.getTemplate("kamenTemplate.ftl");
        Map<String, Object> data = new HashMap<>();
//        data.put("content", contentItem.getContent());
//        data.put("title", contentItem.getTitle());
//        data.put("bigImg", contentItem.getBigImg());
//        data.put("smallImg", contentItem.getSmallImg());
//        data.put("baseUrl", contentItem.getBaseUrl());
//        data.put("itemName", contentItem.getItemName());
        data.put("codeFull", contentItem.getCodeFull());
        data.put("code", contentItem.getCode());


//        for (Map.Entry<String, String> entry : contentItem.getDynamicProperties().entrySet()) {
//            data.put(entry.getKey().replace(".", StringUtils.EMPTY), entry.getValue());
//
//        }

        StringWriter stringWriter = new StringWriter();
        // template.process(data, stringWriter);


        return "[one_stone code=\"COLLECTION CODE_FULL\" photo=\"/wp-content/uploads/LINK/CODE.jpg\" photo_big=\"/wp-content/uploads/LINK/big/CODE.jpg\" alt=\"Искусственный камень из бетона COLLECTION CODE_FULL\" title=\"декоративный камень из бетона COLLECTION CODE_FULL\" size=\"SIZE\" count=\"COUNT\" ugol=\"UGOL\" weight=\"WEIGHT\" text=\"\" beton=\"true\" packsize=\"PACK\"]\n\n[beton_action]"
                .replace("CODE_FULL", contentItem.getCodeFull())
                .replace("CODE", contentItem.getCode())
                .replace("COLLECTION", Main.COLLECTION)
                .replace("LINK", Main.COLLECTION_LINK)
                .replace("WEIGHT",Main.WEIGHT)
                .replace("COUNT",Main.COUNT)
                .replace("SIZE",Main.SIZE)
                .replace("UGOL", Main.UGOL)
                .replace("PACK", Main.PACKSIZE);
    }

    public static void build(List<ContentItem> contentItems) throws IOException, TemplateException {
        for (ContentItem contentItem : contentItems) {
            contentItem.setFinalContent(build(contentItem));
        }
    }
}
