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
        var world = worldGen.generate(WorldType.WORLD, 15, 50, 50, 50, 10);
        assertEquals(true, world instanceof World);
        var dungeon = worldGen.generate(WorldType.DUNGEON, 15, 0, 0, 0, 10);
        assertEquals(true, dungeon instanceof World);
    }
    
    @Test
    public void generatedWorldHasTilemapOfCorrectSize() {
        World world = worldGen.generate(WorldType.WORLD, 15, 50, 50, 50, 10);
        int size = world.getTiles().length;
        assertEquals(15, size);
    }
    
    @Test
    public void generatorCreatesCorrectTypeOfWorld() {
        var world = worldGen.generate(WorldType.WORLD, 50, 50, 50, 50, 10);
        assertEquals(WorldType.WORLD, world.getType());
        var dungeon = worldGen.generate(WorldType.DUNGEON, 50, 0, 0, 0, 10);
        assertEquals(WorldType.DUNGEON, dungeon.getType());
    }
    
}
