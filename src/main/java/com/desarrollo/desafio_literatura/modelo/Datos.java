package com.desarrollo.desafio_literatura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Datos (
    @JsonAlias("results") List<DatosLIbros> listaLibros
) {
}
