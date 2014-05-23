package com.vseostroyke.upload.http;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

//import org.apache.velocity.Template;

/**
 * User: a.radkov
 * Date: 23.05.2014
 */
public class TemplateBuilder {
    public static String build() throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(TemplateBuilder.class, "/");
        Template template = cfg.getTemplate("mebelTemplate.ftl");
        Map<String, Object> data = new HashMap<>();
        data.put("foo", "Hello World!");
        StringWriter stringWriter = new StringWriter();
        template.process(data, stringWriter);
        return stringWriter.toString();
    }
}
