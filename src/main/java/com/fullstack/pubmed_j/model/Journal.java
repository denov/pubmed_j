package com.fullstack.pubmed_j.model;

import org.apache.commons.lang.builder.ToStringBuilder;


public class Journal {

    private String ISSN;
    private String ISSNType;
    private String citedMedium;
    private String volume;
    private String issue;
    private PubDate pubDate;
    private String title;
    private String isoAbreviation;


    public Journal(String ISSN, String ISSNType, String citedMedium, String volume, String issue, PubDate pubDate, String title, String isoAbreviation) {
        this.ISSN = ISSN;
        this.ISSNType = ISSNType;
        this.citedMedium = citedMedium;
        this.volume = volume;
        this.issue = issue;
        this.pubDate = pubDate;
        this.title = title;
        this.isoAbreviation = isoAbreviation;
    }


    public String getISSN() {
        return ISSN;
    }


    public String getISSNType() {
        return ISSNType;
    }


    public String getCitedMedium() {
        return citedMedium;
    }


    public String getVolume() {
        return volume;
    }


    public String getIssue() {
        return issue;
    }


    public PubDate getPubDate() {
        return pubDate;
    }


    public String getTitle() {
        return title;
    }


    public String getIsoAbreviation() {
        return isoAbreviation;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journal journal = (Journal) o;

        if (ISSN != null ? !ISSN.equals(journal.ISSN) : journal.ISSN != null) return false;
        if (ISSNType != null ? !ISSNType.equals(journal.ISSNType) : journal.ISSNType != null) return false;
        if (citedMedium != null ? !citedMedium.equals(journal.citedMedium) : journal.citedMedium != null) return false;
        if (isoAbreviation != null ? !isoAbreviation.equals(journal.isoAbreviation) : journal.isoAbreviation != null) return false;
        if (issue != null ? !issue.equals(journal.issue) : journal.issue != null) return false;
        if (pubDate != null ? !pubDate.equals(journal.pubDate) : journal.pubDate != null) return false;
        if (title != null ? !title.equals(journal.title) : journal.title != null) return false;
        return !(volume != null ? !volume.equals(journal.volume) : journal.volume != null);
    }


    @Override
    public int hashCode() {
        int result = ISSN != null ? ISSN.hashCode() : 0;
        result = 31 * result + (ISSNType != null ? ISSNType.hashCode() : 0);
        result = 31 * result + (citedMedium != null ? citedMedium.hashCode() : 0);
        result = 31 * result + (volume != null ? volume.hashCode() : 0);
        result = 31 * result + (issue != null ? issue.hashCode() : 0);
        result = 31 * result + (pubDate != null ? pubDate.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isoAbreviation != null ? isoAbreviation.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("ISSN", ISSN)
                .append("ISSNType", ISSNType)
                .append("citedMedium", citedMedium)
                .append("volume", volume)
                .append("issue", issue)
                .append("pubDate", pubDate)
                .append("title", title)
                .append("isoAbreviation", isoAbreviation)
                .toString();
    }
}
