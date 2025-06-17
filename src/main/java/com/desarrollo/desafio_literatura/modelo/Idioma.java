package com.desarrollo.desafio_literatura.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "idioma")
public class Idioma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_idioma;
    private String idioma;
    @OneToMany(mappedBy = "idioma")
    private List<Libro> libros;

    public Idioma() {}

    public Idioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getId_idioma() {
        return id_idioma;
    }

    public void setId_idioma(Integer id_idioma) {
        this.id_idioma = id_idioma;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(l-> l.setIdiomas(this));
        this.libros = libros;
    }
}
