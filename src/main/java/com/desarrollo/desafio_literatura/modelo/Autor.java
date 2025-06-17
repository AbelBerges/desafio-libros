package com.desarrollo.desafio_literatura.modelo;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_autor;
    @Column(unique = true)
    private String nombre;
    private int fechaNacimiento;
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {}
    public Autor(String nombre, int fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Autor(Integer id_autor, String nombre, int fechaNacimiento) {
        this.id_autor = id_autor;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getId_autor() {
        return id_autor;
    }

    public void setId_autor(Integer id_autor) {
        this.id_autor = id_autor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(int fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibro(List<Libro> libros) {
        libros.forEach(l -> l.setAutor(this));
        this.libros = libros;
    }
}
