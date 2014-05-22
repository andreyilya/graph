package com.vseostroyke.upload.sql;

import com.vseostroyke.upload.http.ContentItem;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;

/**
 * User: a.radkov
 * Date: 21.05.2014
 */
public class RemoteRepository {

	public void generateSql(ContentItem contentItem, String path) {
		String sqlTemplate = "START TRANSACTION;" +
				" insert into wp_posts" +
				" (post_author, post_content, post_title, post_status, comment_status, ping_status,post_name,post_type) values" +
				" (3,'%s','%s','draft','closed','closed','testing-query','post');" +
				"SET @last_insert_id = LAST_INSERT_ID();" +
				" insert into wp_term_relationships (object_id,term_taxonomy_id) values (@last_insert_id, 2);COMMIT;";
		String sqlCode = String.format(sqlTemplate,
				contentItem.getContent(), contentItem.getTitle());
		try {
			FileUtils.writeStringToFile(new File("d://dump.sql"), sqlCode);
		} catch (IOException e) {
			//DO NOTHING
		}
	}

}
