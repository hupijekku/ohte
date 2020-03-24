package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void saldoAlussaOnOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void kortinLataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void saldoVaheneeOttaessaKunRahaRiittaa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void saldoEiMuutuOttaessaJosRahaEiRiita() {
        kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void otaRahaaPalauttaaOikeanArvon() {
        assertEquals(true, kortti.otaRahaa(5));
        assertEquals(false, kortti.otaRahaa(100));
    }
    
    @Test
    public void toStringPalauttaaKortinOikeassaMuodossa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
}
