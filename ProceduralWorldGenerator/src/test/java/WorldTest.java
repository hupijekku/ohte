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

import pwg.domain.World;
import pwg.domain.WorldGenerator;
import pwg.domain.WorldType;
import pwg.domain.Tile;

/**
 *
 * @author emu
 */
public class WorldTest {
    
    World noParams;
    World noTilemap;
    World params;
    
    public WorldTest() {
        noParams = new World();
        noTilemap = new World(WorldType.DUNGEON, 10);
        Tile[][] tilemap = new Tile[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                tilemap[i][j] = new Tile();
            }
        }
        params = new World(WorldType.TERRAIN, 5, tilemap);
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
    public void noParamConstructorWorks() {
        assertEquals(0, noParams.getTiles().length);
        assertEquals(WorldType.WORLD, noParams.getType());
        assertEquals(0, noParams.getSize());
    }
    
    @Test
    public void getTileAtReturnsATile() {
        assertEquals(true, params.getTileAt(1, 1) instanceof Tile);
    }
    
    @Test
    public void getTileAtReturnsNullWhenOutOfIndex() {
        assertEquals(null, params.getTileAt(6, 6));
        assertEquals(null, params.getTileAt(-1, -1));
        assertEquals(null, params.getTileAt(-1, 2));
        assertEquals(null, params.getTileAt(2, -1));
        assertEquals(null, params.getTileAt(0, 6));
        assertEquals(null, params.getTileAt(6, 0));
    }
    
    @Test
    public void gettersAndSettersCorrectValues() {
        assertEquals(0, noParams.getHumidity());
        assertEquals(0, noParams.getMountainousness());
        assertEquals(0, noParams.getVegetation());
        noParams.setHumidity(10);
        noParams.setMountainousness(10);
        noParams.setVegetation(10);
        assertEquals(10, noParams.getHumidity());
        assertEquals(10, noParams.getMountainousness());
        assertEquals(10, noParams.getVegetation());
    }
}
