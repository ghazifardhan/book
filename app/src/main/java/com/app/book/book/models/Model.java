package com.app.book.book.models;

/**
 * Created by Ghazi on 08/01/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Model {

    @SerializedName("listbuku")
    @Expose
    private List<Listbuku> listbuku = new ArrayList<Listbuku>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    // Getter and Setter

    public List<Listbuku> getListbuku() {
        return listbuku;
    }

    public void setListbuku(List<Listbuku> listbuku) {
        this.listbuku = listbuku;
    }

    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
