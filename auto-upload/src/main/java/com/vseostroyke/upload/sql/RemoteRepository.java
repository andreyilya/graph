package com.vseostroyke.upload.sql;

import com.vseostroyke.upload.http.ContentItem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class RemoteRepository {

    public void generateSqlFile(List<ContentItem> contentItems, String path) {
        List<String> scripts = new ArrayList<>();
        for (ContentItem contentItem : contentItems) {
            scripts.add(formatSql(contentItem));
        }
        saveToFile(scripts, path);
    }

    private void saveToFile(List<String> scripts, String path) {
        try {
            FileUtils.writeLines(new File(path), scripts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatSql(ContentItem contentItem) {
        String sqlTemplate = "START TRANSACTION;" +
                " insert into wp_posts" +
                " (post_author, post_content, post_title, post_status, comment_status, ping_status,post_type) values" +
                " (3,'%s','%s','draft','closed','closed','post');" +
                "SET @last_insert_id = LAST_INSERT_ID();" +
                " insert into wp_term_relationships (object_id,term_taxonomy_id) values (@last_insert_id, '%s');" +
                " insert into wp_postmeta (post_id, meta_key, meta_value) values (@last_insert_id, '_aioseop_title', '%s');" +
                " insert into wp_postmeta (post_id, meta_key, meta_value) values (@last_insert_id, '_aioseop_description', '%s');" +
                " insert into wp_postmeta (post_id, meta_key, meta_value) values (@last_insert_id, '_aioseop_keywords', '%s');" +
                " insert into wp_postmeta (post_id, meta_key, meta_value) values (@last_insert_id, 'wide', '%s');" +
                "COMMIT;";
        return String.format(sqlTemplate
                , contentItem.getFinalContent()
                , contentItem.getHeader()
                , contentItem.getCategoryId()
                , contentItem.getTitle()
                , contentItem.getDescription()
                , contentItem.getKeywords()
                , contentItem.getWide());
    }

}
