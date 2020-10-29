package com.eis.conv.mapping.srcHandler.processing.readSource;

import com.eis.conv.mapping.srcHandler.source.files.handlers.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.files.handlers.PropertiesFileHandler;
import com.eis.conv.mapping.srcHandler.source.files.handlers.XmlFileHandler;
import com.eis.conv.mapping.srcHandler.source.files.SourceFileChecker;
import com.eis.conv.mapping.srcHandler.source.files.SourceFile;
import com.eis.conv.mapping.srcHandler.source.files.java.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.files.properties.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.files.xml.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.files.types.SourceFileType;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoDir;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SourceFilesReader {
    private List<SourceJavaFile> javaFiles = new ArrayList<>();
    private List<SourceXmlFile> xmlFiles = new ArrayList<>();
    private List<SourcePropertyFile> propertyFiles = new ArrayList<>();
    private List<String> unknownFiles = new ArrayList<>();
    private List<String> errorFiles = new ArrayList<>();

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

    public List<String> getErrorFiles() {
        return errorFiles;
    }

    public void setErrorFiles(List<String> errorFiles) {
        this.errorFiles = errorFiles;
    }

    public void readRepo(String project, String product, String version, List<RepoDir> repoFolders) throws IOException {
        cleanRepoData();
        for (RepoDir repoFolder : repoFolders) {
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

    private void readOneRepo(String project, String product, String version, RepoDir repoFolder) {
        AtomicInteger i = new AtomicInteger(1);
        for (String file : repoFolder.getFilesAll()) {
            System.out.println("File (" + String.valueOf(i.getAndIncrement()) + "): " + file);

            try {
                if (SourceFileChecker.getFileType(file) == SourceFileType.JAVA) { //java load
                    readOneFileJava(project, product, version, repoFolder.getName(), file);

                } else if (SourceFileChecker.getFileType(file) == SourceFileType.XML) { //XML load
                    readOneFileXml(project, product, version, repoFolder.getName(), file);

                } else if (SourceFileChecker.getFileType(file) == SourceFileType.PROPERTIES) { //properties load
                    readOneFileProperties(project, product, version, repoFolder.getName(), file);

                } else {
                    unknownFiles.add(file);
                }

            } catch (IOException | ParserConfigurationException | SAXException e) {
                errorFiles.add(file);
            }
        }
    }


    private void readOneFileJava(String project, String product, String version, String partOfProduct, String file) throws IOException {
        //java load
        SourceJavaFile jFileAnnotations = JFileHandler.loadFromFile(file);
        setRepoInfo(jFileAnnotations, project, product, version, partOfProduct, file);
        javaFiles.add(jFileAnnotations);
    }

    private void readOneFileXml(String project, String product, String version, String partOfProduct, String file) throws IOException, ParserConfigurationException, SAXException {
        //XML load
        SourceXmlFile xmlFile = XmlFileHandler.loadFromFile(file);
        setRepoInfo(xmlFile, project, product, version, partOfProduct, file);
        xmlFiles.add(xmlFile);
    }

    private void readOneFileProperties(String project, String product, String version, String partOfProduct, String file) throws IOException {
        //Properties load
        SourcePropertyFile pFile = PropertiesFileHandler.loadFromFile(file);
        setRepoInfo(pFile, project, product, version, partOfProduct, file);
        propertyFiles.add(pFile);
    }

    private void setRepoInfo(SourceFile sf, String project, String product, String version, String partOfProduct, String file) {
        sf.setProject(project);
        sf.setProduct(product);
        sf.setVersion(version);
        sf.setPartOfProduct(partOfProduct);
    }

}
