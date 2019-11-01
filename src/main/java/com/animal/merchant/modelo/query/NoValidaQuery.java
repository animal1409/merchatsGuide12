package com.animal.merchant.modelo.query;

import com.animal.merchant.excepciones.NumeroNoValidoException;

public class NoValidaQuery implements IGestorQuery {
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {
        return "I have no idea what you are talking about";
    }

    @Override
    public boolean devuelveOutput() {
        return true;
    }
}
