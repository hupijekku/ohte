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
        params = new World(WorldType.TERRAIN, 10, new Tile[5][5]);
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
}
