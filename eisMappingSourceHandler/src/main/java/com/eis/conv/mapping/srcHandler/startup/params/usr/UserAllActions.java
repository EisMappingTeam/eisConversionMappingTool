package com.eis.conv.mapping.srcHandler.startup.params.usr;

public enum UserAllActions {
    DOWNLOAD_REPO("DOWNLOAD_REPO"), //download from Mercurial
    LOAD_SOURCE("LOAD_SOURCE"),     //load folders structure and load java-xml-properties
    XML_COMPARE_IMPORT_VS_EXPORT("XML_COMPARE_IMPORT_VS_EXPORT");     //2 XML comparision. For example: compare import XML vs export

    private final String action;

    UserAllActions(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }
}
