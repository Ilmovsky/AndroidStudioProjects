package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

/**
 * Created by Lexa on 27.04.2016.
 */
public class LinkInfo {

    @Attribute(name = "href",required=false)
    private String href;
    @Attribute(name = "rel",required=false)
    private String rel;
    @Attribute(name = "type",required=false)
    private String type;
    @Text(required=false)
    public String link;

    public LinkInfo() {
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRel() {
        return rel;
    }

    public void setRel(String rel) {
        this.rel = rel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
