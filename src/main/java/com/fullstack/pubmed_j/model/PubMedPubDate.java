package com.fullstack.pubmed_j.model;

public class PubMedPubDate {

    private String day;
    private String month;
    private String year;
    private String minute;
    private String hour;
    private String pubStatus;


    public PubMedPubDate(String day, String month, String year, String hour, String minute, String pubStatus) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.minute = minute;
        this.hour = hour;
        this.pubStatus = pubStatus;
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


    public String getMinute() {
        return minute;
    }


    public String getHour() {
        return hour;
    }


    public String getPubStatus() {
        return pubStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PubMedPubDate)) return false;

        PubMedPubDate that = (PubMedPubDate) o;

        if (day != null ? !day.equals(that.day) : that.day != null) return false;
        if (hour != null ? !hour.equals(that.hour) : that.hour != null) return false;
        if (minute != null ? !minute.equals(that.minute) : that.minute != null) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        if (pubStatus != null ? !pubStatus.equals(that.pubStatus) : that.pubStatus != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        int result = day != null ? day.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (minute != null ? minute.hashCode() : 0);
        result = 31 * result + (hour != null ? hour.hashCode() : 0);
        result = 31 * result + (pubStatus != null ? pubStatus.hashCode() : 0);
        return result;
    }
}
