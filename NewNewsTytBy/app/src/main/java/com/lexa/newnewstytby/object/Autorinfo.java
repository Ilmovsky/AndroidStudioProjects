package com.lexa.newnewstytby.object;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lexa on 27.04.2016.
 */
public class Autorinfo {

    @ElementList(entry = "name",required=false, inline = true)
    private List<String> name  = new ArrayList<>();
    @ElementList(entry = "uri",required=false, inline = true)
    private List<String> uri  = new ArrayList<>();

    public Autorinfo() {
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getUri() {
        return uri;
    }

    public void setUri(List<String> uri) {
        this.uri = uri;
    }
}

