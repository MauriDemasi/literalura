package com.example.literalura.repository;

import com.example.literalura.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long>{

    // Buscar un libro por título (insensible a mayúsculas/minúsculas)
    @Query("SELECT b FROM Book b WHERE LOWER(b.titulo) = LOWER(:titulo)")
    Optional<Book> findByTitulo(@Param("titulo") String titulo);

    // Traemos todos los libros
    List <Book> findAll();

    @Query(value = "SELECT * FROM books WHERE array_to_string(idiomas, ',') LIKE %:language%", nativeQuery = true)
    List<Book> findByLanguage(@Param("language") String language);



}
