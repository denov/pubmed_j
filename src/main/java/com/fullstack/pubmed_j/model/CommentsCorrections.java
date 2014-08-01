package com.fullstack.pubmed_j.model;

public class CommentsCorrections {

    private String refType;
    private String refSource;
    private String PMID;
    private String note;


    public CommentsCorrections(String refType, String refSource, String PMID, String note) {
        this.refType = refType;
        this.refSource = refSource;
        this.PMID = PMID;
        this.note = note;
    }


    public String getRefType() {
        return refType;
    }


    public String getRefSource() {
        return refSource;
    }


    public String getPMID() {
        return PMID;
    }


    public String getNote() {
        return note;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CommentsCorrections that = (CommentsCorrections) o;

        if (PMID != null ? !PMID.equals(that.PMID) : that.PMID != null) return false;
        if (refSource != null ? !refSource.equals(that.refSource) : that.refSource != null) return false;
        return !(refType != null ? !refType.equals(that.refType) : that.refType != null);
    }


    @Override
    public int hashCode() {
        int result = refType != null ? refType.hashCode() : 0;
        result = 31 * result + (refSource != null ? refSource.hashCode() : 0);
        result = 31 * result + (PMID != null ? PMID.hashCode() : 0);
        return result;
    }
}
