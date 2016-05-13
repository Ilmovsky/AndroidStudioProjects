package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Text;

/**
 * Created by Lexa on 27.04.2016.
 */
public class CategoryInfo {

    @Attribute(name = "domain")
    private String domain;
    @Text(required=false)
    public String category;

    public CategoryInfo() {
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

}
