package ohtu.kivipaperisakset;


public class KPSPelaajaVsPelaaja extends KPSPeli {

    @Override
    protected void teeSiirrot(String ekanSiirto, String tokanSiirto) {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        ekanSiirto = scanner.nextLine();
        System.out.print("Toisen pelaajan siirto: ");
        tokanSiirto = scanner.nextLine();
    }

    @Override
    protected void setTekoaly() {
    }

}