package com.animal.merchant.procesamiento;

import com.animal.merchant.modelo.ConfiguracionQuery;
import com.animal.merchant.modelo.ContenedorDatos;
import com.animal.merchant.modelo.TipoQuery;
import com.animal.merchant.modelo.query.FactoryGestorQuery;
import com.animal.merchant.modelo.query.IGestorQuery;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * se encarga del procesamiento general de toda la aplicacion
 */
@Slf4j
public class ProcesadorAplicacion {

    private static ProcesadorAplicacion procesadorAplicacion;
    private List<ConfiguracionQuery> lstConfiguracionQuery;
    private List<String> lstEntradas;
    private List<String> lstSalidas;

    private ProcesadorAplicacion() {

    }

    public static ProcesadorAplicacion obtenerInstancia() {
        if (procesadorAplicacion == null) {
            ContenedorDatos.ResetearDatos();
            procesadorAplicacion = new ProcesadorAplicacion();
            procesadorAplicacion.lstSalidas = new ArrayList<String>();
        }
        return procesadorAplicacion;

    }

    /**
     * Configuracion de queries con sus expresiones regulares
     *
     * @param lstConfiguracionQueryVal
     */
    public void configurarQueries(List<ConfiguracionQuery> lstConfiguracionQueryVal) {
        this.lstConfiguracionQuery = lstConfiguracionQueryVal;
    }

    public void ingresarEntradas(List<String> lstEntradasVal) {
        this.lstEntradas = lstEntradasVal;
    }

    /**
     * Hace el procesamienteo de todas las entradas configuradas
     */
    public void procesarEntradas() {

        this.lstEntradas.forEach(e -> {
            try {
                procesarQuery(e);
            } catch (Exception ex) {
                log.error("Error en procesarEntradas", ex);
            }
        });

    }

    /**
     * Procesa el query por unidad
     *
     * @param query
     */
    private void procesarQuery(String query) throws Exception {
        try {
            ProcesadorQuery procesadorQuery = ProcesadorQuery.obtenerInstancia();
            procesadorQuery.ConfigurarTipoQuery(this.lstConfiguracionQuery);
            TipoQuery tipoQuery = procesadorQuery.obtenerTipoQuery(query);
            IGestorQuery gestorQuery = new FactoryGestorQuery().obtenerGestorQuery(tipoQuery);
            boolean esOutput = gestorQuery.devuelveOutput();
            String respuesta = gestorQuery.gestionarQuery(query);
            if (esOutput) {
                this.lstSalidas.add(respuesta);
            }
        } catch (Exception ex) {
            log.error("Error en procesarQuery", ex);
            throw ex;
        }
    }


    /**
     * Obtiene las respuestas
     *
     * @return
     */
    public List<String> obtenerSalida() {
        return this.lstSalidas;
    }


}
