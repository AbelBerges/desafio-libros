package com.desarrollo.desafio_literatura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLIbros(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors")List<DatosAutor> autor,
        @JsonAlias("birth_year") int nacimiento,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("download_count") Double descargas
        ) {
}
