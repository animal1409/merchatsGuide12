package com.animal.merchant.modelo;

import lombok.Data;

/**
 * Complex type para configuracion de consultas
 */
@Data
public class ConfiguracionQuery {

    private TipoQuery tipoQuery;
    private String regEx;

    public ConfiguracionQuery(TipoQuery tipoQueryVal, String regExVal) {
        this.tipoQuery = tipoQueryVal;
        this.regEx = regExVal;
    }

}


