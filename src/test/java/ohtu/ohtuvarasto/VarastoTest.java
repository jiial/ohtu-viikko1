package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringToimii() {
        String kissa = varasto.toString();
        assertEquals("aldo = 0.0, vielä tilaa 10.0", kissa);
    }

    @Test
    public void alkuSaldollaLuominenToimii() {
        Varasto v = new Varasto(20, 10);
        assertEquals(20, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alkuSaldollaLuominenToimii2() {
        Varasto v = new Varasto(20, 10);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void virheellinenTilavuus() {
        Varasto v = new Varasto(-2);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);

    }
    
    @Test
    public void virheellinenTilavuus2() {
        Varasto v = new Varasto(-2, 5);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);

    }
    
    @Test
    public void virheellinenAlkuSaldo() {
        Varasto v = new Varasto(12, -8);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void virheellinenAlkuSaldo2() {
        Varasto v = new Varasto(10, 20);
        assertEquals(10, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void otaVarastostaToimii() {
        varasto.lisaaVarastoon(5);
        double j = varasto.otaVarastosta(-3);
        assertEquals(0, j, vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaSaldoToimii() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-3);
        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaToimii2() {
        varasto.lisaaVarastoon(5);
        double j = varasto.otaVarastosta(10);
        assertEquals(5, j, vertailuTarkkuus);
    }
    
    @Test
    public void otaVarastostaSaldoToimii2() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(10);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonToimii() {
        varasto.lisaaVarastoon(20);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaaVarastoonToimii2() {
        varasto.lisaaVarastoon(-20);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
}
