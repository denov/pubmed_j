package com.fullstack.pubmed_j.model;

public class ArticleId {

    private String type;
    private String value;


    public ArticleId(String type, String value) {
        this.type = type;
        this.value = value;
    }


    public String getType() {
        return type;
    }


    public String getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArticleId articleId = (ArticleId) o;

        if (type != null ? !type.equals(articleId.type) : articleId.type != null) return false;
        return !(value != null ? !value.equals(articleId.value) : articleId.value != null);
    }


    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
