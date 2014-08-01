package com.fullstack.pubmed_j.model;

public class Chemical {

    private String registryNumber;
    private String substanceName;


    public Chemical(String registryNumber, String substanceName) {
        this.registryNumber = registryNumber;
        this.substanceName = substanceName;
    }


    public String getRegistryNumber() {
        return registryNumber;
    }


    public String getSubstanceName() {
        return substanceName;
    }

}
