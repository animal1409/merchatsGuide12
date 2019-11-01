package com.animal.merchant.modelo;

import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import lombok.Data;

@Data
public class NumeroRomano {
    private char signo;
    private int nDecimal;

    //region Constructors
    public NumeroRomano(char signoVal) throws NumeroNoValidoException {
        this.signo = signoVal;
        this.nDecimal = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal(Character.toString(signo));
    }

    public NumeroRomano(char signoVal, int nDecimalVal) {
        this.signo = signoVal;
        this.nDecimal = nDecimalVal;
    }

}
