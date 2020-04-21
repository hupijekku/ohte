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

import pwg.domain.WorldGenerator;
import pwg.domain.World;
import pwg.domain.WorldType;

/**
 *
 * @author emu
 */
public class WorldGeneratorTest {
    
    WorldGenerator worldGen;
    
    public WorldGeneratorTest() {
        worldGen = new WorldGenerator();
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
    public void generationReturnsWorld() {
        var world = worldGen.generate(WorldType.WORLD, 1, 50, 50, 50);
        assertEquals(true, world instanceof World);
    }
    
    @Test
    public void generatedWorldHasTilemapOfCorrectSize() {
        World world = worldGen.generate(WorldType.WORLD, 10, 50, 50, 50);
        int size = world.getTiles().length;
        assertEquals(10, size);
    }
    
    
}
