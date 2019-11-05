package com.animal.merchant.procesamiento;


import com.animal.merchant.modelo.ConfiguracionQuery;
import com.animal.merchant.modelo.TipoQuery;
import com.animal.merchant.utilidades.Utils;

import java.util.List;

/**
 * Se encarga de realizar los procesos de la consulta de entrada
 */
public class ProcesadorQuery {

    private static ProcesadorQuery procesadorQuery;
    private List<ConfiguracionQuery> lstConfiguraiconQuery;

    private ProcesadorQuery() {


    }

    public static ProcesadorQuery obtenerInstancia() {

        if (procesadorQuery == null) {
            procesadorQuery = new ProcesadorQuery();
        }
        return procesadorQuery;

    }

    public void ConfigurarTipoQuery(List<ConfiguracionQuery> lstConfiguracionQueryVal) {
        this.lstConfiguraiconQuery = lstConfiguracionQueryVal;
    }

    /**
     * Obtiene el tipo de query en base al texto de entrada
     *
     * @param textoEntrada texto de entrada
     * @return tpo de query a retornar
     */
    public TipoQuery obtenerTipoQuery(String textoEntrada) {
        return this.lstConfiguraiconQuery
                .stream()
                .filter(lstConf -> Utils.IsMatch(textoEntrada, lstConf.getRegEx()))
                .findFirst().map(ConfiguracionQuery::getTipoQuery).orElse(TipoQuery.NoValida);

    }

    //private void getNoValida() {
    //}

    private void getTipoQuery(ConfiguracionQuery configuracionQuery) {
    }
}
