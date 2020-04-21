/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pwg.domain.Tile;

/**
 *
 * @author emu
 */
public class TileTest {
    
    public TileTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void emptyConstructorAssignsZeros() {
        Tile tile = new Tile();
        assertEquals(0, tile.getHeight(), 0.001);
        assertEquals(0, tile.getHumidity(), 0.001);
        assertEquals(0, tile.getVegetation(), 0.001);
        assertEquals(false, tile.isPassable());
        assertEquals(false, tile.isTransparent());
    }
    
    @Test
    public void constructorsAssignValues() {
        Tile tile = new Tile(0.5d, 0.5d, 0.5d);
        assertEquals(0.5d, tile.getHeight(), 0.001);
        assertEquals(0.5d, tile.getHumidity(), 0.001);
        assertEquals(0.5d, tile.getVegetation(), 0.001);
        assertEquals(false, tile.isPassable());
        assertEquals(false, tile.isTransparent());
        tile = new Tile(0.5d, 0.5d, 0.5d, true, true);
        assertEquals(0.5d, tile.getHeight(), 0.001);
        assertEquals(0.5d, tile.getHumidity(), 0.001);
        assertEquals(0.5d, tile.getVegetation(), 0.001);
        assertEquals(true, tile.isPassable());
        assertEquals(true, tile.isTransparent());
    }
    
    @Test
    public void settersWorkAsExpected() {
        Tile tile = new Tile(0.1d, 0.1d, 0.1d);
        tile.setHeight(0.5d);
        tile.setHumidity(0.5d);
        tile.setVegetation(0.5d);
        assertEquals(0.5d, tile.getHeight(), 0.001);
        assertEquals(0.5d, tile.getHumidity(), 0.001);
        assertEquals(0.5d, tile.getVegetation(), 0.001);
    }
}
