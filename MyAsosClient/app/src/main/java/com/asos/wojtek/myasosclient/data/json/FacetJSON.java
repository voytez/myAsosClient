package com.asos.wojtek.myasosclient.data.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacetJSON {

    private List<FacetValueJSON> FacetValues = new ArrayList<FacetValueJSON>();
    private String Id;
    private String Name;
    private Integer Sequence;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return
     * The FacetValues
     */
    public List<FacetValueJSON> getFacetValues() {
        return FacetValues;
    }

    /**
     *
     * @param FacetValues
     * The FacetValues
     */
    public void setFacetValues(List<FacetValueJSON> FacetValues) {
        this.FacetValues = FacetValues;
    }

    /**
     *
     * @return
     * The Id
     */
    public String getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The Id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The Name
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The Sequence
     */
    public Integer getSequence() {
        return Sequence;
    }

    /**
     *
     * @param Sequence
     * The Sequence
     */
    public void setSequence(Integer Sequence) {
        this.Sequence = Sequence;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}