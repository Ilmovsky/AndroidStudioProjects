package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Lexa on 27.04.2016.
 */
@Root(name = "image")
public class ImageInfo {

    @Element(name = "url")
    private String url;
    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;

    public ImageInfo() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
