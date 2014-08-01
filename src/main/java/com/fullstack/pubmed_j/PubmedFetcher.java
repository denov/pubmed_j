package com.fullstack.pubmed_j;

import com.fullstack.pubmed_j.model.PubMedDoc;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;


public class PubmedFetcher {


    private static final Logger log = LoggerFactory.getLogger(PubmedFetcher.class);


    public static PubMedDoc fetchAndParse(String PMID) {
        try {
            return PubMedParser.parse(fetch(PMID));

        } catch (ParserConfigurationException | IOException | SAXException | XPathExpressionException e) {
            log.error("Trouble parsing PubMed ID: {}", PMID, e);
            throw new RuntimeException(e);
        }
    }


    public static String fetch(String pubmedId) {
        String pubMedDoc;
        CloseableHttpClient client = null;

        try {
            client = HttpClientBuilder.create().build();
            HttpGet getRequest = new HttpGet("http://www.ncbi.nlm.nih.gov/pubmed/" + pubmedId + "?report=xml&format=text");
            HttpResponse response = client.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                log.error("Can not look up {} in PubMed", pubmedId);
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
            }

            pubMedDoc = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
            client.close();

        } catch (IOException e) {
            log.error("Trouble downloading PubMed ID: " + pubmedId, e);
            throw new RuntimeException(e);
        } finally {
            try {
                client.close();
            } catch (IOException ignore) {}
        }

        return pubMedDoc;
    }

}
