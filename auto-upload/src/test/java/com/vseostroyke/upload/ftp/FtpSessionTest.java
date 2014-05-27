package com.vseostroyke.upload.ftp;

import com.vseostroyke.upload.util.ResourceUtil;
import java.io.File;
import java.io.IOException;
import org.junit.Test;

public class FtpSessionTest {
    @Test
    public void testUpload() throws IOException {
        FtpSession ftpSession = new FtpSession(ResourceUtil.getMessage("ftp.url"), 21
                , ResourceUtil.getMessage("ftp.login")
                , ResourceUtil.getMessage("ftp.password"));
        File outputfile = new File("d:/temp" + "/i/items/stol-obed_Georgia-ext.jpg");

        ftpSession.uploadToFTP(outputfile, "/i/items/stol-obed_Georgia-ext.jpg");
    }

}