package com.fullstack.pubmed_j.model;

import org.joda.time.LocalDate;


public class PubDate {

    private String day;
    private String month;
    private String year;
    private String MedlineDate;


    public PubDate(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }


    public PubDate(String medlineDate) {
        MedlineDate = medlineDate;
    }


    public LocalDate toLocalDate() {
        return new LocalDate(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    }


    public String getDay() {
        return day;
    }


    public String getMonth() {
        return month;
    }


    public String getYear() {
        return year;
    }


    public String getMedlineDate() {
        return MedlineDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PubDate pubDate = (PubDate) o;

        if (MedlineDate != null ? !MedlineDate.equals(pubDate.MedlineDate) : pubDate.MedlineDate != null) return false;
        if (day != null ? !day.equals(pubDate.day) : pubDate.day != null) return false;
        if (month != null ? !month.equals(pubDate.month) : pubDate.month != null) return false;
        return !(year != null ? !year.equals(pubDate.year) : pubDate.year != null);
    }


    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (MedlineDate != null ? MedlineDate.hashCode() : 0);
        return result;
    }
}
