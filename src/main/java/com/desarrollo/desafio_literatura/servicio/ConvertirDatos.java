package com.desarrollo.desafio_literatura.servicio;

public interface ConvertirDatos {

    <T> T obtengoDatos(String json, Class<T> clase);
}
