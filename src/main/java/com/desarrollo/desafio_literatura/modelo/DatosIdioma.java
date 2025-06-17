package com.desarrollo.desafio_literatura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosIdioma(
        @JsonAlias("") String idioma
) {
}
