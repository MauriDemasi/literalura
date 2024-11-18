package com.example.literalura.services;

import com.example.literalura.models.*;
import com.example.literalura.repository.AuthorRepository;
import com.example.literalura.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService {

    private ConsumoAPI consumo = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;


    public Book findByTitulo(String titulo) {
        // Sanitizar el título antes de buscarlo
        String sanitizedTitulo = titulo.trim();

        // Verificar si el libro ya existe en la base de datos
        Optional<Book> existingBook = bookRepository.findByTitulo(sanitizedTitulo);
        if (existingBook.isPresent()) {
            return existingBook.get(); // Retornar el libro ya guardado
        }

        // Buscar el libro en la API externa si no está en la base de datos
        String url = URL_BASE + "?search=" + sanitizedTitulo.replace(" ", "%20");
        var json = consumo.obtenerDatos(url);

        // Transformar el JSON en un objeto ResponseContainer
        ResponseContainer responseContainer = conversor.transformarDatos(json, ResponseContainer.class);
        List<DatoBook> librosEncontrados = responseContainer.getResults();

        if (librosEncontrados.isEmpty()) {
            System.out.println("No se encontró ningún libro con ese título.");
            return null;
        }

        // Procesar el primer libro encontrado y sus autores
        DatoBook datoBook = librosEncontrados.get(0);
        List<Personas> autoresGuardados = datoBook.authors().stream()
                .map(datoAuthor -> {
                    Personas autor = authorRepository.findByNombre(datoAuthor.nombre());
                    if (autor == null) { // Guardar autor si no existe
                        autor = datoAuthor.toPerson();
                        autor = authorRepository.save(autor);
                    }
                    return autor;
                })
                .toList();

        // Crear un nuevo libro con los autores guardados
        Book nuevoLibro = new Book(datoBook);
        nuevoLibro.setAutores(autoresGuardados);
        nuevoLibro.setIdiomas(datoBook.languages());
        nuevoLibro.setNumeroDescargas(datoBook.downloadCount());


        // Verificar si el libro ya existe en la base de datos antes de guardarlo
        Optional<Book> libroExistente = bookRepository.findByTitulo(nuevoLibro.getTitulo());
        if (libroExistente.isPresent()) {
            System.out.println("El libro ya existe en la base de datos.");
            return libroExistente.get(); // Retornar el libro ya guardado
        }
        // Si el libro no existe, guardamos el nuevo libro en la base de datos
        return bookRepository.save(nuevoLibro);
    }

    public void findAll() {
        List<Book> libros = bookRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados:");
            for (Book libro : libros) {
                System.out.println("----- LIBRO -----");
                System.out.println("Titulo: " + libro.getTitulo());

                String autores = libro.getAutores().stream()
                        .map(Personas::getNombre)
                        .collect(Collectors.joining(", "));
                System.out.println("Autor/es: " + autores);

                String idiomas = String.join(", ", libro.getIdiomas());
                System.out.println("Idioma: " + idiomas);

                System.out.println("Numero de descargas: " + libro.getNumeroDescargas());
                System.out.println("-------------------");
            }
        }

    }


    public void findAllAuthors() {
        List<Personas> autores = authorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            for (Personas autor : autores) {
                System.out.println("----- AUTOR -----");
                System.out.println("Nombre: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + autor.getFechaNacimiento());
                System.out.println("Fecha de defunción: " + autor.getFechaFallecimiento());
                System.out.println("Libros: " + autor.getBooks().stream()
                        .map(Book::getTitulo)
                        .collect(Collectors.toList())
                );
                System.out.println("-------------------");
            }
        }
    }

    public List<Personas> findAliveAuthors(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, 11, 31); // 31 de diciembre del año especificado
        Date fechaReferencia = cal.getTime();

        return authorRepository.findAliveAuthorsInYear(fechaReferencia);
    }

    public List<Book> findByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}



