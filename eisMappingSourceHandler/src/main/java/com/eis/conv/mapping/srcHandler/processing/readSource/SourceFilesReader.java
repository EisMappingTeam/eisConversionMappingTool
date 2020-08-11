package com.eis.conv.mapping.srcHandler.processing.readSource;

import com.eis.conv.mapping.srcHandler.source.obj.JFileHandler;
import com.eis.conv.mapping.srcHandler.source.obj.XmlFileHandler;
import com.eis.conv.mapping.srcHandler.source.obj.files.SourceFileHandler;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourceJavaFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourcePropertyFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.files.SourceXmlFile;
import com.eis.conv.mapping.srcHandler.source.obj.files.types.SourceFileType;
import com.eis.conv.mapping.srcHandler.source.repo.repoObjects.RepoDir;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void readRepo(String project, String product, String version, List<RepoDir> repoFolders) throws IOException, ParserConfigurationException, SAXException {
        cleanRepoData();
        for (RepoDir repoFolder : repoFolders) {
            repoFolder.loadFilesAll();

            //TODO: refactor it
            try {
                readOneRepo(project, product, version, repoFolder);
            } catch (IOException | ParserConfigurationException | SAXException e) {
                errorFiles.add("");
            }
        }
    }

    private void cleanRepoData() {
        javaFiles = new ArrayList<>();
        xmlFiles = new ArrayList<>();
        propertyFiles = new ArrayList<>();
        unknownFiles = new ArrayList<>();
    }

    private void readOneRepo(String project, String product, String version, RepoDir repoFolder) throws IOException, ParserConfigurationException, SAXException {
        int i = 0;
        for (String file : repoFolder.getFilesAll()) {
            i++;
            System.out.println("File (" + i + "): " + file);

            try {
                if (SourceFileHandler.getFileType(file) == SourceFileType.JAVA) {
                    //java load
                    readOneFileJava(project, product, version, repoFolder.getName(), file);

                } else if (SourceFileHandler.getFileType(file) == SourceFileType.XML) {
                    //XML load
                    readOneFileXml(project, product, version, repoFolder.getName(), file);

                } else if (SourceFileHandler.getFileType(file) == SourceFileType.PROPERTIES) {
                    //properties load
                    propertyFiles.add(new SourcePropertyFile());
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

        jFileAnnotations.setProject(project);
        jFileAnnotations.setProduct(product);
        jFileAnnotations.setVersion(version);
        jFileAnnotations.setPartOfProduct(partOfProduct);

        javaFiles.add(jFileAnnotations);

    }


    private void readOneFileXml(String project, String product, String version, String partOfProduct, String file) throws IOException, ParserConfigurationException, SAXException {
        //XML load
        SourceXmlFile xmlFile = XmlFileHandler.loadFromFile(file);
        xmlFile.setProject(project);
        xmlFile.setProduct(product);
        xmlFile.setVersion(version);
        xmlFile.setPartOfProduct(partOfProduct);
        xmlFiles.add(xmlFile);

    }


}
