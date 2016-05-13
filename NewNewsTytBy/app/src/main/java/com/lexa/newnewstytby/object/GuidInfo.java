package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Text;

/**
 * Created by Lexa on 27.04.2016.
 */
public class GuidInfo {

    @Attribute(name = "isPermaLink")
    private String isPermaLink;
    @Text(required=false)
    public String guid;

    public GuidInfo() {
    }

    public String getIsPermaLink() {
        return isPermaLink;
    }

    public void setIsPermaLink(String isPermaLink) {
        this.isPermaLink = isPermaLink;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

}
