package com.eis.conv.mapping.srcHandler.startup.params.usr;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;


public class UserCompareImportExport {

    @JacksonXmlProperty(localName = "importFile")
    private String importFile;

    @JacksonXmlProperty(localName = "exportFile")
    private String exportFile;


    @JacksonXmlProperty(localName = "resultDir")
    private String resultDir;

    public String getImportFile() {
        return importFile;
    }

    public void setImportFile(String importFile) {
        this.importFile = importFile;
    }

    public String getExportFile() {
        return exportFile;
    }

    public void setExportFile(String exportFile) {
        this.exportFile = exportFile;
    }

    public String getResultDir() {
        return resultDir;
    }

    public void setResultDir(String resultDir) {
        this.resultDir = resultDir;
    }
}




