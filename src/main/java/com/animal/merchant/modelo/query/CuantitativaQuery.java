package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.modelo.ContenedorDatos;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.utilidades.Utils;

import java.util.List;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * Realiza el procesamiento del tipo de query cuantitativo
 */
public class CuantitativaQuery implements IGestorQuery {
    /**
     * Gestiona el tipo de query cuantitativo
     * @param query entrada
     * @return
     * @throws NumeroNoValidoException
     */
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {
        //how much is pish tegj glob glob ?
        //control signo pregunta
        query = query.replace("?", "");


        String palabraPedidoExtraterrestre = Utils.DescomponerTexto(" is ", query).get(1);
        List<String> lstExtrarrestre = Utils.DescomponerTexto(" ", palabraPedidoExtraterrestre);
        if(!ContenedorDatos.obtenerInstancia().palabrasExtraterrestresDefinidas(lstExtrarrestre))
        {
            return "Existen palabras extraterrestres no definidas previamente";
        }

        ProcesadorNumeroRomano proc = ProcesadorNumeroRomano.obtenerInstancia();
        String nroRomano = proc.obtenerNumeroRomanoDePalabrasExtraterrestres(lstExtrarrestre);
        int nroDecimal = proc.convertirRomanoADecimal(nroRomano);
        return construirRespuesta(palabraPedidoExtraterrestre, nroDecimal);
    }

    /**
     * contruye la respuesta que debe devolver para este tipo de query
     * @param palabraPedidoExtraterrestre
     * @param nroDecimal
     * @return
     */
    private String construirRespuesta(String palabraPedidoExtraterrestre, int nroDecimal) {
        return palabraPedidoExtraterrestre + " is " + nroDecimal;
    }

    /**
     * Indica si este query devulve una salida
     * @return
     */
    @Override
    public boolean devuelveOutput() {
        return true;
    }
}
