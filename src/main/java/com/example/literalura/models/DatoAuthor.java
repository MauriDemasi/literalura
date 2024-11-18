package com.example.literalura.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.expression.ParseException;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoAuthor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaDeNacimiento,
        @JsonAlias("death_year") String fechaDeFallecimiento
) {

    public Personas toPerson() {
        Personas autor = new Personas();
        autor.setNombre(this.nombre);

        // Manejo de la fecha de nacimiento
        if (this.fechaDeNacimiento != null && !this.fechaDeNacimiento.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            try {
                var fechaNacimiento = sdf.parse(this.fechaDeNacimiento);
                autor.setFechaNacimiento(fechaNacimiento);
            } catch (ParseException | java.text.ParseException e) {
                System.out.println("Error al parsear la fecha de nacimiento: " + e.getMessage());
            }
        }

        // Manejo de la fecha de fallecimiento
        if (this.fechaDeFallecimiento != null && !this.fechaDeFallecimiento.isEmpty()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy"); // Solo año
            try {
                Date fechaFallecimiento = dateFormat.parse(this.fechaDeFallecimiento);
                autor.setFechaFallecimiento(fechaFallecimiento);
            } catch (ParseException | java.text.ParseException e) {
                System.out.println("Error al parsear la fecha de fallecimiento: " + e.getMessage());
            }
        } else {
            autor.setFechaFallecimiento(null); // Mantener como null si no ha fallecido
        }

        return autor;
    }

    @Override
    public String toString() {
        return
                 nombre + '\'' ;
    }
}

