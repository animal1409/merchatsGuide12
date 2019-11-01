package com.animal.merchant.procesamiento;

import com.animal.merchant.modelo.ConfiguracionQuery;
import com.animal.merchant.modelo.TipoQuery;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProcesamientoAplicacionTest {



    @Test
    public void ProbarProcesoAplicacion() {


        //region ConfiguracionInicial

        List<ConfiguracionQuery> lstConfQuery = new ArrayList<ConfiguracionQuery>();
        lstConfQuery.add(new ConfiguracionQuery(TipoQuery.Declarativa, "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$"));
        lstConfQuery.add(new ConfiguracionQuery(TipoQuery.Calculativa, "(.*) is ([0-9]+) ([c|C]redits)$"));
        lstConfQuery.add(new ConfiguracionQuery(TipoQuery.Crediticia, "^how many [C|c]redits is .*?$"));
        lstConfQuery.add(new ConfiguracionQuery(TipoQuery.Cuantitativa, "^how much is .*?$"));

        ProcesadorAplicacion procesadorAplicacion = ProcesadorAplicacion.obtenerInstancia();
        procesadorAplicacion.configurarQueries(lstConfQuery);

        //endregion ConfiguracionInicial

        //region ConfigurarEntradaDatos
        List<String> lstEntradas = new ArrayList<String>();

        lstEntradas.add("glob is I");
        lstEntradas.add("prok is V");
        lstEntradas.add("pish is X");
        lstEntradas.add("tegj is L");
        lstEntradas.add("glob glob Silver is 34 Credits");
        lstEntradas.add("glob prok Gold is 57800 Credits");
        lstEntradas.add("pish pish Iron is 3910 Credits");
        lstEntradas.add("how much is pish tegj glob glob ?");
        lstEntradas.add("how many Credits is glob prok Silver ?");
        lstEntradas.add("how many Credits is glob prok Gold ?");
        lstEntradas.add("how many Credits is glob prok Iron ?");
        lstEntradas.add("how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
        //endregion ConfigurarEntradaDatos


        procesadorAplicacion.ingresarEntradas(lstEntradas);
        procesadorAplicacion.procesarEntradas();

        List<String> lstSalidasResultados = procesadorAplicacion.obtenerSalida();
        List<String> lstSalidasDefinidas = new ArrayList<String>();

        //region configuracionSalidasDefinidas
        lstSalidasDefinidas.add("pish tegj glob glob is 42");
        lstSalidasDefinidas.add("glob prok Silver is 68 Credits");
        lstSalidasDefinidas.add("glob prok Gold is 57800 Credits");
        lstSalidasDefinidas.add("glob prok Iron is 782 Credits");
        lstSalidasDefinidas.add("I have no idea what you are talking about");

        //endregion configuracionSalidasDefinidas

        Assert.assertEquals(lstSalidasDefinidas, lstSalidasResultados);


    }

}
