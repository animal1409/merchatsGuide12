package com.animal.merchant.excepciones;



/**
 * Excepcion de Número no válido
 */

public class NumeroNoValidoException extends Exception {

    private String descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
