package com.fullstack.pubmed_j.model;

public class Grant {

    private String grantId;
    private String agency;
    private String country;


    public Grant(String grantId, String agency, String country) {
        this.grantId = grantId;
        this.agency = agency;
        this.country = country;
    }


    public String getGrantId() {
        return grantId;
    }


    public String getAgency() {
        return agency;
    }


    public String getCountry() {
        return country;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grant grant = (Grant) o;

        return !(grantId != null ? !grantId.equals(grant.grantId) : grant.grantId != null);
    }


    @Override
    public int hashCode() {
        return grantId != null ? grantId.hashCode() : 0;
    }

}
