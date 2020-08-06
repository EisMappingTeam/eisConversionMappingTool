package com.eis.conv.mapping.srcHandler.source.startup.parameters.user;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class UserDownloadRepo {

    @JacksonXmlProperty(localName = "project")
    private String project;

    @JacksonXmlProperty(localName = "product")
    private String product;

    @JacksonXmlProperty(localName = "version")
    private String version;

    @JacksonXmlProperty(localName = "sourceAlias")
    private String sourceAlias;

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

    public String getSourceAlias() {
        return sourceAlias;
    }

    public void setSourceAlias(String sourceAlias) {
        this.sourceAlias = sourceAlias;
    }
}
