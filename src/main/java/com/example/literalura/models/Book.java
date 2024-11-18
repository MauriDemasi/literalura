package com.example.literalura.models;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "books")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany (fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Personas> autores;

    private List <String> idiomas;

    private double numeroDescargas;

    public List<String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<String> idiomas) {
        this.idiomas = idiomas;
    }

    public double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    public Book() {
    }

    public Book(DatoBook datoBook) {
        this.titulo = datoBook.title();
        this.autores = datoBook.authors().stream()
                .map(DatoAuthor::toPerson)
                .toList();
        this.idiomas = datoBook.languages();
        this.numeroDescargas = datoBook.downloadCount();

    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Personas> getAutores() {
        return autores;
    }

    public void setAutores(List<Personas> autores) {
        this.autores = autores;
    }

    public void addAutor(Personas autor) {
        this.autores.add(autor);
    }

}

