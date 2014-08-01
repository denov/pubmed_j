package com.fullstack.pubmed_j.model;

import org.apache.commons.lang.builder.ToStringBuilder;


public class Author {

    private String firstName;
    private String lastName;
    private String initials;
    private String affiliation;
    private String identifier;
    private boolean isValid;


    public Author(String firstName, String lastName, String initials, String identifier, String affiliation, boolean isValid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.initials = initials;
        this.affiliation = affiliation;
        this.identifier = identifier;
        this.isValid = isValid;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public String getInitials() {
        return initials;
    }


    public String getAffiliation() {
        return affiliation;
    }


    public String getIdentifier() {
        return identifier;
    }


    public boolean isValid() {
        return isValid;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (affiliation != null ? !affiliation.equals(author.affiliation) : author.affiliation != null) return false;
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName != null) return false;
        if (initials != null ? !initials.equals(author.initials) : author.initials != null) return false;
        return !(lastName != null ? !lastName.equals(author.lastName) : author.lastName != null);
    }


    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (initials != null ? initials.hashCode() : 0);
        result = 31 * result + (affiliation != null ? affiliation.hashCode() : 0);
        result = 31 * result + (identifier != null ? identifier.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("initials", initials)
                .append("affiliation", affiliation)
                .append("identifier", identifier)
                .append("isValid", isValid)
                .toString();
    }
}

