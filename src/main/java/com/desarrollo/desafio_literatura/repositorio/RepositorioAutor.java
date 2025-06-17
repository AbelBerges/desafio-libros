package com.desarrollo.desafio_literatura.repositorio;

import com.desarrollo.desafio_literatura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositorioAutor extends JpaRepository<Autor, Integer> {
    Optional<Autor> findByNombre(String nombre);

    @Query(value = "SELECT a FROM Autor a")
    List<Autor> buscarTodosLosAutores();

    @Query(value = "SELECT a FROM Autor a WHERE fechaNacimiento = :nacimiento")
    List<Autor> buscarAutorPorNAcimiento(int nacimiento);
}
