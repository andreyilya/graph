package com.vseostroyke.upload.normalizer;

import com.vseostroyke.upload.http.ContentItem;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/**
 * User: a.radkov
 * Date: 22.05.2014
 */
public abstract class NormalizerBase implements Normalizer {
    @Override
    public ContentItem normalize(ContentItem contentItem) {
        basicNormalization(contentItem);
        return contentItem;
    }

    private void basicNormalization(ContentItem contentItem) {
        if (StringUtils.isBlank(contentItem.getHeader())) {
            contentItem.setHeader(getNormalizedHeader(contentItem));
        }
        if (StringUtils.isBlank(contentItem.getTitle())) {
            contentItem.setTitle(getNormalizedTitle(contentItem));
        }
        if (StringUtils.isBlank(contentItem.getDescription())) {
            contentItem.setDescription(getNormalizedDescription(contentItem));
        }
    }

    protected String getNormalizedHeader(ContentItem contentItem) {
        if (StringUtils.isNotBlank(contentItem.getTitle())) {
            return contentItem.getTitle();
        } else if (StringUtils.isNotBlank(contentItem.getDescription())) {
            return contentItem.getDescription();
        }
        return StringUtils.EMPTY;
    }

    protected String getNormalizedTitle(ContentItem contentItem) {
        if (StringUtils.isNotBlank(contentItem.getHeader())) {
            return contentItem.getHeader();
        } else if (StringUtils.isNotBlank(contentItem.getDescription())) {
            return contentItem.getDescription();
        }
        return StringUtils.EMPTY;
    }

    protected String getNormalizedDescription(ContentItem contentItem) {
        if (StringUtils.isNotBlank(contentItem.getHeader())) {
            return contentItem.getHeader();
        } else if (StringUtils.isNotBlank(contentItem.getTitle())) {
            return contentItem.getTitle();
        }
        return StringUtils.EMPTY;
    }

    @Override
    public List<ContentItem> normalize(List<ContentItem> contentItems) {
        List<ContentItem> normalizedContentItems = new ArrayList<>();
        for (ContentItem contentItem : contentItems) {
            normalizedContentItems.add(normalize(contentItem));
        }
        return normalizedContentItems;
    }
}
