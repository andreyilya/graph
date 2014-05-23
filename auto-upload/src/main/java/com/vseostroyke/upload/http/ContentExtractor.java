package com.vseostroyke.upload.http;

import com.vseostroyke.upload.util.ResourceUtil;
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

    public List<ContentItem> extract(List<String> urls) throws IOException, SAXException, ParserConfigurationException {
        List<ContentItem> contentItems = new ArrayList<>();
        for (String url : urls) {
            contentItems.add(extract(url));
        }
        return contentItems;
    }

    public ContentItem extract(String url) throws ParserConfigurationException, IOException, SAXException {
        ContentDOMpath domPath = prepareDomPath();
        Document doc = Jsoup.connect(url).timeout(100000).get();

        Elements title = doc.select(domPath.getTitleXpath());
        Elements description = doc.select(domPath.getDescriptionXpath());
        Elements keywords = doc.select(domPath.getKeywordsXpath());
        Elements content = doc.select(domPath.getContentXpath());
        Elements header = doc.select(domPath.getHeaderXpath());

        ContentItem contentItem = new ContentItem();
        contentItem.setTitle(getInnerHtml(title));
        contentItem.setDescription(getAttribute(description, "content"));
        contentItem.setKeywords(getAttribute(keywords, "content"));
        contentItem.setContent(getOuterHtml(content));
        contentItem.setHeader(getOuterHtml(header));
        contentItem.setCategoryId(Long.parseLong(ResourceUtil.getMessage("category.id")));
        contentItem.setWide(ResourceUtil.getMessage("wide"));

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


    private ContentDOMpath prepareDomPath() {
        ContentDOMpath domPath = new ContentDOMpath();
        domPath.setTitleXpath(ResourceUtil.getMessage("xpath.title"));
        domPath.setContentXpath(ResourceUtil.getMessage("xpath.content"));
        domPath.setDescriptionXpath(ResourceUtil.getMessage("xpath.description"));
        domPath.setKeywordsXpath(ResourceUtil.getMessage("xpath.keywords"));
        domPath.setHeaderXpath(ResourceUtil.getMessage("xpath.header"));
        return domPath;
    }
}
