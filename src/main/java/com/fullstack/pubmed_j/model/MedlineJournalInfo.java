package com.fullstack.pubmed_j.model;

public class MedlineJournalInfo {

    private String country;
    private String medlineTA;
    private String nlmUniqueId;
    private String issnLinking;


    public MedlineJournalInfo(String country, String medlineTA, String nlmUniqueId, String issnLinking) {
        this.country = country;
        this.medlineTA = medlineTA;
        this.nlmUniqueId = nlmUniqueId;
        this.issnLinking = issnLinking;
    }


    public String getCountry() {
        return country;
    }


    public String getMedlineTA() {
        return medlineTA;
    }


    public String getNlmUniqueId() {
        return nlmUniqueId;
    }


    public String getIssnLinking() {
        return issnLinking;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MedlineJournalInfo that = (MedlineJournalInfo) o;

        if (country != null ? !country.equals(that.country) : that.country != null) return false;
        if (issnLinking != null ? !issnLinking.equals(that.issnLinking) : that.issnLinking != null) return false;
        if (medlineTA != null ? !medlineTA.equals(that.medlineTA) : that.medlineTA != null) return false;
        return !(nlmUniqueId != null ? !nlmUniqueId.equals(that.nlmUniqueId) : that.nlmUniqueId != null);
    }


    @Override
    public int hashCode() {
        int result = country != null ? country.hashCode() : 0;
        result = 31 * result + (medlineTA != null ? medlineTA.hashCode() : 0);
        result = 31 * result + (nlmUniqueId != null ? nlmUniqueId.hashCode() : 0);
        result = 31 * result + (issnLinking != null ? issnLinking.hashCode() : 0);
        return result;
    }
}
