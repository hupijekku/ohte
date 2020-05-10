/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.*;
import pwg.dao.Database;
import pwg.domain.World;
import pwg.domain.WorldGenerator;
import pwg.domain.WorldType;

/**
 *
 * @author emu
 */
public class DatabaseTest {
    
    static Database db;
    static World world;
    static World dungeon;
    
    public DatabaseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        db = new Database("jdbc:sqlite:tests.db");
        WorldGenerator wgen = new WorldGenerator();
        world = wgen.generate(WorldType.WORLD, 50, 50, 50, 50, 10);
        dungeon = wgen.generate(WorldType.DUNGEON, 50, 50, 50, 50, 10);
    }
    
    @AfterClass
    public static void tearDownClass() {
        File f = new File("tests.db");
        f.delete(); 
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void worldGetsCorrectlySaved() {
        db.saveWorld(world, "test1");
        db.saveWorld(dungeon, "test2");
        assertEquals(2, db.getWorldNames().size());
        assertEquals(true, db.getWorldByName("test1") instanceof World);
    }
    
    @Test
    public void tilesGetCorrectlySaved() {
        db.saveTiles(world.getTiles(), "test1");
        db.saveTiles(dungeon.getTiles(), "test2");
        assertEquals(2500, db.getTilesByWorldName("test1").size());
        assertEquals(2500, db.getTilesByWorldName("test2").size());
    }
}
