package com.desarrollo.desafio_literatura;

import com.desarrollo.desafio_literatura.app.Inicio;
import com.desarrollo.desafio_literatura.repositorio.RepositorioAutor;
import com.desarrollo.desafio_literatura.repositorio.RepositorioIdioma;
import com.desarrollo.desafio_literatura.repositorio.RepositorioLibro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DesafioLiteraturaApplication implements CommandLineRunner {
	@Autowired
	public RepositorioLibro repositorio;
	@Autowired
	public RepositorioAutor repoAutor;
	@Autowired
	public RepositorioIdioma repoIdioma;
	public static void main(String[] args) {
		SpringApplication.run(DesafioLiteraturaApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		Inicio inicio = new Inicio(repositorio, repoAutor, repoIdioma);
		inicio.cargoMenu();
	}
}
