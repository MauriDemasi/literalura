package com.example.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseContainer {
    private int count;
    @JsonAlias ("results") List<DatoBook> results;


    public List<DatoBook> getResults() {
        return results;
    }
}

