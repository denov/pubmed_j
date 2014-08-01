package com.fullstack.pubmed_j.model;

import java.util.ArrayList;
import java.util.List;


public class PubmedData {

    private String publicationStatus;
    private List<PubMedPubDate> history = new ArrayList<>();
    private List<ArticleId> articleIdList = new ArrayList<>();



    public List<PubMedPubDate> getHistory() {
        return history;
    }


    public void addHistory(PubMedPubDate history) {
        this.history.add(history);
    }


    public String getPublicationStatus() {
        return publicationStatus;
    }


    public void setPublicationStatus(String publicationStatus) {
        this.publicationStatus = publicationStatus;
    }


    public List<ArticleId> getArticleIdList() {
        return articleIdList;
    }


    public void addArticleIdList(ArticleId articleId) {
        this.articleIdList.add(articleId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PubmedData that = (PubmedData) o;

        if (articleIdList != null ? !articleIdList.equals(that.articleIdList) : that.articleIdList != null) return false;
        if (history != null ? !history.equals(that.history) : that.history != null) return false;
        return !(publicationStatus != null ? !publicationStatus.equals(that.publicationStatus) : that.publicationStatus != null);
    }


    @Override
    public int hashCode() {
        int result = history != null ? history.hashCode() : 0;
        result = 31 * result + (publicationStatus != null ? publicationStatus.hashCode() : 0);
        result = 31 * result + (articleIdList != null ? articleIdList.hashCode() : 0);
        return result;
    }
}
