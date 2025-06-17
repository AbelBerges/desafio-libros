package com.desarrollo.desafio_literatura.repositorio;

import com.desarrollo.desafio_literatura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositorioLibro extends JpaRepository<Libro, Integer> {
    Optional<Libro> findByTitulo(String titulo);

    @Query(value = "SELECT l FROM Libro l JOIN l.autor JOIN l.idioma")
    List<Libro> findAll();

    @Query(value = "SELECT l FROM Libro l JOIN l.autor a JOIN l.idioma i WHERE i.idioma LIKE %:cadena%")
    List<Libro> porIdioma(String cadena);
}
