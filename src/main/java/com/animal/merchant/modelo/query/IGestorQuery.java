package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;

/**
 * Se encarga de definir el método de gestion y si un query da una salida
 */
public interface IGestorQuery {
    /**
     * Gestiona el query de entrada
     *
     * @param query entrada
     * @return
     */
    String gestionarQuery(String query) throws NumeroNoValidoException;

    boolean devuelveOutput();


}
