package com.desarrollo.desafio_literatura.app;

import com.desarrollo.desafio_literatura.modelo.*;
import com.desarrollo.desafio_literatura.repositorio.RepositorioAutor;
import com.desarrollo.desafio_literatura.repositorio.RepositorioIdioma;
import com.desarrollo.desafio_literatura.repositorio.RepositorioLibro;
import com.desarrollo.desafio_literatura.servicio.ConsumirAPI;
import com.desarrollo.desafio_literatura.servicio.ConviertoDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inicio {
    private Scanner entrada = new Scanner(System.in);
    private ConsumirAPI api = new ConsumirAPI();
    private ConviertoDatos conversor = new ConviertoDatos();
    private final String URL_BASE_BUSQUEDA = "https://gutendex.com/books/?search=";
    private final String URL_BASE = "https://gutendex.com/books/";

    private List<DatosLIbros> listadoLibro = new ArrayList<>();
    private RepositorioLibro repositorio;
    private RepositorioAutor repoAutor;
    private RepositorioIdioma repoIdioma;
    public Inicio() {}
    public Inicio(RepositorioLibro repo, RepositorioAutor repoAutor, RepositorioIdioma repoIdioma) {
        this.repositorio = repo;
        this.repoAutor = repoAutor;
        this.repoIdioma = repoIdioma;

    }

    public void cargoMenu() {

        boolean control = false;
        do {
            System.out.println("Sistema de búsqueda de libros");
            System.out.println("1- Buscar titulo");
            System.out.println("2- Listar libros registrados");
            System.out.println("3- Listar autores registrados");
            System.out.println("4- Listar autores vivos en determinado año");
            System.out.println("5- Listar libros por idioma");
            System.out.println("0- Salir");
            try {
                int eleccion = entrada.nextInt();

                switch (eleccion) {
                    case 1: {
                        entrada.nextLine();
                        buscarLibro();
                        break;
                    }
                    case 2: {
                        listarTodosLosLibros();
                        break;
                    }
                    case 3: {
                        listarTodosLosAutores();
                        break;
                    }
                    case 4: {
                        entrada.nextLine();
                        buscar_fecha_nacimiento();
                        break;
                    }
                    case 5: {
                        entrada.nextLine();
                        buscarPorIdioma();
                        break;
                    }
                    case 0: {
                        control = true;
                        break;
                    }
                    default: {
                        System.out.println("Debe elegir un número entre 1 y 8");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Debe ingresar un número entero");
            }
        } while (!control);
        //buscar_fecha_nacimiento();
    }

    private void buscarLibro() {
        System.out.println("Ingrese un título o parte de él para hacer la búsqueda");
        String cadena = entrada.nextLine();
        if (!cadena.isEmpty()) {
            var busco = api.obtenerDatos(URL_BASE_BUSQUEDA + cadena.replace(" ", "%20"));
            var construyo = conversor.obtengoDatos(busco, Biblioteca.class);
            if (construyo.libros().isEmpty()){
                System.out.println("No se ha enconrado el título ");
                return;
            }
            List<DatosLIbros> listado = new ArrayList<>();
            listado.addAll(construyo.libros());
            System.out.println(listado);
            listado.stream().limit(1).forEach(lib -> {
                if (lib.autor().isEmpty()) {
                    System.out.println("El libro no tiene autor, no se guarda");
                    return;
                }
                if (lib.idiomas().get(0).isEmpty()) {
                    System.out.println("El libro no tiene idioma, no se guarda");
                    return;
                }

                var tomoIdioma = lib.idiomas().get(0);
                String elIdioma = tomoIdioma; //hay que seguir con el armado de idioma
                Idioma i = repoIdioma.findByIdioma(elIdioma).orElseGet(()-> {
                    Idioma nuevoIdioma = new Idioma(elIdioma);
                    return repoIdioma.save(nuevoIdioma);
                });
                var datosAutor = lib.autor().get(0);
                String nomAutor = datosAutor.nombre();
                int nacimiento = Integer.parseInt(datosAutor.nacimiento());
                Autor elAutor = repoAutor.findByNombre(nomAutor).orElseGet(()->{
                   Autor nuevoAutor = new Autor(nomAutor, nacimiento);
                   return repoAutor.save(nuevoAutor);
                });
                String tomoDatosLibros = lib.titulo();
                Libro temp = repositorio.findByTitulo(tomoDatosLibros).orElseGet(()-> {
                    Libro nuevo = new Libro(lib.titulo(), lib.descargas(), elAutor, i);
                    return repositorio.save(nuevo);
                });
            });

        } else {
            System.out.println("El título no puede estar vacío");
        }
    }

    private void listarTodosLosLibros() {
        List<Libro> resultado = repositorio.findAll();
        resultado.stream().forEach(l -> {
            System.out.println("Titulo: " + l.getTitulo() + ", descargas: " + l.getDescargas() + ", Autor: " +
                    l.getAutor().getNombre() + ", Nacimiento: " + l.getAutor().getFechaNacimiento() + ", Idioma: " +
                    l.getIdiomas().getIdioma());
        });
    }

    private void listarTodosLosAutores() {
        List<Autor> resultado = repoAutor.buscarTodosLosAutores();
        resultado.stream().forEach(a -> {
            System.out.println("Nombre: " + a.getNombre() + ", Nacimiento: " + a.getFechaNacimiento());
        });
    }

    private void buscar_fecha_nacimiento() {
        System.out.println("Ingrese la fecha de nacimiento desde la que se hara la búsqueda");
        try {
            Integer nacio = entrada.nextInt();
            String conver = String.valueOf(nacio);
            if (nacio > 0 && !conver.isEmpty()) {
                List<Autor> resultado = repoAutor.buscarAutorPorNAcimiento(nacio);
                if (!resultado.isEmpty()){
                    resultado.forEach(a -> {
                        System.out.println("Nombre: " + a.getNombre() + ", Nació en: " + a.getFechaNacimiento());
                    });
                } else {
                    System.out.println("No se han encontrado autores en el año " + conver);
                }
            } else {
                System.out.println("La búsqueda no pueda estar vacía");
            }
        } catch (NumberFormatException e) {
            System.out.println("Valor númerico no válido");
        }
    }

    private void buscarPorIdioma() {
        System.out.println("Ingrese la identificación del idioma para hacer la búsqueda. Por ej: Inglés=en, Español=es, Frances=fr, etc");
        String cadena = entrada.nextLine();
        if (!cadena.isEmpty()) {
            List<Libro> resultado = repositorio.porIdioma(cadena);
            if (!resultado.isEmpty()) {
                resultado.forEach(l-> {
                    System.out.println("Titulo: " + l.getTitulo() + ", Descargas: " + l.getDescargas() + ", Autor:" +
                            l.getAutor().getNombre() + ", Idioma: " + l.getIdiomas().getIdioma());
                });
            }
        } else {
            System.out.println("La opción de búsqueda no puede estar vacía");
        }
    }
}
