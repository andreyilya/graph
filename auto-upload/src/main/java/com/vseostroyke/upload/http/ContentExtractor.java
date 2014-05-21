package com.vseostroyke.upload.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class ContentExtractor {
    public ContentItem extract(String url) {
        ContentXpath xpath = prepareXpath();
        return new ContentItem();
    }

    public String getHTML(String url) {
        String result = "";
        try {
            HttpClient client = new DefaultHttpClient();
            HttpParams httpParameters = client.getParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
            HttpConnectionParams.setSoTimeout(httpParameters, 5000);
            HttpConnectionParams.setTcpNoDelay(httpParameters, true);
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);
            InputStream ips = response.getEntity().getContent();
            BufferedReader buf = new BufferedReader(new InputStreamReader(ips, "UTF-8"));
            StringBuilder sb = new StringBuilder();
            String s;
            while (true) {
                s = buf.readLine();
                if (s == null || s.length() == 0)
                    break;
                sb.append(s);
            }
            buf.close();
            ips.close();
            result = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private ContentXpath prepareXpath() {
        ContentXpath xpath = new ContentXpath();
        xpath.setTitleXpath("title");
        xpath.setContentXpath("content");
        xpath.setDescriptionXpath("description");
        xpath.setKeywordsXpath("keywords");
        xpath.setHeaderXpath("h1");
        return xpath;
    }
}
