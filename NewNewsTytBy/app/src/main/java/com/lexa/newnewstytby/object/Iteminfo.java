package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 27.04.2016.
 */

@Root(name = "item")
public class Iteminfo {
    @Element(name = "title")
    private String title;
    @Element(name = "link")
    private String link;
    @Element(name = "description")
    private String description;
    @ElementList(entry="author", type=Autorinfo.class,inline=true, required=false)
    private List<Autorinfo> autor  = new ArrayList<>();
    @Element(name = "category", type=CategoryInfo.class)
    private CategoryInfo category;
    @Element(name = "enclosure", type=EnclosureInfo.class, required=false)
    private EnclosureInfo enclosure;
    @Element(name = "guid", type=GuidInfo.class)
    private GuidInfo guid;
    @Element(name = "pubDate")
    private String pubDate;
    @ElementList(entry="content", type=MediaInfo.class,inline=true, required=false)
    private List<MediaInfo> media = new ArrayList<>();

    public Iteminfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MediaInfo> getMedia() {
        return media;
    }

    public void setMedia(List<MediaInfo> media) {
        this.media = media;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public GuidInfo getGuid() {
        return guid;
    }

    public void setGuid(GuidInfo guid) {
        this.guid = guid;
    }

    public CategoryInfo getCategory() {
        return category;
    }

    public void setCategory(CategoryInfo category) {
        this.category = category;
    }

    public EnclosureInfo getEnclosure() {
        return enclosure;
    }

    public void setEnclosure(EnclosureInfo enclosure) {
        this.enclosure = enclosure;
    }

    public List<Autorinfo> getAutor() {
        return autor;
    }

    public void setAutor(List<Autorinfo> autor) {
        this.autor = autor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
