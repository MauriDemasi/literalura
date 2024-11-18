package com.example.literalura.repository;

import com.example.literalura.models.Personas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface AuthorRepository extends  JpaRepository<Personas, Long>{
    Personas findByNombre(String nombre);


    @Query("SELECT p FROM Personas p WHERE p.fechaNacimiento <= :fecha AND " +
            "(p.fechaFallecimiento IS NULL OR p.fechaFallecimiento >= :fecha)")
    List<Personas> findAliveAuthorsInYear(@Param("fecha") Date fecha);


}
