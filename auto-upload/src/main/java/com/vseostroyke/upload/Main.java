package com.vseostroyke.upload;

import com.vseostroyke.upload.normalizer.MebelNormalizer;
import com.vseostroyke.upload.util.ResourceUtil;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.util.Arrays;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TemplateException {
        ResourceUtil.getDynamicProperties();
        ContentCopier contentCopier = new ContentCopier();
        contentCopier.copy(Arrays.asList(
                "http://www.domovoy.by/good/id/4973/900/901"
                , "http://www.domovoy.by/good/id/2047/700/709"), new MebelNormalizer());


    }


}
