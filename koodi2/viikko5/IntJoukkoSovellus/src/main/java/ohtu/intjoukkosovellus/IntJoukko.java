package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public void alustaIntJoukko() {
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
    }

    public IntJoukko() {
        ljono = new int[KAPASITEETTI];
        alustaIntJoukko();
        this.kasvatuskoko = OLETUSKASVATUS;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti < 0) {
            return;
        }
        ljono = new int[kapasiteetti];
        alustaIntJoukko();
        this.kasvatuskoko = OLETUSKASVATUS;

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti ei voi olla negatiivinen");//taulukon kapasiteetti oltava ainakin 0
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Taulukon kasvatuskoko tulee olla positiivnen");//muuten taulukko pienenee
        }
        ljono = new int[kapasiteetti];
        alustaIntJoukko();
        this.kasvatuskoko = kasvatuskoko;

    }

    public void kasvataTaulukkoa() {
        int[] vanhaTaulukko = ljono;
        ljono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(vanhaTaulukko, ljono);
    }

    public boolean lisaa(int luku) {

        if (!kuuluuJoukkoon(luku)) {
            ljono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % ljono.length == 0) {
                kasvataTaulukkoa();
            }
            return true;
        }
        return false;
    }

    public boolean kuuluuJoukkoon(int luku) {
        int onJoukossa = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                onJoukossa++;
            }
        }
        if (onJoukossa > 0) {
            return true;
        }
        return false;
    }

    public int etsiPoistettava(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) { //luku löytyy tuosta kohdasta
                ljono[i] = 0;
                return i;
            }
        }
        return -1;
    }

    public void korjaaTaulukko(int indeksi) {
        int apu;
        for (int j = indeksi; j < alkioidenLkm - 1; j++) {
            apu = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    public boolean poista(int luku) {
        int indeksi = etsiPoistettava(luku);
        if (indeksi != -1) {
            korjaaTaulukko(indeksi);
            return true;
        }
        return false;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else {
            String tuotos = "{";
            for (int i = 0; i < alkioidenLkm - 1; i++) {
                tuotos += ljono[i];
                tuotos += ", ";
            }
            tuotos += ljono[alkioidenLkm - 1];
            tuotos += "}";
            return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
    
    public static IntJoukko joukkoOperaationAlustus(IntJoukko a, IntJoukko b, String tyyppi) {
        IntJoukko joukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        if (tyyppi.equals("yhdiste")) {
            joukko = yhdisteOperaatio(joukko, aTaulu, bTaulu);
        } else if (tyyppi.equals("leikkaus")) {
            joukko = leikkausOperaatio(joukko, aTaulu, bTaulu);
        } else if (tyyppi.equals("erotus")) {
            joukko = erotusOperaatio(joukko, aTaulu, bTaulu);
        }
        return joukko;
    }
    
    public static IntJoukko yhdisteOperaatio(IntJoukko yhdiste, int[] aTaulu, int[] bTaulu) {
        for (int i = 0; i < aTaulu.length; i++) {
            yhdiste.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdiste.lisaa(bTaulu[i]);
        }
        return yhdiste;
    }
    
    public static IntJoukko leikkausOperaatio(IntJoukko leikkaus, int[] aTaulu, int[] bTaulu) {
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkaus.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkaus;
    }
    
    public static IntJoukko erotusOperaatio(IntJoukko erotus, int[] aTaulu, int[] bTaulu) {
        for (int i = 0; i < aTaulu.length; i++) {
            erotus.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotus.poista(i);
        }
        return erotus;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdiste = joukkoOperaationAlustus(a, b, "yhdiste");
        return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkaus = joukkoOperaationAlustus(a, b, "leikkaus");
        return leikkaus;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko erotus = joukkoOperaationAlustus(a, b, "erotus");
        return erotus;
    }

}
