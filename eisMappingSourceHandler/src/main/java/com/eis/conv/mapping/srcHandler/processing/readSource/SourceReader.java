package com.eis.conv.mapping.srcHandler.processing.readSource;

import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoFolder;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.SourceFileHandler;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.sourceObjects.files.types.SourceFileType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SourceReader {
    private List<SourceJavaFile> javaFiles = new ArrayList<>();
    private List<SourceXmlFile> xmlFiles = new ArrayList<>();
    private List<SourcePropertyFile> propertyFiles = new ArrayList<>();
    private List<String> unknownFiles = new ArrayList<>();

    public List<SourceJavaFile> getJavaFiles() {
        return javaFiles;
    }

    public void setJavaFiles(List<SourceJavaFile> javaFiles) {
        this.javaFiles = javaFiles;
    }

    public List<SourceXmlFile> getXmlFiles() {
        return xmlFiles;
    }

    public void setXmlFiles(List<SourceXmlFile> xmlFiles) {
        this.xmlFiles = xmlFiles;
    }

    public List<SourcePropertyFile> getPropertyFiles() {
        return propertyFiles;
    }

    public void setPropertyFiles(List<SourcePropertyFile> propertyFiles) {
        this.propertyFiles = propertyFiles;
    }

    public List<String> getUnknownFiles() {
        return unknownFiles;
    }

    public void setUnknownFiles(List<String> unknownFiles) {
        this.unknownFiles = unknownFiles;
    }

    public void readRepo(String project, String product, String version, List<RepoFolder> repoFolders) throws IOException {
        cleanRepoData();
        for (RepoFolder repoFolder : repoFolders) {
            repoFolder.loadFilesAll();
            readOneRepo(project, product, version, repoFolder);
        }
    }

    private void cleanRepoData() {
        javaFiles = new ArrayList<>();
        xmlFiles = new ArrayList<>();
        propertyFiles = new ArrayList<>();
        unknownFiles = new ArrayList<>();
    }

    private void readOneRepo(String project, String product, String version, RepoFolder repoFolder) throws IOException {
        for (String file : repoFolder.getFilesAll()) {
            if (SourceFileHandler.getFileType(file) == SourceFileType.JAVA) {
                //java load
                SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile(file);

                jFileAnnotations.setProject(project);
                jFileAnnotations.setProduct(product);
                jFileAnnotations.setVersion(version);
                jFileAnnotations.setPartOfProduct(repoFolder.getName());

                javaFiles.add(jFileAnnotations);

            } else if (SourceFileHandler.getFileType(file) == SourceFileType.XML) {
                //XML load
            } else if (SourceFileHandler.getFileType(file) == SourceFileType.PROPERTIES) {
                //properties load
            }
        }
    }

}
