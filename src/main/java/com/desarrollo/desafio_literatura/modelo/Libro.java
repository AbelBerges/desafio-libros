package com.desarrollo.desafio_literatura.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libro")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_libro;
    @Column(unique = true)
    private String titulo;
    private Double descargas;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
    @ManyToOne
    @JoinColumn(name = "id_idioma")
    private Idioma idioma;
    @Transient
    private DatosLIbros datosLIbros;

    public Libro(){}

    public Libro(String titulo, Double descargas, Autor autores, Idioma idioma) {
        this.titulo = titulo;
        this.descargas = descargas;
        this.autor = autores;
        this.idioma = idioma;
    }

    public Libro(DatosLIbros datos) {
        this.datosLIbros = datos;
    }


    public int getId_libro() {
        return id_libro;
    }

    public void setId_libro(int id_libro) {
        this.id_libro = id_libro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }

    public DatosLIbros getDatosLIbros() {
        return datosLIbros;
    }

    public Idioma getIdiomas() {
        return idioma;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idioma = idiomas;
    }

    public void setDatosLIbros(DatosLIbros datosLIbros) {
        this.datosLIbros = datosLIbros;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
}
