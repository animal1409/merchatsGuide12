package com.animal.merchant.modelo.query;

import com.animal.merchant.excepciones.NumeroNoValidoException;

/**
 * Realiza el procesamiento del tipo de query no válida
 */
public class NoValidaQuery implements IGestorQuery {
    /**
     * gestiona el tipo de query no válida
     * @param query entrada
     * @return
     * @throws NumeroNoValidoException
     */
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {
        return "I have no idea what you are talking about";
    }

    /**
     * indica si develve salida
     * @return
     */
    @Override
    public boolean devuelveOutput() {
        return true;
    }
}
