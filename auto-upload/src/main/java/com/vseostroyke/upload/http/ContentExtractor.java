package com.vseostroyke.upload.http;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class ContentExtractor {

    public ContentItem extract(String url) throws ParserConfigurationException, IOException, SAXException {
        ContentDOMpath domPath = prepareDomPath();
        Document doc = Jsoup.connect(url).timeout(100000).get();

        Elements title = doc.select(domPath.getTitleXpath());
        Elements description = doc.select(domPath.getDescriptionXpath());
        Elements keywords = doc.select(domPath.getKeywordsXpath());
        Elements content = doc.select(domPath.getContentXpath());
        Elements header = doc.select(domPath.getHeaderXpath());

        ContentItem contentItem = new ContentItem();
        contentItem.setTitle(getHtml(title));
        contentItem.setDescription(getAttribute(description, "content"));
        contentItem.setKeywords(getAttribute(keywords, "content"));
        contentItem.setContent(getHtml(content));
        contentItem.setHeader(getHtml(header));
        return contentItem;
    }

    private String getAttribute(Elements elements, String attribute) {
        return elements.first().attr(attribute);
    }

    private String getHtml(Elements title) {
        if (title.first() != null) {
            return title.first().html();
        } else {
            return "";
        }
    }


    private ContentDOMpath prepareDomPath() {
        ContentDOMpath domPath = new ContentDOMpath();
        domPath.setTitleXpath("title");
        domPath.setContentXpath("#content");
        domPath.setDescriptionXpath("meta[name=description]");
        domPath.setKeywordsXpath("meta[name=keywords]");
        domPath.setHeaderXpath("h1");
        return domPath;
    }
}
