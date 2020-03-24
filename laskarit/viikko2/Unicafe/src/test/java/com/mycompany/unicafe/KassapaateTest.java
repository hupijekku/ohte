/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emu
 */
public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti1;
    Maksukortti kortti2;
    
    public KassapaateTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti1 = new Maksukortti(1000);
        kortti2 = new Maksukortti(100);
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void konstruktoriLuoKassapaatteenOikein() {
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kateisostoToimiiJosRahaRiittaa() {
        assertEquals(760, kassa.syoEdullisesti(1000));
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        assertEquals(100240, kassa.kassassaRahaa());
        
        assertEquals(600, kassa.syoMaukkaasti(1000));
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100640, kassa.kassassaRahaa());
    }
    
    @Test
    public void kateisostoToimiiJosRahaEiRiita() {
        assertEquals(50, kassa.syoEdullisesti(50));
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
        
        assertEquals(300, kassa.syoMaukkaasti(300));
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttiostoToimiiJosKortinRahaRiittaa() {
        assertEquals(true, kassa.syoEdullisesti(kortti1));
        assertEquals(760, kortti1.saldo());
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
        
        assertEquals(true, kassa.syoMaukkaasti(kortti1));
        assertEquals(360, kortti1.saldo());
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void korttiostoToimiiJosKortinRahaEiRiita() {
        assertEquals(false, kassa.syoEdullisesti(kortti2));
        assertEquals(100, kortti2.saldo());
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
        
        assertEquals(false, kassa.syoMaukkaasti(kortti2));
        assertEquals(100, kortti2.saldo());
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKorttiOstoksesta() {
        kassa.syoEdullisesti(kortti1);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kortilleLadattaessaSaldoJaKassanRahatKasvaa() {
        kassa.lataaRahaaKortille(kortti1, 100);
        assertEquals(100100, kassa.kassassaRahaa());
        assertEquals(1100, kortti1.saldo());
    }
    
    @Test
    public void kortilleLadattaessaNegatiivistaSaldoEiMuutu() {
        kassa.lataaRahaaKortille(kortti1, -100);
        assertEquals(100000, kassa.kassassaRahaa());
        assertEquals(1000, kortti1.saldo());
    }
}
