package com.animal.merchant.modelo;

import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class NumeroRomanoTest {


    @Before
    public void ConfigurarRomano()
    {
        List<NumeroRomano> lstNumeroRomano = new ArrayList<NumeroRomano>();
        lstNumeroRomano.add(new NumeroRomano('I', 1));
        lstNumeroRomano.add(new NumeroRomano('V', 5));
        lstNumeroRomano.add(new NumeroRomano('X', 10));
        lstNumeroRomano.add(new NumeroRomano('L', 50));
        lstNumeroRomano.add(new NumeroRomano('C', 100));
        lstNumeroRomano.add(new NumeroRomano('D', 500));
        lstNumeroRomano.add(new NumeroRomano('M', 1000));
        ProcesadorNumeroRomano.obtenerInstancia().ConfigurarRomanos(lstNumeroRomano);
    }


    @Test
    public void ProbarRomanos() throws NumeroNoValidoException {


        int numDecimalI = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("I");
        Assert.assertEquals(1, numDecimalI);
        int numDecimalV = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("V");
        Assert.assertEquals(5, numDecimalV);
        int numDecimalX = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("X");
        Assert.assertEquals(10, numDecimalX);
        int numDecimalL = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("L");
        Assert.assertEquals(50, numDecimalL);
        int numDecimalC = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("C");
        Assert.assertEquals(100, numDecimalC);
        int numDecimalD = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("D");
        Assert.assertEquals(500, numDecimalD);
        int numDecimalM = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("M");
        Assert.assertEquals(1000, numDecimalM);


    }

    @Test
    public void ProbarDecimalesCompuestos() throws NumeroNoValidoException {
        int numDecimalII = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("II");
        Assert.assertEquals(2, numDecimalII);
        Assert.assertNotEquals(3, numDecimalII);


        int numDecimalXXX = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("XXX");
        Assert.assertEquals(30, numDecimalXXX);
        Assert.assertNotEquals(40, numDecimalXXX);

        int numDecimalIV = ProcesadorNumeroRomano.obtenerInstancia().convertirRomanoADecimal("IV");
        Assert.assertEquals(4, numDecimalIV);
        Assert.assertNotEquals(5, numDecimalIV);

    }

}
