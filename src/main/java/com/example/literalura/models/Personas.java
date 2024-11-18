package com.example.literalura.models;
import jakarta.persistence.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "autores")

public class Personas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date fechaNacimiento;
    private Date fechaFallecimiento;
    private String nombre;

    @ManyToMany(mappedBy = "autores",
           fetch = FetchType.EAGER)
    private List<Book> books;

    public Personas(String nombre, Date fechaNacimiento,Date fechaFallecimiento){
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public Personas() {

    }

    public DatoAuthor toDatoAuthor(){
       String birthDate = fechaNacimiento != null ? fechaNacimiento.toString() : "Desconocida";
       String deathDate = fechaFallecimiento != null ? fechaFallecimiento.toString() : "Desconocida";

       return new DatoAuthor(nombre, birthDate, deathDate);
    }

    public String getFechaNacimiento() {
        if (fechaNacimiento != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return dateFormat.format(fechaNacimiento);
        }
        return "Desconocido";
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaFallecimiento() {
        if (fechaFallecimiento != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            return dateFormat.format(fechaFallecimiento);
        }
        return "Dato desconocido o Autor Vivo";
    }

    public void setFechaFallecimiento(Date fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



}
