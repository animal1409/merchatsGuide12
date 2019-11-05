package com.animal.merchant.modelo.query;

import com.animal.merchant.modelo.ContenedorDatos;
import com.animal.merchant.procesamiento.ProcesadorAplicacion;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.utilidades.Utils;

import java.util.List;

/**
 * Realiza el procesamiento del tipo de query declarativa
 */
public class DeclarativaQuery implements IGestorQuery {
    private boolean devuelveRespuesta = false;
    /**
     * Gestiona el tipo de query declarativo
     *
     * @return
     */
    @Override
    public String gestionarQuery(String query) {
        String res = "";


        List<String> lstParametros = Utils.DescomponerTexto(" is ", query);

        String valorRomano = lstParametros.get(1);

        ContenedorDatos.obtenerInstancia().AnadirExtraterrestreRomano(lstParametros.get(0), valorRomano);
        return res;
    }

    /**
     * indica si devuelve salida de datos
     * @return
     */
    @Override
    public boolean devuelveOutput() {
        return this.devuelveRespuesta;
    }
}
