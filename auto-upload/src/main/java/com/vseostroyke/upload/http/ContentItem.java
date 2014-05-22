package com.vseostroyke.upload.http;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class ContentItem {
    private String header;
    private String title;
    private String description;
    private String keywords;
    private String content;
    private String wide;

    private Long categoryId;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getWide() {
        return wide;
    }

    public void setWide(String wide) {
        this.wide = wide;
    }
}
