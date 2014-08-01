package com.fullstack.pubmed_j.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


public class Article {

    private String pubModel;
    private Journal journal;
    private String articleTitle;
    private List<String> pagination = new ArrayList<>();
    private List<ELocation> eLocation = new ArrayList<>();
    private List<AbstractText> abstractTexts = new ArrayList<>();
    private String copyrightInformation;
    private boolean isAuthorListComplete;
    private List<Author> authorList = new ArrayList<>();
    private String language;
    private boolean isGrantListComplete;
    private List<Grant> grantList = new ArrayList<>();
    private List<String> publicationTypes = new ArrayList<>();
    private LocalDate articleDate;
    private String articleDateType;


    public String getPubModel() {
        return pubModel;
    }


    public void setPubModel(String pubModel) {
        this.pubModel = pubModel;
    }


    public Journal getJournal() {
        return journal;
    }


    public void setJournal(Journal journal) {
        this.journal = journal;
    }


    public String getArticleTitle() {
        return articleTitle;
    }


    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }


    public List<String> getPagination() {
        return pagination;
    }


    public void addPagination(String pagination) {
        this.pagination.add(pagination);
    }


    public List<ELocation> geteLocations() {
        return eLocation;
    }


    public void addeLocation(ELocation eLocation) {
        this.eLocation.add(eLocation);
    }


    public List<AbstractText> getAbstractTexts() {
        return abstractTexts;
    }


    public void addAbstractText(AbstractText abstractTexts) {
        this.abstractTexts.add(abstractTexts);
    }


    public String getCopyrightInformation() {
        return copyrightInformation;
    }


    public void setCopyrightInformation(String copyrightInformation) {
        this.copyrightInformation = copyrightInformation;
    }


    public boolean isAuthorListComplete() {
        return isAuthorListComplete;
    }


    public void setAuthorListComplete(boolean isAuthorListComplete) {
        this.isAuthorListComplete = isAuthorListComplete;
    }


    public List<Author> getAuthorList() {
        return authorList;
    }


    public void addAuthor(Author author) {
        this.authorList.add(author);
    }


    public String getLanguage() {
        return language;
    }


    public void setLanguage(String language) {
        this.language = language;
    }


    public boolean isGrantListComplete() {
        return isGrantListComplete;
    }


    public void setGrantListComplete(boolean isGrantListComplete) {
        this.isGrantListComplete = isGrantListComplete;
    }


    public List<Grant> getGrantList() {
        return grantList;
    }


    public void addGrant(Grant grant) {
        this.grantList.add(grant);
    }


    public List<String> getPublicationTypes() {
        return publicationTypes;
    }


    public void addPublicationType(String publicationType) {
        this.publicationTypes.add(publicationType);
    }


    public String getArticleDateType() {
        return articleDateType;
    }


    public void setArticleDateType(String articleDateType) {
        this.articleDateType = articleDateType;
    }


    public LocalDate getArticleDate() {
        return articleDate;
    }


    public void setArticleDate(int day, int month, int year) {
        this.articleDate = new LocalDate(year, month, day);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;
        if (abstractTexts != null ? !abstractTexts.equals(article.abstractTexts) : article.abstractTexts != null) return false;
        if (articleTitle != null ? !articleTitle.equals(article.articleTitle) : article.articleTitle != null) return false;
        if (journal != null ? !journal.equals(article.journal) : article.journal != null) return false;
        return !(pubModel != null ? !pubModel.equals(article.pubModel) : article.pubModel != null);
    }


    @Override
    public int hashCode() {
        int result = pubModel != null ? pubModel.hashCode() : 0;
        result = 31 * result + (journal != null ? journal.hashCode() : 0);
        result = 31 * result + (articleTitle != null ? articleTitle.hashCode() : 0);
        result = 31 * result + (abstractTexts != null ? abstractTexts.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("pubModel", pubModel)
                .append("journal", journal)
                .append("articleTitle", articleTitle)
                .append("pagination", pagination)
                .append("eLocation", eLocation)
                .append("abstractTexts", abstractTexts)
                .append("copyrightInformation", copyrightInformation)
                .append("isAuthorListComplete", isAuthorListComplete)
                .append("authorList", authorList)
                .append("language", language)
                .append("isGrantListComplete", isGrantListComplete)
                .append("grantList", grantList)
                .append("publicationTypes", publicationTypes)
                .append("articleDate", articleDate)
                .append("articleDateType", articleDateType)
                .toString();
    }
}
