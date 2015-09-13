package com.asos.wojtek.myasosclient.data.json;

import java.util.HashMap;
import java.util.Map;


public class FacetValueJSON {

    private Integer Count;
    private String Id;
    private String Name;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * @return The Count
     */
    public Integer getCount() {
        return Count;
    }

    /**
     * @param Count The Count
     */
    public void setCount(Integer Count) {
        this.Count = Count;
    }

    /**
     * @return The Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id The Id
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * @param Name The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}