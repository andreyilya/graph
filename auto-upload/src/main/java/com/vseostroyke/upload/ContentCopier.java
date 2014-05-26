package com.vseostroyke.upload;

import com.vseostroyke.upload.http.ContentExtractor;
import com.vseostroyke.upload.http.ContentItem;
import com.vseostroyke.upload.http.TemplateBuilder;
import com.vseostroyke.upload.normalizer.Normalizer;
import com.vseostroyke.upload.sql.RemoteRepository;
import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public class ContentCopier {

    private static final String DUMP_FILE = "dump.file";

    public void copy(List<String> urls, Normalizer normalizer) throws ParserConfigurationException, SAXException, IOException, TemplateException {
        ContentExtractor contentExtractor = new ContentExtractor();
        List<ContentItem> contentItems = contentExtractor.extract(urls);
        contentItems = normalizer.normalize(contentItems);
        TemplateBuilder.build(contentItems);
        RemoteRepository remoteRepository = new RemoteRepository();
        remoteRepository.generateSqlFile(contentItems, ResourceUtil.getMessage(DUMP_FILE));
    }
}
