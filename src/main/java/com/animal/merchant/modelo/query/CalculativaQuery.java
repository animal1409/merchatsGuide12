package com.animal.merchant.modelo.query;


import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.modelo.ContenedorDatos;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.utilidades.Utils;


import java.util.ArrayList;
import java.util.List;


/**
 * Implementacion de tipo de query calculativa
 */
public class CalculativaQuery implements IGestorQuery {

    private boolean devuelveRespuesta =false;
    /**
     * Gestiona el tipo de query calculativa
     * @param query entrada
     * @return salida
     * @throws NumeroNoValidoException
     */
    @Override
    public String gestionarQuery(String query) throws NumeroNoValidoException {
        String res = "";
        //glob glob Silver is 34 Credits

        //split por "is"
        List<String> lstPrincipal = Utils.DescomponerTexto(" is ", query);

        //obtener del [0] los n-1
        List<String> lstIzquierda = Utils.DescomponerTexto(" ", lstPrincipal.get(0));

        String metal = lstIzquierda.get(lstIzquierda.size() - 1);

        List<String> lstPalabrasExtraterrestres = new ArrayList<String>();

        //obtenemos solo los nomrbres exterrestres
        for (int i = 0; i < lstIzquierda.size() - 1; i++) {
            lstPalabrasExtraterrestres.add(lstIzquierda.get(i));
        }


        if (!palabrasExtraterrestresDefinidas(lstPalabrasExtraterrestres))
        {
            Utils.PrintText("Ha Ingresado en que no existen las palabras extraterrestres");
            this.devuelveRespuesta = true;
            return "Existen palabras extraterrestres no definidas previamente";
        }

        //pediro en el hashmap los caracteres de romano
        String nroRomano = obtenerNumeroRomanoDePalabrasExtraterrestres(lstPalabrasExtraterrestres);
        //concatenar y pedir el numero en ProcesadorNumeroRomano

        int nroDecimal = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal(nroRomano);
        //hacer funcion de devolucion del Valor del metal

        int nroCreditos = Integer.parseInt(Utils.DescomponerTexto(" ", lstPrincipal.get(1)).get(0));
        double valorMetal = ObtenerValorMetal(nroCreditos, nroDecimal);

        //guardar el metal con su valor
        ContenedorDatos.obtenerInstancia().AnadirValorMetal(metal, valorMetal);


        return res;
    }

    /**
     * Define devuelve true si todas las palabras extraterrestres estan definidas
     * @param lstPalabrasExtraterrestres
     * @return
     */
    private boolean palabrasExtraterrestresDefinidas(List<String> lstPalabrasExtraterrestres) {

        boolean respuesta = true;

        Utils.PrintText("ha ingresado en la funcion de texto");

        for (String p : lstPalabrasExtraterrestres) {
            if (!ContenedorDatos.obtenerInstancia().getDicExtraterresteNumeroRomano().containsKey(p)) {
                Utils.PrintText("No ha encontrado la palabra "+p);
                return false;
            }
        }


        return respuesta;
    }


    private Double ObtenerValorMetal(int nroCreditos, int nroDecimal) {
        return (((double) nroCreditos) / ((double) nroDecimal));
    }

    /**
     * Obtiene el número romano dada una lista de palabras extraterrestres
     * @param lstPalabrasExtraterrestres
     * @return
     */
    private String obtenerNumeroRomanoDePalabrasExtraterrestres(List<String> lstPalabrasExtraterrestres) {
        return ProcesadorNumeroRomano.obtenerInstancia().obtenerNumeroRomanoDePalabrasExtraterrestres(lstPalabrasExtraterrestres);
    }

    /**
     * Indica si devuelveSalida de datos
     * @return
     */
    @Override
    public boolean devuelveOutput() {
        return this.devuelveRespuesta;
    }
}
