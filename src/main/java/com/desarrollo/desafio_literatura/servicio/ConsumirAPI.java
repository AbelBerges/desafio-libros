package com.desarrollo.desafio_literatura.servicio;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class ConsumirAPI {

    public String obtenerDatos(String laUrl) {
        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requerimiento = HttpRequest.newBuilder(URI.create(laUrl)).build();
        HttpResponse<String> respuesta = null;
        try {
            respuesta = cliente.send(requerimiento, HttpResponse.BodyHandlers.ofString());
            if (respuesta.previousResponse().isEmpty()){
                return respuesta.body();
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        String elJson = respuesta.body();
        return elJson;
    }

}
