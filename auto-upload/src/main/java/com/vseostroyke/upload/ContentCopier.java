package com.vseostroyke.upload;

import com.vseostroyke.upload.http.ContentExtractor;
import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.normalizer.Normalizer;
import com.vseostroyke.upload.sql.RemoteRepository;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public class ContentCopier {
    public void copy(List<String> urls, Normalizer normalizer) throws ParserConfigurationException, SAXException, IOException {
        //TODO: parameters from properties
        ContentExtractor contentExtractor = new ContentExtractor();
        List<ContentItem> contentItems = contentExtractor.extract(urls);
        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.generateSqlFile(normalizer.normalize(contentItems), "d://dump.sql");
    }
}
