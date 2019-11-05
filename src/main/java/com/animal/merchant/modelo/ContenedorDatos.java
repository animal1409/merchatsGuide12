package com.animal.merchant.modelo;

import lombok.Data;

import java.util.HashMap;
import java.util.List;

/**
 * Clase encargada de devolver y almacenar los datos mientras la aplicación está en ejecución
 */
public class ContenedorDatos {

    private static ContenedorDatos contendorDatos;
    private HashMap<String, String> dicExtraterresteNumeroRomano;
    private HashMap<String, Double> dicValorMetal;

    private ContenedorDatos() {
        this.dicExtraterresteNumeroRomano = new HashMap<String, String>();
        this.dicValorMetal = new HashMap<String, Double>();

    }

    public static ContenedorDatos obtenerInstancia() {
        if (contendorDatos == null)
            contendorDatos = new ContenedorDatos();

        return contendorDatos;

    }

    /**
     * Resetea los datos guardados
     */
    public static void resetearDatos() {
        contendorDatos = null;
    }

    /**
     * Agrega una relacion de palabra extraterrestre con un numero romano
     * @param palabraExtraterrestre
     * @param valorRomano
     */
    public void AnadirExtraterrestreRomano(String palabraExtraterrestre, String valorRomano) {
        this.getDicExtraterresteNumeroRomano().put(palabraExtraterrestre, valorRomano);

    }

    /**
     * Agrega un nombre de metal con un valor del mismo
     * @param nombreMetal
     * @param valorMetal
     */
    public void AnadirValorMetal(String nombreMetal, Double valorMetal) {
        this.getDicValorMetal().put(nombreMetal, valorMetal);
    }

    /**
     * Devuleve los datos actuales de los extraterrester numeroRomano
     * @return
     */
    public HashMap<String, String> getDicExtraterresteNumeroRomano() {
        return dicExtraterresteNumeroRomano;
    }

    /**
     * Devuelve los datos actuales del valor de metal
     * @return
     */
    public HashMap<String, Double> getDicValorMetal() {
        return dicValorMetal;
    }



    /**
     * Define devuelve true si todas las palabras extraterrestres estan definidas
     * @param lstPalabrasExtraterrestres
     * @return
     */
    public boolean palabrasExtraterrestresDefinidas(List<String> lstPalabrasExtraterrestres) {

        boolean respuesta = true;


        for (String p : lstPalabrasExtraterrestres) {
            if (!ContenedorDatos.obtenerInstancia().getDicExtraterresteNumeroRomano().containsKey(p)) {
                return false;
            }
        }


        return respuesta;
    }

    /**
     * Obtiene el dato si existe el metal definido
     * @param nombreMetal
     * @return
     */
    public boolean nombreMetalDefinido(String nombreMetal) {
        boolean respuesta = true;
        if(!ContenedorDatos.obtenerInstancia().getDicValorMetal().containsKey(nombreMetal))
        {
            return false;
        }
        return respuesta;
    }
}
