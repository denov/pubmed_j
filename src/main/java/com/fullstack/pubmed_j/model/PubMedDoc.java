/*
 * Copyright 2013, devbliss GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.fullstack.pubmed_j.model;


/*
http://www.nlm.nih.gov/bsd/licensee/elements_descriptions.html
*/

public class PubMedDoc {

    private MedlineCitation medlineCitation;
    private PubmedData pubmedData;


    public PubMedDoc(final MedlineCitation medlineCitation, final PubmedData pubmedData) {
        this.medlineCitation = medlineCitation;
        this.pubmedData = pubmedData;
    }


    public MedlineCitation getMedlineCitation() {
        return medlineCitation;
    }


    public PubmedData getPubmedData() {
        return pubmedData;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PubMedDoc pubMedDoc = (PubMedDoc) o;

        if (medlineCitation != null ? !medlineCitation.equals(pubMedDoc.medlineCitation) : pubMedDoc.medlineCitation != null) return false;
        return !(pubmedData != null ? !pubmedData.equals(pubMedDoc.pubmedData) : pubMedDoc.pubmedData != null);
    }


    @Override
    public int hashCode() {
        int result = medlineCitation != null ? medlineCitation.hashCode() : 0;
        result = 31 * result + (pubmedData != null ? pubmedData.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return new org.apache.commons.lang.builder.ToStringBuilder(this)
                .append("medlineCitation", medlineCitation)
                .append("pubmedData", pubmedData)
                .toString();
    }

}

