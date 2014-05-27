package com.vseostroyke.upload.http;

import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class ContentExtractor {

    public List<ContentItem> extract(List<String> urls) throws IOException, SAXException, ParserConfigurationException, TemplateException {
        List<ContentItem> contentItems = new ArrayList<>();
        for (String url : urls) {
            contentItems.add(extract(url));
        }
        return contentItems;
    }

    public ContentItem extract(String url) throws ParserConfigurationException, IOException, SAXException, TemplateException {
        Document doc = Jsoup.connect(url).timeout(100000).get();

        Elements title = doc.select(ResourceUtil.getMessage("xpath.title"));
        Elements description = doc.select(ResourceUtil.getMessage("xpath.description"));
        Elements keywords = doc.select(ResourceUtil.getMessage("xpath.keywords"));
        Elements content = doc.select(ResourceUtil.getMessage("xpath.content"));
        Elements header = doc.select(ResourceUtil.getMessage("xpath.header"));
        Elements img = doc.select(ResourceUtil.getMessage("xpath.img"));

        ContentItem contentItem = new ContentItem();
        contentItem.setTitle(getInnerHtml(title));
        contentItem.setDescription(getAttribute(description, "content"));
        contentItem.setKeywords(getAttribute(keywords, "content"));
        contentItem.setContent(getOuterHtml(content));
        contentItem.setHeader(getOuterHtml(header));
        contentItem.setImg(getAttribute(img, "href"));
        contentItem.setCategoryId(Long.parseLong(ResourceUtil.getMessage("category.id")));
        contentItem.setWide(ResourceUtil.getMessage("wide"));
        contentItem.setBaseUrl(url);
        contentItem.setDynamicProperties(getDynamicProperties(doc));
        return contentItem;
    }

    private Map<String, String> getDynamicProperties(Document doc) {
        Map<String, String> dynamicProperties = new HashMap<>();
        for (String key : ResourceUtil.getDynamicProperties()) {
            dynamicProperties.put(key, getOuterHtml(doc.select(key)));
        }
        return dynamicProperties;
    }

    private String getAttribute(Elements elements, String attribute) {
        return elements.first().attr(attribute);
    }

    private String getOuterHtml(Elements title) {
        if (title.first() != null) {
            return title.first().outerHtml();
        } else {
            return StringUtils.EMPTY;
        }
    }

    private String getInnerHtml(Elements title) {
        if (title.first() != null) {
            return title.first().html();
        } else {
            return StringUtils.EMPTY;
        }
    }

}
