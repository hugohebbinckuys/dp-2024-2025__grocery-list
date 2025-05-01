package com.fges.FileManagement;

import java.io.File;

public class FileManager {
    private String chemin;
    private String fileType;
    private String fileName;

    public FileManager(String chemin, String fileType, String fileName) {
        this.chemin = chemin;
        this.fileType = fileType;
        this.fileName = fileName;
    }

    public String getChemin() {

        return this.chemin + this.fileName + "." + this.fileType;
    }

    public String getFileType() {
        return fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public File getFileObject(){return new File(getChemin());}
}
