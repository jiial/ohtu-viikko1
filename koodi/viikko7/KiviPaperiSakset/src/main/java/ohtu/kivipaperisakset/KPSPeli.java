/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author ljone
 */
public abstract class KPSPeli {
    
    protected static final Scanner scanner = new Scanner(System.in);
    protected Tekoaly tekoaly; // Vain tekoäly tarvitsee
    protected TekoalyParannettu tekoalyParannettu; // Vain parempi tekoäly tarvitsee
    
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();
        String ekanSiirto = "";
        String tokanSiirto = "";
        
        setTekoaly();
        
        teeSiirrot(ekanSiirto, tokanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            teeSiirrot(ekanSiirto, tokanSiirto);
        }

        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
    
    
    protected abstract void teeSiirrot(String ekanSiirto, String tokanSiirto);
    
    protected abstract void setTekoaly();
    
    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
