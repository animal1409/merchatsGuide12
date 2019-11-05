package com.animal.merchant.modelo;

import lombok.Data;

import java.util.HashMap;

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
    public static void ResetearDatos() {
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
}
