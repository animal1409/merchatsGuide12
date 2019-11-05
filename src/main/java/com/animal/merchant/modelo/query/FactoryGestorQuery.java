package com.animal.merchant.modelo.query;

import com.animal.merchant.modelo.TipoQuery;

/**
 * Devuelve el tipo de IGestonQuery en base al tipo de query obtenido
 */
public class FactoryGestorQuery {

    /**
     * Obtiene la instancia según el tipo de query proporcionado
     * @param tipoQuery
     * @return
     */
    public IGestorQuery obtenerGestorQuery(TipoQuery tipoQuery) {

        IGestorQuery res = null;
        switch (tipoQuery) {

            case Declarativa:
                res = new DeclarativaQuery();
                break;
            case Calculativa:
                res = new CalculativaQuery();
                break;
            case Crediticia:
                res = new CrediticiaQuery();
                break;
            case Cuantitativa:
                res = new CuantitativaQuery();
                break;
            case NoValida:
                res = new NoValidaQuery();
                break;
        }
        return res;

    }


}
