package com.animal.merchant.aplicacion;

import com.animal.merchant.modelo.ConfiguracionQuery;
import com.animal.merchant.modelo.NumeroRomano;
import com.animal.merchant.modelo.TipoQuery;
import com.animal.merchant.procesamiento.ProcesadorAplicacion;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;

import java.util.ArrayList;
import java.util.List;

public class Aplicacion {

    public static void main(String[] args)
    {
        EjecutarProcesamiento();
    }


    private static void EjecutarProcesamiento()
    {
        //region ConfiguracionInicial


        List<NumeroRomano> lstNumeroRomano = new ArrayList<NumeroRomano>();
        lstNumeroRomano.add(new NumeroRomano('I', 1));
        lstNumeroRomano.add(new NumeroRomano('V', 5));
        lstNumeroRomano.add(new NumeroRomano('X', 10));
        lstNumeroRomano.add(new NumeroRomano('L', 50));
        lstNumeroRomano.add(new NumeroRomano('C', 100));
        lstNumeroRomano.add(new NumeroRomano('D', 500));
        lstNumeroRomano.add(new NumeroRomano('M', 1000));
        ProcesadorNumeroRomano.obtenerInstancia().ConfigurarRomanos(lstNumeroRomano);



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

        System.out.println("Ingreso de las siguientes lines de texto");

        lstEntradas.forEach(e->System.out.println(e));
        procesadorAplicacion.ingresarEntradas(lstEntradas);
        procesadorAplicacion.procesarEntradas();
        List<String> lstSalidasResultados = procesadorAplicacion.obtenerSalida();

        System.out.println("Produce la siguiente salida");
        lstSalidasResultados.forEach(r->System.out.println(r));


    }

}
