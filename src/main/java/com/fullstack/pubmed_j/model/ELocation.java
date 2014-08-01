package com.fullstack.pubmed_j.model;

public class ELocation {

    private String type;
    private boolean valid;
    private String value;


    public ELocation(String type, boolean valid, String value) {
        this.type = type;
        this.valid = valid;
        this.value = value;
    }


    public String getType() {
        return type;
    }


    public boolean isValid() {
        return valid;
    }


    public String getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ELocation eLocation = (ELocation) o;

        if (valid != eLocation.valid) return false;
        if (type != null ? !type.equals(eLocation.type) : eLocation.type != null) return false;
        return !(value != null ? !value.equals(eLocation.value) : eLocation.value != null);
    }


    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (valid ? 1 : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
