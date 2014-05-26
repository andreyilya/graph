package com.vseostroyke.upload.normalizer;

import com.vseostroyke.upload.http.ContentItem;
import java.io.IOException;
import java.util.List;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public interface Normalizer {
    public ContentItem normalize(ContentItem contentItem) throws IOException;

    public List<ContentItem> normalize(List<ContentItem> contentItems) throws IOException;
}
