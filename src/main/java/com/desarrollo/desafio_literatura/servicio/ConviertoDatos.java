package com.desarrollo.desafio_literatura.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConviertoDatos implements ConvertirDatos{
    private ObjectMapper mapeador = new ObjectMapper();

    @Override
    public <T> T obtengoDatos(String json, Class<T> clase) {
        try {
            return mapeador.readValue(json.toString(), clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
