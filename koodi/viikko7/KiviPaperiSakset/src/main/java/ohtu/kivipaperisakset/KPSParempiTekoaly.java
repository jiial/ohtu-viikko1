package ohtu.kivipaperisakset;

import java.util.Scanner;


// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends KPSPeli {

    @Override
    protected void teeSiirrot(String ekanSiirto, String tokanSiirto) {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();

        tokanSiirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + tokanSiirto);
        tekoaly.asetaSiirto(ekanSiirto);
    }

    @Override
    protected void setTekoaly() {
        this.tekoalyParannettu = new TekoalyParannettu(20);
    }

}
