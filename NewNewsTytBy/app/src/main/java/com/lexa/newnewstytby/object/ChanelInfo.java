package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 27.04.2016.
 */

@Root(name="channel")
public class ChanelInfo {

    @Element(name = "title")
    private String title;
    @ElementList(entry="link",type=LinkInfo.class,inline=true, required=false)
    private List<Iteminfo> link = new ArrayList<>();
    @Element(name = "description")
    private String description;
    @Element(name = "language")
    private String language;
    @Element(name = "image")
    private ImageInfo image;
    @Element(name = "pubDate")
    private String pubDate;
    @Element(name = "lastBuildDate")
    private String lastBuildDate;
    @Element(name = "ttl")
    private String ttl;
    @ElementList(entry="item", inline=true)
    private List<Iteminfo> item = new ArrayList<>();


    public ChanelInfo() {
    }

    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public ImageInfo getImage() {
        return image;
    }

    public void setImage(ImageInfo image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLastBuildDate() {
        return lastBuildDate;
    }

    public void setLastBuildDate(String lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    public String getTtl() {
        return ttl;
    }

    public void setTtl(String ttl) {
        this.ttl = ttl;
    }

    public List<Iteminfo> getItem() {
        return item;
    }

    public void setItem(List<Iteminfo> item) {
        this.item = item;
    }

    public List<Iteminfo> getLink() {
        return link;
    }

    public void setLink(List<Iteminfo> link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "ChanelInfo{" +
                "title='" + title + '\'' +
                ", link=" + link +
                ", description='" + description + '\'' +
                ", language='" + language + '\'' +
                ", image=" + image +
                ", pubDate='" + pubDate + '\'' +
                ", lastBuildDate='" + lastBuildDate + '\'' +
                ", ttl='" + ttl + '\'' +
                ", item=" + item +
                '}';
    }
}
