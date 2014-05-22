package com.vseostroyke.upload.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
        for(String url:urls) {
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
        contentItem.setCategoryId(2L);//TODO: normal
        contentItem.setWide("true");//TODO: normal
        return contentItem;
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
        domPath.setTitleXpath("title");
        domPath.setContentXpath("#content #content");
        domPath.setDescriptionXpath("meta[name=description]");
        domPath.setKeywordsXpath("meta[name=keywords]");
        domPath.setHeaderXpath("h1");
        return domPath;
    }
}
