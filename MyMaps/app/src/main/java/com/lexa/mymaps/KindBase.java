package com.lexa.mymaps;

/**
 * Created by Lexa on 17.02.2016.
 */
public class KindBase {
    private long id;
    private String kind;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof KindBase)) return false;

        KindBase kindBase = (KindBase) o;

        if (getId() != kindBase.getId()) return false;
        return getKind().equals(kindBase.getKind());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getKind().hashCode();
        return result;
    }
}
