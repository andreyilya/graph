package com.vseostroyke.upload.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
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
        Document doc = Jsoup.connect(url).get();
        Elements title = doc.select(domPath.getTitleXpath());
        Elements description = doc.select(domPath.getDescriptionXpath());
        Elements keywords = doc.select(domPath.getKeywordsXpath());
        ContentItem contentItem = new ContentItem();
        contentItem.setTitle(title.first().html());
        contentItem.setDescription(description.first().attr("content"));
        contentItem.setKeywords(keywords.first().attr("content"));
        return contentItem;
    }


    private ContentDOMpath prepareDomPath() {
        ContentDOMpath domPath = new ContentDOMpath();
        domPath.setTitleXpath("title");
        domPath.setContentXpath("content");
        domPath.setDescriptionXpath("meta[name=description]");
        domPath.setKeywordsXpath("meta[name=keywords]");
        domPath.setHeaderXpath("h1");
        return domPath;
    }
}
