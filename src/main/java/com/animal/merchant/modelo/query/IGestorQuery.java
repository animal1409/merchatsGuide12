package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;

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
