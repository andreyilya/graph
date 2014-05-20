package com.vseostroyke.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * User: a.radkov
 * Date: 20.05.2014
 */
public class Main {
    public static final String LOGIN = "fftdfbzs29847";
    public static final String PASSWORD = "Q8ibY.y511((32";

    public static void main(String[] args) {
        URL url = null;
        File folder = null;
        try {
            url = new URL ("ftp://" + LOGIN + ":" + PASSWORD + "@k29.hostenko.com");
            URLConnection urlc = url.openConnection();
            InputStream is = urlc.getInputStream();
            //folder = new File (is);
        } catch (Exception e) {
            e.printStackTrace();
        }
        folder.listFiles();
    }
}
