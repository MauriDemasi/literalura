package com.example.literalura.services;

import com.example.literalura.models.DatoBook;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class ConsumoAPI {


    public String obtenerDatos(String url) {
        // Crear HttpClient con manejo de redirecciones automáticas
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS) // Habilitar seguimiento de redirecciones
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = response.statusCode();

            if (statusCode != 200) {
                System.err.println("Error en la solicitud HTTP. Código de estado: " + statusCode);
                return null;
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener datos de la API", e);
        }

        return response.body();
    }
}

