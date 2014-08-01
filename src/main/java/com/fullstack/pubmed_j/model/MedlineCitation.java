package com.fullstack.pubmed_j.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;


public class MedlineCitation {

    private String owner;
    private String status;
    private String PMID;
    private String PMIDVersion;
    private LocalDate dateCreated;
    private LocalDate dateCompleted;
    private LocalDate dateRevised;
    private Article article;
    private MedlineJournalInfo medlineJournalInfo;
    private List<Chemical> chemicalList = new ArrayList<>();
    private List<String> citationSubset = new ArrayList<>();
    private List<CommentsCorrections> commentsCorrectionsList = new ArrayList<>();
    private List<MeshHeading> meshHeadingList = new ArrayList<>();
    private String otherIdSource;
    private String otherId;


    public String getOwner() {
        return owner;
    }


    public void setOwner(String owner) {
        this.owner = owner;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getPMID() {
        return PMID;
    }


    public void setPMID(String PMID) {
        this.PMID = PMID;
    }


    public String getPMIDVersion() {
        return PMIDVersion;
    }


    public void setPMIDVersion(String PMIDVersion) {
        this.PMIDVersion = PMIDVersion;
    }


    public LocalDate getDateCreated() {
        return dateCreated;
    }



    public void setDateCreated(String day, String month, String year) {
        this.dateCreated = new LocalDate(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    }


    public LocalDate getDateCompleted() {
        return dateCompleted;
    }


    public void setDateCompleted(String day, String month, String year) {
        this.dateCompleted = new LocalDate(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    }


    public LocalDate getDateRevised() {
        return dateRevised;
    }


    public void setDateRevised(String day, String month, String year) {
        this.dateRevised = new LocalDate(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
    }


    public Article getArticle() {
        return article;
    }


    public void setArticle(Article article) {
        this.article = article;
    }


    public MedlineJournalInfo getMedlineJournalInfo() {
        return medlineJournalInfo;
    }


    public void setMedlineJournalInfo(MedlineJournalInfo medlineJournalInfo) {
        this.medlineJournalInfo = medlineJournalInfo;
    }


    public List<Chemical> getChemicalList() {
        return chemicalList;
    }


    public void addChemical(Chemical chemical) {
        this.chemicalList.add(chemical);
    }


    public List<String> getCitationSubset() {
        return citationSubset;
    }


    public void addCitation(String citation) {
        this.citationSubset.add(citation);
    }


    public List<CommentsCorrections> getCommentsCorrectionsList() {
        return commentsCorrectionsList;
    }


    public void addCommentsCorrection(CommentsCorrections commentsCorrections) {
        this.commentsCorrectionsList.add(commentsCorrections);
    }


    public List<MeshHeading> getMeshHeadingList() {
        return meshHeadingList;
    }


    public void addMeshHeading(MeshHeading meshHeading) {
        this.meshHeadingList.add(meshHeading);
    }


    public String getOtherIdSource() {
        return otherIdSource;
    }


    public void setOtherIdSource(String otherIdSource) {
        this.otherIdSource = otherIdSource;
    }


    public String getOtherId() {
        return otherId;
    }


    public void setOtherId(String otherId) {
        this.otherId = otherId;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("owner", owner)
                .append("status", status)
                .append("PMID", PMID)
                .append("PMIDVersion", PMIDVersion)
                .append("dateCreated", dateCreated)
                .append("dateCompleted", dateCompleted)
                .append("dateRevised", dateRevised)
                .append("article", article)
                .append("medlineJournalInfo", medlineJournalInfo)
                .append("chemicalList", chemicalList)
                .append("citationSubset", citationSubset)
                .append("commentsCorrectionsList", commentsCorrectionsList)
                .append("meshHeadingList", meshHeadingList)
                .append("otherIdSource", otherIdSource)
                .append("otherId", otherId)
                .toString();
    }
}
