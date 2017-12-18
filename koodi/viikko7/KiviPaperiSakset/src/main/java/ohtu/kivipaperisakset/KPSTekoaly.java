package ohtu.kivipaperisakset;


public class KPSTekoaly extends KPSPeli {

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
        this.tekoaly = new Tekoaly();
    }
}
