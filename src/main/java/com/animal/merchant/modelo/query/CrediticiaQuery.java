package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.modelo.ContenedorDatos;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.utilidades.Utils;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Realiza el proceso de tipo de query crediticio
 */
@Slf4j
public class CrediticiaQuery implements IGestorQuery {

    /**
     * gestiona el tipo de query crediticio
     * @param query entrada
     * @return
     * @throws NumeroNoValidoException
     */
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {

        String res = "";

        query = query.replace("?", "");
        String textoPregunta = Utils.DescomponerTexto(" is ", query).get(1);
        //how many Credits is glob prok Iron ?
        List<String> lstPalabrasExtraterretres = Utils.DescomponerTexto(" ", textoPregunta);
        //el ultimo es el Metal
        String nombreMetal = lstPalabrasExtraterretres.get(lstPalabrasExtraterretres.size() - 1);

        List<String> lstExtraTerrestresRomanos = lstPalabrasExtraterretres.subList(0, lstPalabrasExtraterretres.size() - 1);



        if (!ContenedorDatos.obtenerInstancia().palabrasExtraterrestresDefinidas(lstExtraTerrestresRomanos))
        {
            return "Existen palabras extraterrestres no definidas previamente";
        }

        if(!ContenedorDatos.obtenerInstancia().nombreMetalDefinido(nombreMetal))
        {
            return "El metal ingresado no ha podido ser calculado";
        }

        //obtener el valor del Metal
        double valorMetal = ContenedorDatos.obtenerInstancia().getDicValorMetal().get(nombreMetal);




        ProcesadorNumeroRomano proc = ProcesadorNumeroRomano.obtenerInstancia();
        String nroRomano = proc.obtenerNumeroRomanoDePalabrasExtraterrestres(lstExtraTerrestresRomanos);
        int nroDecimal = proc.convertirRomanoADecimal(nroRomano);
        res = construirRespuesta(textoPregunta, nroDecimal, valorMetal);
        return res;

    }

    /**
     * Hace el cálculo para este tipo de query, obteniendo la salida que debe proporcionar dicha entrada
     * @param entrada
     * @param nroDecimal
     * @param valorMetal
     * @return
     */
    private String construirRespuesta(String entrada, int nroDecimal, double valorMetal) {


        double Valor = ((double) nroDecimal) * valorMetal;
        String strValor = new DecimalFormat("#").format(Valor);
        return entrada + " is " + strValor + " Credits";
    }

    /**
     * indica si devuelve salida de datos
     * @return
     */
    @Override
    public boolean devuelveOutput() {
        return true;
    }
}
