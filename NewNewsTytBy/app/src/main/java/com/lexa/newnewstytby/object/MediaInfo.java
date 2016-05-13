package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;

/**
 * Created by Lexa on 27.04.2016.
 */
public class MediaInfo {


    @Attribute(name = "url")
    private String url;
    @Attribute(name = "type")
    private String type;
    @Attribute(name = "medium")
    private String medium;
    @Attribute(name = "height")
    private String height;
    @Attribute(name = "width")
    private String width;
    @Attribute(name = "fileSize")
    private String fileSize;

    public MediaInfo() {
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
