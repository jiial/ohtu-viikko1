/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ljone
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        @Override
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }
    
    @Test
    public void searchToimii() {
        Player p = stats.search("Esa");
        assertNull(p);
    }
    
    @Test
    public void searchToimii2() {
        Player p = stats.search("Kurri");
        assertEquals("Kurri", p.getName());
    }
    
    @Test
    public void teamToimii() {
        List<Player> l = stats.team("PIT");
        assertEquals("Lemieux", l.get(0).getName());
    }
    
    @Test
    public void teamToimii2() {
        List<Player> l = stats.team("PITtd");
        assertEquals(0, l.size());
    }
    
    @Test
    public void topScorersToimii() {
        List<Player> l = stats.topScorers(1);
        assertEquals(2, l.size());
    }
    
    @Test
    public void topScorersToimii2() {
        List<Player> l = stats.topScorers(-41);
        assertEquals(0, l.size());
    }
    
    @Test
    public void topScoresToimii3() {
        List<Player> l = stats.topScorers(2);
        assertEquals("Lemieux", l.get(1).getName());
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
