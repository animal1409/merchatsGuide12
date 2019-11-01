package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.utilidades.Utils;

import java.util.List;

//import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CuantitativaQuery implements IGestorQuery {
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {
        //how much is pish tegj glob glob ?
        //control signo pregunta
        query = query.replace("?", "");


        String palabraPedidoExtraterrestre = Utils.DescomponerTexto(" is ", query).get(1);
        List<String> lstExtrarrestre = Utils.DescomponerTexto(" ", palabraPedidoExtraterrestre);
        ProcesadorNumeroRomano proc = ProcesadorNumeroRomano.obtenerInstancia();
        String nroRomano = proc.obtenerNumeroRomanoDePalabrasExtraterrestres(lstExtrarrestre);
        int nroDecimal = proc.convertirRomanoADecimal(nroRomano);
        return construirRespuesta(palabraPedidoExtraterrestre, nroDecimal);
    }

    private String construirRespuesta(String palabraPedidoExtraterrestre, int nroDecimal) {
        return palabraPedidoExtraterrestre + " is " + nroDecimal;
    }

    @Override
    public boolean devuelveOutput() {
        return true;
    }
}
