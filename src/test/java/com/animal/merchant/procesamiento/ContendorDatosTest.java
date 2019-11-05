package com.animal.merchant.procesamiento;

import com.animal.merchant.modelo.ContenedorDatos;
import org.junit.Assert;
import org.junit.Test;

public class ContendorDatosTest {
    @Test
    public void ProbarContenedorDatos() {
        ContenedorDatos.resetearDatos();
        ContenedorDatos contenedorDatos = ContenedorDatos.obtenerInstancia();

        //debe iniciar el I y luego reeamplazar el II
        contenedorDatos.AnadirExtraterrestreRomano("clink", "I");
        contenedorDatos.AnadirExtraterrestreRomano("clink", "II");

        //debe obtener la misma instancia y anadir clank como 2do elemento
        ContenedorDatos contenedor2 = ContenedorDatos.obtenerInstancia();
        contenedorDatos.AnadirExtraterrestreRomano("clank", "III");

        //obtener 3ra instancia (la misma) para consultas
        ContenedorDatos contendor3 = ContenedorDatos.obtenerInstancia();

        //debe dar el ultimo elemento configurado en clink
        Assert.assertEquals("II", contendor3.getDicExtraterresteNumeroRomano().get("clink"));

        //debe haber anadido el clank en la 2da instancia
        Assert.assertEquals("III", contendor3.getDicExtraterresteNumeroRomano().get("clank"));

        //solo debe haber dos elementos en el hashmap
        Assert.assertEquals(2, contendor3.getDicExtraterresteNumeroRomano().size());
    }


}
