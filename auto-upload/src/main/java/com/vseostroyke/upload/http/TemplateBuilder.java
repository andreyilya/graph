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


        return "[one_lepnina code=\"CODE\" base=\"BASE\" with_photo=\"WITH_PHOTO\" without_sechenie=\"true\" type=\"TYPE\" category_url=\"LINK\"]"
                .replace("CODE", contentItem.getCode())
                .replace("LINK", Main.COLLECTION_LINK)
                .replace("WITH_PHOTO",Main.WITH_PHOTO)
                .replace("BASE",Main.BASE)
                .replace("TYPE",Main.TYPE);
    }

    public static void build(List<ContentItem> contentItems) throws IOException, TemplateException {
        for (ContentItem contentItem : contentItems) {
            contentItem.setFinalContent(build(contentItem));
        }
    }
}
