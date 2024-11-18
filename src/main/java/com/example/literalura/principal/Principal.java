package com.example.literalura.principal;

import com.example.literalura.models.Book;
import com.example.literalura.models.DatoBook;
import com.example.literalura.services.BookService;
import com.example.literalura.models.Personas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Clase principal que ejecuta el menú de opciones
 */
@Component
public class Principal {

    private Scanner teclado = new Scanner(System.in);

    @Autowired
    private BookService bookService;


    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0){
            System.out.println("------------------------");
            System.out.println("Elija la opcion a través de su número");
            System.out.println("1. Buscar libro por titulo");
            System.out.println("2. Listar libros registrados");
            System.out.println("3. Listar autores registrados");
            System.out.println("4. Listar autores vivos en un determinado año");
            System.out.println("5. Listar libros por idioma");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");
            opcion = teclado.nextInt();
            teclado.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    getBookByTitulo();
                    break;
                case 2:
                    getBooksOnDatabase();
                    break;
               case 3:
                   getAuthorsOnDatabase();
                   break;
                case 4:
                    getAuthorsByYear();
                    break;
                case 5:
                    getBooksByLanguage();
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Inténtelo de nuevo.");
            }
        }
    }

    private void getBooksByLanguage() {
        System.out.println("Ingrese el idioma del libro: ");
        System.out.println("es - español");
        System.out.println("en - inglés");
        System.out.println("fr - frances");
        System.out.println("pt - portugués");
        String language = teclado.nextLine();

        String[] validLanguages = {"es", "en", "fr", "pt"};
        boolean isValidLanguage = false;
        while (!isValidLanguage) {
            for (String validLanguage : validLanguages) {
                if (language.equals(validLanguage)) {
                    isValidLanguage = true;
                    break;
                }
            }
            if (!isValidLanguage) {
                System.out.println("Idioma no válido. Inténtelo de nuevo.");
                System.out.print("Ingrese el idioma del libro: ");
                language = teclado.nextLine();
            }
        }


        List<Book> libros = bookService.findByLanguage(language);

        if (libros.isEmpty()) {
            System.out.println("No se encontraron libros en el idioma " + language);
        } else {
            System.out.println("Libros en el idioma " + language + ":");
            libros.stream()
                    .map(libro -> new DatoBook(
                            libro.getTitulo(),
                            libro.getAutores().stream()
                                    .map(Personas::toDatoAuthor)
                                    .toList(),
                            libro.getIdiomas(),
                            libro.getNumeroDescargas()
                    ))
                    .collect(Collectors.toList())
                    .forEach(System.out::println);
        }
    }

    private void getAuthorsByYear() {
        int year;

        // Bucle para asegurarnos de que el usuario ingresa un año válido
        while (true) {
            System.out.print("Ingrese el año de nacimiento: ");

            // Validamos que la entrada sea un número entero
            if (!teclado.hasNextInt()) {
                System.out.println("Por favor, ingrese un número válido.");
                teclado.next(); // Limpiar el buffer del scanner
                continue; // Volver a solicitar el input si no es un número
            }

            year = teclado.nextInt();

            // Validamos que el año sea positivo y no mayor al año actual
            if (year < 0 || year > LocalDate.now().getYear()) {
                System.out.println("Año inválido. Inténtelo de nuevo.");
                continue; // Volver a solicitar el input si el año es inválido
            }

            // Si el año es válido, salimos del ciclo
            break;
        }

        // Limpiar el buffer en caso de que el año se haya ingresado correctamente
        teclado.nextLine();

        // Realizar la consulta con el año ingresado
        List<Personas> autores = bookService.findAliveAuthors(year);

        // Mostrar los resultados
        if (autores.isEmpty()) {
            System.out.println("No se encontraron autores nacidos en el año " + year);
        } else {
            System.out.println("Autores nacidos en el año " + year + ":");
            for (Personas autor : autores) {
                System.out.println(autor.getNombre());
            }
        }
    }

    private void getAuthorsOnDatabase() {
        bookService.findAllAuthors();
    }

    private void getBookByTitulo() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = teclado.nextLine();

        Optional<Book> libroEncontrado = Optional.ofNullable(bookService.findByTitulo(titulo));

        libroEncontrado.ifPresentOrElse(
                libro -> {
                    // Crear un DatoBook temporal
                    DatoBook datoBook = new DatoBook(
                            libro.getTitulo(),
                            libro.getAutores().stream()
                                    .map(Personas::toDatoAuthor)
                                    .toList(),
                           libro.getIdiomas(),
                            libro.getNumeroDescargas()
                    );

                    System.out.println(datoBook.toString());
                },
                () -> System.out.println("Libro no encontrado.")
        );
    }



    private void getBooksOnDatabase() {
       bookService.findAll();
    }


}

