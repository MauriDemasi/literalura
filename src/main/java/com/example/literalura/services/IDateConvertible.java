package com.example.literalura.services;

public interface IDateConvertible {
    <T> T transformarDatos(String json, Class<T> clase);
}
