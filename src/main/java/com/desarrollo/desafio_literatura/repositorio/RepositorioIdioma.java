package com.desarrollo.desafio_literatura.repositorio;

import com.desarrollo.desafio_literatura.modelo.Idioma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepositorioIdioma extends JpaRepository<Idioma, Integer> {
    Optional<Idioma> findByIdioma(String idioma);
}
