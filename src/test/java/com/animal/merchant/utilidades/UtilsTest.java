package com.animal.merchant.utilidades;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UtilsTest {
    @Test
    public void ProbarSeparadorTexto() {
        String texto = " elemento1  is  elemento2  is  elemento3 ";

        List<String> lstTextoSeparado = Utils.DescomponerTexto(" is ", texto);

        Assert.assertEquals(3, lstTextoSeparado.size());
        Assert.assertEquals("elemento1", lstTextoSeparado.get(0));
        Assert.assertEquals("elemento2", lstTextoSeparado.get(1));
        Assert.assertEquals("elemento3", lstTextoSeparado.get(2));

    }
}
