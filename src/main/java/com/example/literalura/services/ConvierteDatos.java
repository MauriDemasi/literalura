package com.example.literalura.services;
import com.example.literalura.models.Book;
import com.example.literalura.models.DatoBook;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IDateConvertible {

    private ObjectMapper mapper = new ObjectMapper();


    public <T> T transformarDatos(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error al convertir JSON a objeto", e);
        }
    }
}
