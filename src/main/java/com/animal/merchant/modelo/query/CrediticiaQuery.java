package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.modelo.ContenedorDatos;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.utilidades.Utils;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;
import java.util.List;

@Slf4j
public class CrediticiaQuery implements IGestorQuery {
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {

        String res = "";

        query = query.replace("?", "");
        String textoPregunta = Utils.DescomponerTexto(" is ", query).get(1);
        //how many Credits is glob prok Iron ?
        List<String> lstPalabrasExtraterretres = Utils.DescomponerTexto(" ", textoPregunta);
        //el ultimo es el Metal
        String nombreMetal = lstPalabrasExtraterretres.get(lstPalabrasExtraterretres.size() - 1);
        //obtener el valor del Metal
        double valorMetal = ContenedorDatos.obtenerInstancia().getDicValorMetal().get(nombreMetal);
        List<String> lstExtraTerrestresRomanos = lstPalabrasExtraterretres.subList(0, lstPalabrasExtraterretres.size() - 1);

        ProcesadorNumeroRomano proc = ProcesadorNumeroRomano.obtenerInstancia();
        String nroRomano = proc.obtenerNumeroRomanoDePalabrasExtraterrestres(lstExtraTerrestresRomanos);
        int nroDecimal = proc.convertirRomanoADecimal(nroRomano);
        res = ConstruirRespuesta(textoPregunta, nroDecimal, valorMetal);
        return res;

    }

    private String ConstruirRespuesta(String entrada, int nroDecimal, double valorMetal) {

        log.info("nroDecimal: " + nroDecimal);
        log.info("valorMetal " + valorMetal);
        double Valor = ((double) nroDecimal) * valorMetal;
        String strValor = new DecimalFormat("#").format(Valor);
        return entrada + " is " + strValor + " Credits";
    }

    @Override
    public boolean devuelveOutput() {
        return true;
    }
}
