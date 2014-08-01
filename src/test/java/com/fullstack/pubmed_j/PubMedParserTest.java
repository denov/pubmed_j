package com.fullstack.pubmed_j;

import com.fullstack.pubmed_j.model.*;
import org.joda.time.LocalDate;
import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class PubMedParserTest {

    public final String TEST_DATA_DIRECTORY = "src/test/resources/";


    @Test
    public void parseTestFile() throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(TEST_DATA_DIRECTORY + "pubmed/pubmeb_sample.xml"));
        String xml = StandardCharsets.UTF_8.decode(ByteBuffer.wrap(encoded)).toString();

        PubMedDoc doc = PubMedParser.parse(xml);
        assertTrue(doc.getMedlineCitation().getArticle().getJournal().getTitle().startsWith("Medical image computing and computer-assisted intervention"));
        assertTrue(doc.getMedlineCitation().getArticle().getArticleTitle().startsWith("Adaptive voxel, texture and temporal conditional random fields for detection of Gad-enhancing"));
        assertEquals("eng", doc.getMedlineCitation().getArticle().getLanguage());
        assertEquals("24505804", doc.getMedlineCitation().getPMID());
    }


    @Test
    public void randomSampleImportPubMed() {
        // this test is just a randomly sampling to find corner cases. mostly it just
        // finds IDs for there is no document.

        List badPubmedIdList = new ArrayList<Integer>(){{ add(16057878); add(19797348); add(12788911); }};

        int min = 10000000;
        int max = 22000000;
        int seedId = min + (int)(Math.random() * ((max - min) + 1));
        for(int i=1; i < 50; i++) {
            if(badPubmedIdList.contains(seedId+i)) continue;  // no doc for this ID

            String pubmedId = Integer.toString(seedId + i);
            PubMedDoc doc = PubmedFetcher.fetchAndParse(pubmedId);
            assertEquals(pubmedId, doc.getMedlineCitation().getPMID());
            assertNotNull(doc.getMedlineCitation().getArticle().getArticleTitle());

            System.out.println("Pubmed ID " + pubmedId + " is OK! -- " + doc.getMedlineCitation().getArticle().getArticleTitle());
        }
    }


    @Test
    public void testImportPubMed() {
        PubMedDoc doc = PubmedFetcher.fetchAndParse("22056550");  // http://www.ncbi.nlm.nih.gov/pubmed/22056550?report=xml&format=text

        assertEquals("22056550", doc.getMedlineCitation().getPMID());
        assertEquals("MEDLINE", doc.getMedlineCitation().getStatus());
        assertEquals("NLM", doc.getMedlineCitation().getOwner());
        assertEquals(new LocalDate(2013, 1, 14), doc.getMedlineCitation().getDateCreated());
        assertEquals(new LocalDate(2013, 6, 24), doc.getMedlineCitation().getDateCompleted());

        Article article = doc.getMedlineCitation().getArticle();
        assertEquals("0736-4679", article.getJournal().getISSN());
        assertEquals("Print", article.getJournal().getISSNType());
        assertEquals("Internet", article.getJournal().getCitedMedium());
        assertEquals("44", article.getJournal().getVolume());
        assertEquals("1", article.getJournal().getIssue());
        assertEquals(new PubDate("","Jan","2013"), article.getJournal().getPubDate());
        assertEquals("The Journal of emergency medicine", article.getJournal().getTitle());
        assertEquals("J Emerg Med", article.getJournal().getIsoAbreviation());

        assertEquals("Dislocation of the knee: an epidemic in waiting?", article.getArticleTitle());
        assertEquals("eng", article.getLanguage());
        assertTrue(article.getPagination().contains("68-71"));

        assertTrue("Should have 2", article.geteLocations().size() == 2);
        assertTrue(article.geteLocations().contains(new ELocation("doi", true, "10.1016/j.jemermed.2011.06.064")));
        assertTrue(article.geteLocations().contains(new ELocation("pii", true, "S0736-4679(11)00727-X")));

        assertTrue("Should have 4", article.getAbstractTexts().size() == 4);
        assertTrue(article.getAbstractTexts().contains(new AbstractText("BACKGROUND","BACKGROUND","Knee dislocation is an uncommon but serious injury that has traditionally been associated with high velocity injuries such as motor vehicle accidents. More recently, individual cases of obese individuals sustaining knee dislocation from a low velocity mechanism have been noted. Associated injuries of knee dislocation are common and include popliteal vessel damage requiring surgical repair and injuries to the peroneal nerve. Prompt diagnosis and reduction is essential to reduce the risk of these complications.")));
        assertTrue(article.getAbstractTexts().contains(new AbstractText("OBJECTIVES","OBJECTIVE","We aim to highlight the importance of prompt diagnosis and management of obese patients presenting with knee pain after a seemingly innocuous injury who may have a knee dislocation.")));
        assertTrue(article.getAbstractTexts().contains(new AbstractText("CASES","METHODS","We present a series of four cases of dislocation of the knee that have presented to our hospital over the course of 1 year. Each was sustained by a morbidly obese female of body mass index range 35-41, age range 33-52 years, experiencing a simple mechanical fall from standing. Magnetic resonance image scanning revealed multiple knee ligament rupture in all four cases. One case had peroneal nerve palsy.")));
        assertTrue(article.getAbstractTexts().contains(new AbstractText("CONCLUSION","CONCLUSIONS","This is the first series of such injuries that we are aware of and highlights a potential future increase in incidence of these major injuries as body mass in society increases, placing more strain on health care resources. Practitioners in the Emergency Department need to be aware that serious injury can be present in morbidly obese patients that have sustained no more than a fall from standing height. Prompt investigation and management is essential.")));

        assertTrue("Should have 3", article.getAuthorList().size() == 3);
        assertTrue(article.getAuthorList().contains(new Author("Gray A D", "Edwards", "GA", "", "Department of Trauma and Orthopaedics, Morriston Hospital, Heol Maes Eglwys, Swansea, United Kingdom.", true)));
        assertTrue(article.getAuthorList().contains(new Author("Steven M", "Sarasin", "SM", "", "", true)));
        assertTrue(article.getAuthorList().contains(new Author("Andrew P", "Davies", "AP", "", "", true)));
        assertTrue(article.isAuthorListComplete());

        assertEquals("Crown Copyright © 2013. Published by Elsevier Inc. All rights reserved.", article.getCopyrightInformation());
        assertTrue(article.getPublicationTypes().size() == 2);
        assertTrue(article.getPublicationTypes().contains("Case Reports"));
        assertTrue(article.getPublicationTypes().contains("Journal Article"));

        assertEquals("Electronic", article.getArticleDateType());
        assertEquals(new LocalDate(2011, 11, 6), article.getArticleDate());


        MedlineJournalInfo medlineJournalInfo = doc.getMedlineCitation().getMedlineJournalInfo();
        assertEquals("United States", medlineJournalInfo.getCountry());
        assertEquals("J Emerg Med", medlineJournalInfo.getMedlineTA());
        assertEquals("8412174", medlineJournalInfo.getNlmUniqueId());
        assertEquals("0736-4679", medlineJournalInfo.getIssnLinking());
        assertEquals("ppublish", doc.getPubmedData().getPublicationStatus());

        assertTrue(doc.getPubmedData().getHistory().size() == 7);
        assertTrue(doc.getPubmedData().getHistory().contains(new PubMedPubDate("9","2","2011","","","received")));
        assertTrue(doc.getPubmedData().getHistory().contains(new PubMedPubDate("26","6","2013","6","0","medline")));

        assertTrue(doc.getPubmedData().getArticleIdList().size() == 3);
        assertTrue(doc.getPubmedData().getArticleIdList().contains(new ArticleId("pii", "S0736-4679(11)00727-X")));
        assertTrue(doc.getPubmedData().getArticleIdList().contains(new ArticleId("doi", "10.1016/j.jemermed.2011.06.064")));
        assertTrue(doc.getPubmedData().getArticleIdList().contains(new ArticleId("pubmed", "22056550")));

        assertTrue(doc.getMedlineCitation().getCitationSubset().contains("IM"));

        assertTrue(doc.getMedlineCitation().getCommentsCorrectionsList().size() == 0);
        assertTrue(doc.getMedlineCitation().getChemicalList().size() == 0);

        assertTrue(doc.getMedlineCitation().getMeshHeadingList().size() == 8);
        // todo - finish up checking mesh headings.

    }


    @Test
    public void testImportPubMedWithGrantList() {
        PubMedDoc doc = PubmedFetcher.fetchAndParse("11786451");  // http://www.ncbi.nlm.nih.gov/pubmed/11786451?report=xml&format=text
        assertTrue(doc.getMedlineCitation().getArticle().getGrantList().size() == 2);
        assertTrue(doc.getMedlineCitation().getArticle().getGrantList().contains(new Grant("10293","Cancer Research UK","United Kingdom")));
        assertTrue(doc.getMedlineCitation().getArticle().getGrantList().contains(new Grant("MC_U137686849","Medical Research Council","United Kingdom")));
    }


    @Test
    public void testImportPubMedSamplesFromLinn() {
        PubMedDoc doc = PubmedFetcher.fetchAndParse("24520345");  // http://www.ncbi.nlm.nih.gov/pubmed/24520345?report=xml&format=text
        assertTrue(doc.getMedlineCitation().getArticle().getGrantList().size() == 2);
        assertEquals(new PubDate("", "", "2014"), doc.getMedlineCitation().getArticle().getJournal().getPubDate());
    }



}
