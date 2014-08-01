package com.fullstack.pubmed_j;

import com.fullstack.pubmed_j.model.*;
import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.io.StringReader;import java.lang.Exception;import java.lang.Integer;import java.lang.String;


public class PubMedParser {


    public static PubMedDoc parse(String xmlDoc) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        // pubMed XML is wack
        xmlDoc = StringEscapeUtils.unescapeHtml(xmlDoc);
        xmlDoc = xmlDoc.replace("<pre>", "").replace("</pre>","");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(false);
        documentBuilderFactory.setValidating(false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/namespaces", false);
        documentBuilderFactory.setFeature("http://xml.org/sax/features/validation", false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
        documentBuilderFactory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        Document document = documentBuilderFactory.newDocumentBuilder().parse(new InputSource(new StringReader(xmlDoc)));

        return parse(document);
    }


    public static PubMedDoc parse(Document xmlDoc) throws XPathExpressionException {
        return new PubMedDoc(getMedlineCitation(xmlDoc), getPubmedData(xmlDoc));
    }


    private static MedlineCitation getMedlineCitation(Document xmlDoc) {
        MedlineCitation medlineCitation = new MedlineCitation();

        medlineCitation.setOwner(getAttributeValue(xmlDoc, "MedlineCitation", "Owner"));
        medlineCitation.setStatus(getAttributeValue(xmlDoc, "MedlineCitation", "Status"));
        medlineCitation.setPMID(getElementValue(xmlDoc, "PMID"));

        NodeList dateCreatedNode = xmlDoc.getElementsByTagName("DateCreated");
        if(dateCreatedNode != null && dateCreatedNode.getLength() > 0) {
            for (int i = 0; i < dateCreatedNode.getLength(); i++) {
                Node node =  dateCreatedNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String year = e.getElementsByTagName("Year").item(0).getChildNodes().item(0).getNodeValue();
                    String month =  e.getElementsByTagName("Month").item(0).getChildNodes().item(0).getNodeValue();
                    String day = e.getElementsByTagName("Day").item(0).getChildNodes().item(0).getNodeValue();
                    medlineCitation.setDateCreated(day, month, year);
                }
            }
        }

        NodeList dateCompletedNode = xmlDoc.getElementsByTagName("DateCompleted");
        if(dateCompletedNode != null && dateCompletedNode.getLength() > 0) {
            for (int i = 0; i < dateCompletedNode.getLength(); i++) {
                Node node =  dateCompletedNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String year = e.getElementsByTagName("Year").item(0).getChildNodes().item(0).getNodeValue();
                    String month =  e.getElementsByTagName("Month").item(0).getChildNodes().item(0).getNodeValue();
                    String day = e.getElementsByTagName("Day").item(0).getChildNodes().item(0).getNodeValue();
                    medlineCitation.setDateCompleted(day, month, year);
                }
            }
        }

        medlineCitation.setArticle(getArticle(xmlDoc));
        medlineCitation.setMedlineJournalInfo(getMedlineJournalInfo(xmlDoc));

        NodeList citationSubset = xmlDoc.getElementsByTagName("CitationSubset");
        if(citationSubset != null && citationSubset.getLength() > 0) {
            for (int i = 0; i < citationSubset.getLength(); i++) {
                Node nodeCitation =  citationSubset.item(i);
                if (nodeCitation.getNodeType() == Node.ELEMENT_NODE) {
                    medlineCitation.addCitation(nodeCitation.getFirstChild().getNodeValue());
                }
            }
        }

        NodeList meshHeadings = xmlDoc.getElementsByTagName("MeshHeading");
        if(meshHeadings != null && meshHeadings.getLength() > 0) {
            for (int i = 0; i < meshHeadings.getLength(); i++) {
                Node nodeHeading =  meshHeadings.item(i);
                if (nodeHeading.getNodeType() == Node.ELEMENT_NODE) {
                    MeshHeading heading = new MeshHeading(getNodeValue(nodeHeading), getAttribute(nodeHeading, "MajorTopicYN").contains("Y"));
                    // heading.addQualifier();  // todo - finish
                    medlineCitation.addMeshHeading(heading);
                }
            }
        }

        NodeList chemicalList = xmlDoc.getElementsByTagName("Chemical");
        if(chemicalList != null && chemicalList.getLength() > 0) {
            for (int i = 0; i < chemicalList.getLength(); i++) {
                Node nodeChemical =  chemicalList.item(i);
                if (nodeChemical.getNodeType() == Node.ELEMENT_NODE) {
                    medlineCitation.addChemical(new Chemical(getElementValue(nodeChemical, "RegistryNumber"), getElementValue(nodeChemical, "NameOfSubstance")));
                }
            }
        }

        NodeList commentsCorrectionsList = xmlDoc.getElementsByTagName("CommentsCorrectionsList");
        //medlineCitation.addCommentsCorrection();


        return medlineCitation;
    }


    private static Article getArticle(Document xmlDoc) {
        Article article = new Article();

        article.setJournal(getJournal(xmlDoc));
        article.setArticleTitle(getElementValue(xmlDoc, "ArticleTitle"));
        article.addPagination(getElementValue(xmlDoc, "MedlinePgn"));

        NodeList eLocationIdNode = xmlDoc.getElementsByTagName("ELocationID");
        if(eLocationIdNode != null && eLocationIdNode.getLength() > 0) {
            for (int i = 0; i < eLocationIdNode.getLength(); i++) {
                Node node =  eLocationIdNode.item(i);
                article.addeLocation(new ELocation(getAttribute(node, "EIdType"), getAttribute(node, "ValidYN").equals("Y"), getNodeValue(node)));
            }
        }

        NodeList abstractList = xmlDoc.getElementsByTagName("AbstractText");
        if(abstractList != null && abstractList.getLength() > 0) {
            for (int i = 0; i < abstractList.getLength(); i++) {
                Node node =  abstractList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    article.addAbstractText(new AbstractText(getAttribute(node, "Label"), getAttribute(node, "NlmCategory"), getNodeValue(node)));
                }
            }
        }
        article.setCopyrightInformation(getElementValue(xmlDoc, "CopyrightInformation"));

        NodeList authorsNode = xmlDoc.getElementsByTagName("AuthorList").item(0).getChildNodes();
        article.setAuthorListComplete(getAttributeValue(xmlDoc, "AuthorList", "CompleteYN").contains("Y"));
        if(authorsNode != null && authorsNode.getLength() > 0) {
            for (int i = 0; i < authorsNode.getLength(); i++) {
                Node node =  authorsNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Author author = new Author(getElementValue(node, "ForeName"),
                                               getElementValue(node, "LastName"),
                                               getElementValue(node, "Initials"),
                                               getElementValue(node, "CollectiveName"),
                                               getElementValue(node, "Affiliation"),
                                               getAttribute(node, "ValidYN").equals("Y"));
                    article.addAuthor(author);
                }
            }
        }

        article.setLanguage(xmlDoc.getElementsByTagName("Language").item(0).getFirstChild().getNodeValue());

        if(xmlDoc.getElementsByTagName("GrantList").item(0) != null) {
            NodeList grantList = xmlDoc.getElementsByTagName("GrantList").item(0).getChildNodes();
            article.setGrantListComplete(getAttributeValue(xmlDoc, "GrantList", "CompleteYN").contains("Y"));
            if(grantList != null && grantList.getLength() > 0) {
                for (int i = 0; i < grantList.getLength(); i++) {
                    Node node =  grantList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        article.addGrant(new Grant(getElementValue(node, "GrantID"), getElementValue(node, "Agency"), getElementValue(node, "Country")));
                    }
                }
            }
        }

        NodeList publicationList = xmlDoc.getElementsByTagName("PublicationType");
        if(publicationList != null && publicationList.getLength() > 0) {
            for (int i = 0; i < publicationList.getLength(); i++) {
                Node node =  publicationList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    article.addPublicationType(getNodeValue(node));
                }
            }
        }

        NodeList dateCompletedNode = xmlDoc.getElementsByTagName("ArticleDate");
        if(dateCompletedNode != null && dateCompletedNode.getLength() > 0) {
            for (int i = 0; i < dateCompletedNode.getLength(); i++) {
                Node node =  dateCompletedNode.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) node;
                    String year = e.getElementsByTagName("Year").item(0).getChildNodes().item(0).getNodeValue();
                    String month =  e.getElementsByTagName("Month").item(0).getChildNodes().item(0).getNodeValue();
                    String day = e.getElementsByTagName("Day").item(0).getChildNodes().item(0).getNodeValue();
                    article.setArticleDate(Integer.valueOf(day), Integer.valueOf(month), Integer.valueOf(year));
                    article.setArticleDateType(getAttribute(node, "DateType"));
                }
            }
        }


        return article;
    }


    private static Journal getJournal(Document xmlDoc) {
        return new Journal(getElementValue(xmlDoc, "ISSN"),
                           getAttributeValue(xmlDoc, "ISSN", "IssnType"),
                           getAttributeValue(xmlDoc, "JournalIssue", "CitedMedium"),
                           getElementValue(xmlDoc, "Volume"),
                           getElementValue(xmlDoc, "Issue"),
                           getPubDate(xmlDoc),
                           getElementValue(xmlDoc, "Title"),
                           getElementValue(xmlDoc, "ISOAbbreviation"));
    }


    private static PubDate getPubDate(Document xmlDoc) {
        Node node = xmlDoc.getElementsByTagName("PubDate").item(0);
        if(getElementValue(node, "MedlineDate").equals("")) {
            return new PubDate(getElementValue(node, "Day"), getElementValue(node, "Month"), getElementValue(node, "Year"));
        } else {
            return new PubDate(getElementValue(node, "MedlineDate"));
        }
    }


    private static MedlineJournalInfo getMedlineJournalInfo(Document xmlDoc) {
        return new MedlineJournalInfo(getElementValue(xmlDoc, "Country"),
                                      getElementValue(xmlDoc, "MedlineTA"),
                                      getElementValue(xmlDoc, "NlmUniqueID"),
                                      getElementValue(xmlDoc, "ISSNLinking"));
    }


    private static PubmedData getPubmedData(Document xmlDoc) {
        PubmedData pubmedData = new PubmedData();

        NodeList publicationList = xmlDoc.getElementsByTagName("PubMedPubDate");
        if(publicationList != null && publicationList.getLength() > 0) {
            for (int i = 0; i < publicationList.getLength(); i++) {
                Node node =  publicationList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    String day = getElementValue(node, "Day");
                    String month = getElementValue(node, "Month");
                    String year = getElementValue(node, "Year");
                    String hour = getElementValue(node, "Hour");
                    String minute = getElementValue(node, "Minute");
                    String status = getAttribute(node, "PubStatus");
                    pubmedData.addHistory(new PubMedPubDate(day, month, year, hour, minute, status));
                }
            }
        }

        pubmedData.setPublicationStatus(getElementValue(xmlDoc, "PublicationStatus"));
        NodeList articleIdList = xmlDoc.getElementsByTagName("ArticleId");
        if(articleIdList != null && articleIdList.getLength() > 0) {
            for (int i = 0; i < articleIdList.getLength(); i++) {
                Node nodeArticle =  articleIdList.item(i);
                if (nodeArticle.getNodeType() == Node.ELEMENT_NODE) {
                    ArticleId articleId = new ArticleId(getAttribute(nodeArticle, "IdType"), getNodeValue(nodeArticle));
                    pubmedData.addArticleIdList(articleId);
                }
            }
        }

        return pubmedData;
    }


    private static String getElementValue(Document xmlDoc, String elementName) {
        try {
            return xmlDoc.getElementsByTagName(elementName).item(0).getFirstChild().getNodeValue();
        } catch (Exception e) {
            // should we log?
            return "";
        }
    }


    private static String getElementValue(Node e, String elementName) {
        try {
            return ((Element)e).getElementsByTagName(elementName).item(0).getFirstChild().getNodeValue();
        } catch (Exception ex) {
            // should we log?
            return "";
        }
    }


    private static String getNodeValue(Node node) {
        return ((DeferredElementImpl) node).item(0).getNodeValue();
    }


    private static String getAttribute(Node node, String attr) {
        return ((DeferredElementImpl) node).getAttribute(attr);
    }


    private static String getAttributeValue(Document xmlDoc, String elementName, String attributeName) {
        try {
            return xmlDoc.getElementsByTagName(elementName).item(0).getAttributes().getNamedItem(attributeName).getNodeValue();
        } catch (Exception ex) {
            // should we log?
            return "";
        }
    }

}
