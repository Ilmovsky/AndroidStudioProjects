package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Attribute;


/**
 * Created by Lexa on 27.04.2016.
 */
public class EnclosureInfo {

    @Attribute(name = "url")
    private String url;
    @Attribute(name = "type")
    private String type;
    @Attribute(name = "length")
    private String length;

    public EnclosureInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
