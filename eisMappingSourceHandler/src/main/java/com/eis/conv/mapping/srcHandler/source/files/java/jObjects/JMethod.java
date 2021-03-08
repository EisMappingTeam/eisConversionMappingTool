package com.eis.conv.mapping.srcHandler.source.files.java.jObjects;

public class JMethod {
    private String name = "";
    private String body = "";

    public JMethod() {
    }

    public JMethod(String name, String body) {
        this.name = name;
        this.body = body;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getBodyHash() {
        return body.hashCode();
    }

}
