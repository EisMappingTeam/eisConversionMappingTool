package com.eis.conv.mapping.srcHandler.source.entities.files.srcFiles;

import com.eis.conv.mapping.srcHandler.source.entities.files.SourceFileHandler;
import com.eis.conv.mapping.srcHandler.source.entities.files.types.SourceFileType;

public class SourceFile {
    private String fileName = "";
    private String fileExtension = "";
    private String project = "";
    private String product = "";
    private String version = "";
    private String partOfProduct = "";

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        //not from disk file - to avoid disk loading
        this.fileExtension = fileExtension;
    }

    public SourceFileType getType() {
        return SourceFileHandler.getFileTypeByExtension(this.fileExtension);
    }

//    public void setType(SourceFileType type) {
//        this.type = type;
//    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPartOfProduct() {
        return partOfProduct;
    }

    public void setPartOfProduct(String partOfProduct) {
        this.partOfProduct = partOfProduct;
    }
}
