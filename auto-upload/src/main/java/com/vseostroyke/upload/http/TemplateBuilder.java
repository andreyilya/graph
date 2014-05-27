package com.vseostroyke.upload.http;

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
        Template template = cfg.getTemplate("mebelTemplate.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("content", contentItem.getContent());
        data.put("title", contentItem.getTitle());
        data.put("img", contentItem.getImg());
        data.put("baseUrl", contentItem.getBaseUrl());

        for (Map.Entry<String, String> entry : contentItem.getDynamicProperties().entrySet()) {
            data.put(entry.getKey(), entry.getValue());

        }

        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.toString();
    }

    public static void build(List<ContentItem> contentItems) throws IOException, TemplateException {
        for (ContentItem contentItem : contentItems) {
            contentItem.setFinalContent(build(contentItem));
        }
    }
}
