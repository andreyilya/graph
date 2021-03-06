package com.vseostroyke.upload.ftp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class FtpSession {

    public static final String SEPARATOR = "/";


    private FTPClient client;

    public FtpSession(FTPClient client) {
        this.client = client;
    }

    public FtpSession(String address, int port, String username, String password) throws IOException {
        connectToFTP(address, port, username, password);
    }

    public FTPClient connectToFTP(String address, int port, String username, String password)
            throws IOException {
        client = new FTPClient();
        client.connect(address, port);
        client.login(username, password);

        return client;
    }

    public boolean remove(String path) throws IOException {
        boolean completed = this.client.deleteFile(path);
        if (!completed) {
            throw new IOException("Failed to delete '" + path + "'. Server replied with: " + client.getReplyString());
        }
        return completed;
    }

    public FTPFile[] list(String path) throws IOException {
        return this.client.listFiles(path);
    }

    public FTPFile ftpFile(String path, String fileName) throws IOException {
        FTPFile[] files = list(path);
        for (FTPFile file : files) {
            FTPFile f = (FTPFile) file;
            if (f.isFile()) {
                if (f.getName().equals(fileName)) {
                    return file;
                }
            }
        }
        return null;
    }

    public String[] listNames(String path) throws IOException {
        return this.client.listNames(path);
    }

    public void downloadFromFTP(String path, OutputStream fos) throws IOException {
        boolean completed = client.retrieveFile(path, fos);
        if (!completed) {
            throw new IOException("Failed to copy '" + path +
                    "'. Server replied with: " + this.client.getReplyString());
        }
    }

    public boolean downloadFromFTP(String fileName) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/home/house_md/" + fileName);
        boolean isDownload = client.retrieveFile(fileName, fileOutputStream);
        fileOutputStream.flush();
        fileOutputStream.close();

        return isDownload;
    }

    public void uploadToFTP(InputStream inputStream, String path) throws IOException {
        client.setFileType(FTP.BINARY_FILE_TYPE);

        boolean completed = client.storeFile(path, inputStream);
        if (!completed) {
            throw new IOException("Failed to write to '" + path
                    + "'. Server replied with: " + this.client.getReplyString());
        }
    }

    public void uploadToFTP(File file, String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        String remoteFile = "/wp-content/uploads/" + fileName;
        if (!exists(remoteFile)) {
            mkdirs(remoteFile);
        }
        client.setFileType(FTP.BINARY_FILE_TYPE);
        boolean completed = client.storeFile(remoteFile, fileInputStream);
        if (!completed) {
            throw new IOException("Failed to write to '" + file.getName()
                    + "'. Server replied with: " + this.client.getReplyString());
        }
    }

    public void close() throws IOException {
        try {
            this.client.disconnect();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
        }
    }

    public boolean isOpen() {
        try {
            this.client.noop();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void rename(String pathFrom, String pathTo) throws IOException {
        this.client.deleteFile(pathTo);
        boolean completed = this.client.rename(pathFrom, pathTo);
        if (!completed) {
            throw new IOException("Failed to rename '" + pathFrom +
                    "' to " + pathTo + "'. Server replied with: " + this.client.getReplyString());
        }
    }

    public boolean mkdir(String remoteDirectory) throws IOException {
        client.changeToParentDirectory();
        return client.makeDirectory(remoteDirectory);
    }

    public boolean mkdirs(String remoteDirectory) throws IOException {
        boolean dirExists = true;

        //tokenize the string and attempt to change into each directory level.  If you cannot, then start creating.
        String[] directories = remoteDirectory.split("/");
        directories = Arrays.copyOfRange(directories, 0, directories.length - 1);
        for (String dir : directories) {
            if (!dir.isEmpty()) {
                if (dirExists) {
                    dirExists = client.changeWorkingDirectory(dir);
                }
                if (!dirExists) {
                    if (!client.makeDirectory(dir)) {
                        throw new IOException("Unable to create remote directory '" + dir + "'.  error='" + client.getReplyString() + "'");
                    }
                    if (!client.changeWorkingDirectory(dir)) {
                        throw new IOException("Unable to change into newly created remote directory '" + dir + "'.  error='" + client.getReplyString() + "'");
                    }
                }
            }
        }
        return dirExists;
    }

    public boolean exists(String path) throws IOException {

        String currentWorkingPath = this.client.printWorkingDirectory();
        boolean exists = false;

        try {
            if (this.client.changeWorkingDirectory(path)) {
                exists = true;
            }
        } finally {
            this.client.changeWorkingDirectory(currentWorkingPath);
        }

        return exists;
    }

    public String normalizeDir(String directory) {
        if ("".equals(directory)) {
            return directory;
        }

        final String BACKSLASH = "\\";
        String normalizeDirectory = directory.replace(BACKSLASH, SEPARATOR);                                        // Замена всех "\" на "/"
        normalizeDirectory = Paths.get(normalizeDirectory).normalize().toString().replace(BACKSLASH, SEPARATOR);    // Нормализация пути
        normalizeDirectory = normalizeDirectory.equals(SEPARATOR) ? "./" : normalizeDirectory;                      // Если путь это "/", замена "/" на "./"
        normalizeDirectory = normalizeDirectory.substring(0, 1).equals(SEPARATOR) ?                                 // Если первый символ "/", удаление этого символа
                normalizeDirectory.substring(1) : normalizeDirectory;
        return normalizeDirectory;
    }
}
