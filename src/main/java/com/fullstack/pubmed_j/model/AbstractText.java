package com.fullstack.pubmed_j.model;

public class AbstractText {

    private String label;
    private String nlmCategory;
    private String text;


    public AbstractText(String label, String nlmCategory, String text) {
        this.label = label;
        this.nlmCategory = nlmCategory;
        this.text = text;
    }


    public String getLabel() {
        return label;
    }


    public String getNlmCategory() {
        return nlmCategory;
    }


    public String getText() {
        return text;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractText that = (AbstractText) o;

        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (nlmCategory != null ? !nlmCategory.equals(that.nlmCategory) : that.nlmCategory != null) return false;
        return !(text != null ? !text.equals(that.text) : that.text != null);

    }


    @Override
    public int hashCode() {
        int result = label != null ? label.hashCode() : 0;
        result = 31 * result + (nlmCategory != null ? nlmCategory.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}

