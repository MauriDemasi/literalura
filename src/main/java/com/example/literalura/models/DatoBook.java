package com.example.literalura.models;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoBook(
        @JsonAlias("title") String title,
        @JsonAlias("authors") List<DatoAuthor> authors,
        @JsonAlias("languages") List<String> languages,
        @JsonAlias("download_count") double downloadCount
) {


    @Override
    public String title() {
        return title;
    }

    @Override
    public List<DatoAuthor> authors() {
        return authors;
    }

    @Override
    public List<String> languages() {
        return languages;
    }

    @Override
    public double downloadCount() {
        return downloadCount;
    }

    @Override
    public String toString() {
        return "----- LIBRO -----\n" +
                "Titulo: " + title + "\n" +
                "Autor/es: " + authors.stream()
                .map(DatoAuthor::nombre)
                .collect(Collectors.joining(", ")) + "\n" +
                "Idioma: " + String.join(", ", languages) + "\n" +
                "Numero de descargas: " + downloadCount + "\n" +
                "-------------------";
    }


}

