package com.fullstack.pubmed_j.model;

import java.util.ArrayList;
import java.util.List;


public class MeshHeading {


    private String descriptorName;
    private Boolean isDescriptorNameMajorTopic;
    private List<Qualifier> qualifiers = new ArrayList<>();


    public MeshHeading(String descriptorName, Boolean isDescriptorNameMajorTopic) {
        this.descriptorName = descriptorName;
        this.isDescriptorNameMajorTopic = isDescriptorNameMajorTopic;
    }


    public String getDescriptorName() {
        return descriptorName;
    }


    public Boolean getIsDescriptorNameMajorTopic() {
        return isDescriptorNameMajorTopic;
    }


    public void addQualifier(Qualifier qualifier) {
        qualifiers.add(qualifier);
    }


    public List<Qualifier> getQualifiers() {
        return qualifiers;
    }


    public class Qualifier {
        private String qualifierName ;
        private Boolean isQualifierNameMajorTopic;

        public Qualifier(String qualifierName, Boolean isQualifierNameMajorTopic) {
            this.qualifierName = qualifierName;
            this.isQualifierNameMajorTopic = isQualifierNameMajorTopic;
        }


        public String getQualifierName() {
            return qualifierName;
        }


        public Boolean getIsQualifierNameMajorTopic() {
            return isQualifierNameMajorTopic;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MeshHeading that = (MeshHeading) o;

        if (descriptorName != null ? !descriptorName.equals(that.descriptorName) : that.descriptorName != null) return false;
        return !(isDescriptorNameMajorTopic != null ? !isDescriptorNameMajorTopic.equals(that.isDescriptorNameMajorTopic) : that.isDescriptorNameMajorTopic != null);
    }


    @Override
    public int hashCode() {
        int result = descriptorName != null ? descriptorName.hashCode() : 0;
        result = 31 * result + (isDescriptorNameMajorTopic != null ? isDescriptorNameMajorTopic.hashCode() : 0);
        return result;
    }
}
