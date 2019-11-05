package com.animal.merchant.modelo;

import com.animal.merchant.excepciones.NumeroNoValidoException;
import com.animal.merchant.modelo.query.FactoryGestorQuery;
import com.animal.merchant.modelo.query.IGestorQuery;
import com.animal.merchant.procesamiento.ProcesadorNumeroRomano;
import com.animal.merchant.procesamiento.ProcesadorQuery;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class QueryTest {

    private static List<ConfiguracionQuery> lstConfiguracionQuery;




    @Before
    public void ConfigurarTester() {

        //region ConfiguracionNumeroRomano
        List<NumeroRomano> lstNumeroRomano = new ArrayList<NumeroRomano>();
        lstNumeroRomano.add(new NumeroRomano('I', 1));
        lstNumeroRomano.add(new NumeroRomano('V', 5));
        lstNumeroRomano.add(new NumeroRomano('X', 10));
        lstNumeroRomano.add(new NumeroRomano('L', 50));
        lstNumeroRomano.add(new NumeroRomano('C', 100));
        lstNumeroRomano.add(new NumeroRomano('D', 500));
        lstNumeroRomano.add(new NumeroRomano('M', 1000));
        ProcesadorNumeroRomano.obtenerInstancia().ConfigurarRomanos(lstNumeroRomano);


        //endregion ConfiguracionNumeroRomano

        //region ConfiguracionTipoQuery
        lstConfiguracionQuery = new ArrayList<ConfiguracionQuery>();
        lstConfiguracionQuery.add(new ConfiguracionQuery(TipoQuery.Declarativa, "^([A-Za-z]+) is ([I|V|X|L|C|D|M])$"));
        lstConfiguracionQuery.add(new ConfiguracionQuery(TipoQuery.Calculativa, "(.*) is ([0-9]+) ([c|C]redits)$"));
        lstConfiguracionQuery.add(new ConfiguracionQuery(TipoQuery.Crediticia, "^how many [C|c]redits is .*?$"));
        lstConfiguracionQuery.add(new ConfiguracionQuery(TipoQuery.Cuantitativa, "^how much is .*?$"));

        //endregion ConfiguracionTipoQuery


    }

    @Test
    public void ProbarQueryPorTipo() {
        String queryDeclarativa = "glob is I";
        String queryCalculativa = "glob glob Silver is 34 Credits";
        String queryCuantitativa = "how much is pish tegj glob glob ?";
        String queryCrediticia = "how many Credits is glob prok Silver ?";
        String queryNoValida = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";

        Assert.assertEquals(TipoQuery.Declarativa, obtenerTipoQueryPorQuery(queryDeclarativa));
        Assert.assertEquals(TipoQuery.Calculativa, obtenerTipoQueryPorQuery(queryCalculativa));
        Assert.assertEquals(TipoQuery.Cuantitativa, obtenerTipoQueryPorQuery(queryCuantitativa));
        Assert.assertEquals(TipoQuery.Crediticia, obtenerTipoQueryPorQuery(queryCrediticia));
        Assert.assertEquals(TipoQuery.NoValida, obtenerTipoQueryPorQuery(queryNoValida));
        Assert.assertTrue(TipoQuery.Calculativa != obtenerTipoQueryPorQuery(queryCrediticia));

    }

    private TipoQuery obtenerTipoQueryPorQuery(String entrada) {
        ProcesadorQuery procQuery = ProcesadorQuery.obtenerInstancia();
        procQuery.ConfigurarTipoQuery(lstConfiguracionQuery);
        return procQuery.obtenerTipoQuery(entrada);
    }






    @Test
    public void ProbarDeclarativeQuery() throws NumeroNoValidoException {
        ContenedorDatos.ResetearDatos();
        ProcesadorQuery procQuery = ProcesadorQuery.obtenerInstancia();
        procQuery.ConfigurarTipoQuery(lstConfiguracionQuery);
        TipoQuery tipoQuery = ProcesadorQuery.obtenerInstancia().obtenerTipoQuery("glob is I");
        Assert.assertEquals(TipoQuery.Declarativa, tipoQuery);

        IGestorQuery gestorQuery = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Declarativa);

        gestorQuery.gestionarQuery("glob is I");
        gestorQuery.gestionarQuery("prok is V");
        gestorQuery.gestionarQuery("pish is X");
        gestorQuery.gestionarQuery("tegj is L");

        String txtRomanI = ContenedorDatos.obtenerInstancia().getDicExtraterresteNumeroRomano().get("glob");
        String txtRomanV = ContenedorDatos.obtenerInstancia().getDicExtraterresteNumeroRomano().get("prok");
        String txtRomanX = ContenedorDatos.obtenerInstancia().getDicExtraterresteNumeroRomano().get("pish");

        Assert.assertEquals("I", txtRomanI);
        Assert.assertEquals("V", txtRomanV);
        Assert.assertEquals("X", txtRomanX);

    }

    @Test
    public void ProbarCalculativaQuery() throws NumeroNoValidoException {


        //encerar datos
        ContenedorDatos.ResetearDatos();

        //configugrar el procesador de queries
        ProcesadorQuery procQuery = ProcesadorQuery.obtenerInstancia();
        procQuery.ConfigurarTipoQuery(lstConfiguracionQuery);

        //obtenermos el proce
        /*TipoQuery tipoQuery = ProcesadorQuery.obtenerInstancia().obtenerTipoQuery("glob is I");
        Assert.assertEquals(TipoQuery.Declarativa, tipoQuery);*/

        //obtenermos un gestor de query declarativa, se obtendrá automaticamente luego por el tipo de query
        IGestorQuery gestorQueryDeclarativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Declarativa);

        gestorQueryDeclarativa.gestionarQuery("glob is I");
        gestorQueryDeclarativa.gestionarQuery("prok is V");
        gestorQueryDeclarativa.gestionarQuery("pish is X");
        gestorQueryDeclarativa.gestionarQuery("tegj is L");

        //obtenermos un gestor de Query calculativa para ingresar las variables de tipo clavulativas
        IGestorQuery gestorQueryCalculativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Calculativa);

        String querySilver = "glob glob Silver is 34 Credits";
        String queryGold = "glob prok Gold is 57800 Credits";
        String queryIron = "pish pish Iron is 3910 Credits";
        gestorQueryCalculativa.gestionarQuery(querySilver);
        gestorQueryCalculativa.gestionarQuery(queryGold);
        gestorQueryCalculativa.gestionarQuery(queryIron);


        double valorMetalSilver = ContenedorDatos.obtenerInstancia().getDicValorMetal().get("Silver");
        double valorMetalGold = ContenedorDatos.obtenerInstancia().getDicValorMetal().get("Gold");
        double valorMetalIron = ContenedorDatos.obtenerInstancia().getDicValorMetal().get("Iron");
        Assert.assertEquals(17.00, valorMetalSilver, 0);
        Assert.assertEquals(14450, valorMetalGold, 0);
        Assert.assertEquals(195.5, valorMetalIron, 0);


    }

    @Test
    public void ProbarCualitativaQuery() throws NumeroNoValidoException {

        //region PasosPrevios

        //encerar datos
        ContenedorDatos.ResetearDatos();

        //configugrar el procesador de queries
        ProcesadorQuery procQuery = ProcesadorQuery.obtenerInstancia();
        procQuery.ConfigurarTipoQuery(lstConfiguracionQuery);


        //obtenermos un gestor de query declarativa, se obtendrá automaticamente luego por el tipo de query
        IGestorQuery gestorQueryDeclarativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Declarativa);

        gestorQueryDeclarativa.gestionarQuery("glob is I");
        gestorQueryDeclarativa.gestionarQuery("prok is V");
        gestorQueryDeclarativa.gestionarQuery("pish is X");
        gestorQueryDeclarativa.gestionarQuery("tegj is L");

        //obtenermos un gestor de Query calculativa para ingresar las variables de tipo clavulativas
        IGestorQuery gestorQueryCalculativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Calculativa);

        String querySilver = "glob glob Silver is 34 Credits";
        String queryGold = "glob prok Gold is 57800 Credits";
        String queryIron = "pish pish Iron is 3910 Credits";
        gestorQueryCalculativa.gestionarQuery(querySilver);
        gestorQueryCalculativa.gestionarQuery(queryGold);
        gestorQueryCalculativa.gestionarQuery(queryIron);

        //#endregion PasosPrevios

        IGestorQuery gestorCuantitativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Cuantitativa);
        String respuestaCuanto = gestorCuantitativa.gestionarQuery("how much is pish tegj glob glob ?");
        //XLII
        Assert.assertEquals("pish tegj glob glob is 42", respuestaCuanto);

    }

    @Test
    public void ProbarCrediticiaQuery() throws NumeroNoValidoException {
        //region PasosPrevios

        //encerar datos
        ContenedorDatos.ResetearDatos();

        //configugrar el procesador de queries
        ProcesadorQuery procQuery = ProcesadorQuery.obtenerInstancia();
        procQuery.ConfigurarTipoQuery(lstConfiguracionQuery);


        //obtenermos un gestor de query declarativa, se obtendrá automaticamente luego por el tipo de query
        IGestorQuery gestorQueryDeclarativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Declarativa);

        gestorQueryDeclarativa.gestionarQuery("glob is I");
        gestorQueryDeclarativa.gestionarQuery("prok is V");
        gestorQueryDeclarativa.gestionarQuery("pish is X");
        gestorQueryDeclarativa.gestionarQuery("tegj is L");

        //obtenermos un gestor de Query calculativa para ingresar las variables de tipo clavulativas
        IGestorQuery gestorQueryCalculativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Calculativa);

        String querySilver = "glob glob Silver is 34 Credits";
        String queryGold = "glob prok Gold is 57800 Credits";
        String queryIron = "pish pish Iron is 3910 Credits";
        gestorQueryCalculativa.gestionarQuery(querySilver);
        gestorQueryCalculativa.gestionarQuery(queryGold);
        gestorQueryCalculativa.gestionarQuery(queryIron);


        IGestorQuery gestorCuantitativa = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Cuantitativa);
        String respuestaCuanto = gestorCuantitativa.gestionarQuery("how much is pish tegj glob glob");
        //XLII


        //#endregion PasosPrevios

        String entradaCrediticia = "how many Credits is glob prok Silver ?";
        TipoQuery tipoQueryCrediticia = procQuery.obtenerTipoQuery(entradaCrediticia);

        Assert.assertEquals(TipoQuery.Crediticia, tipoQueryCrediticia);

        IGestorQuery gestorQueryCrediticia = new FactoryGestorQuery().obtenerGestorQuery(TipoQuery.Crediticia);
        String salidaCrediticia = gestorQueryCrediticia.gestionarQuery(entradaCrediticia);
        Assert.assertEquals("glob prok Silver is 68 Credits", salidaCrediticia);

    }

    @Test
    public void ProbarNoValidaQuery() throws NumeroNoValidoException {
        //region PasosPrevios

        //encerar datos
        ContenedorDatos.ResetearDatos();

        //configugrar el procesador de queries
        ProcesadorQuery procQuery = ProcesadorQuery.obtenerInstancia();
        procQuery.ConfigurarTipoQuery(lstConfiguracionQuery);

        //confiugracmos la entrada no valida
        String entradaNoValida = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
        TipoQuery tipoQueryNoValida = procQuery.obtenerTipoQuery(entradaNoValida);

        //gestiona el ingreso del query
        IGestorQuery gestorQueryNoValida = new FactoryGestorQuery().obtenerGestorQuery(tipoQueryNoValida);
        String resultado = gestorQueryNoValida.gestionarQuery(entradaNoValida);
        Assert.assertEquals("I have no idea what you are talking about", resultado);

    }


}
