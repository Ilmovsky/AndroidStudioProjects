package com.lexa.newnewstytby.object;

import com.lexa.newnewstytby.object.ChanelInfo;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by Lexa on 27.04.2016.
 */

@Root(name="rss")
public class RssInfo {

    @Attribute(name = "version")
    private String version;

    @Element(name="channel")
    private ChanelInfo chanel;


    public RssInfo() {
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public ChanelInfo getChanel() {
        return chanel;
    }

    public void setChanel(ChanelInfo chanel) {
        this.chanel = chanel;
    }
}
