/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwg.dao;

import java.sql.*;
import java.util.ArrayList;
import pwg.domain.Tile;
import pwg.domain.World;
import pwg.domain.WorldType;

/**
 *
 * @author emu
 */
public class Database {
    
    private String file;
    private Connection db;
    
    public Database(String file) {
        try {
            this.file = file;
            this.db = DriverManager.getConnection(file);
            this.db.setAutoCommit(false);
            Statement s = db.createStatement();
            s.execute("PRAGMA foreign_keys = ON");
            s.execute("CREATE TABLE world (id INTEGER PRIMARY KEY, name TEXT, type TEXT, size INTEGER)");
            s.execute("CREATE TABLE tile (id INTEGER PRIMARY KEY, name TEXT, x INTEGER, y INTEGER, height REAL, humidity REAL, vegetation REAL, passable INTEGER, transparent INTEGER)");
            
        } catch (SQLException ex) {
            //Most likely table already exists
            System.out.println(ex);
        }
    }
    
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(file);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    public World getWorldByName(String name) {
        try {
            PreparedStatement ps = db.prepareStatement("SELECT type, size FROM world WHERE name=?");
            ps.setString(1, name);
            
            ResultSet results = ps.executeQuery();
            if (results.next()) {
                WorldType type;
                if (results.getString("type").equals("WORLD")) {
                    type = WorldType.WORLD;
                } else {
                    type = WorldType.DUNGEON;
                }
                int size = results.getInt("size");
                World world = new World(type, size);
                return world;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
    
    public ArrayList<String> getWorldNames() {
        ArrayList<String> names = new ArrayList();
        try {    
            Connection c = getConnection();
            Statement s = c.createStatement();
            ResultSet rs = s.executeQuery("SELECT name FROM world");
            while (rs.next()) {
                names.add(rs.getString("name"));
            }
        } catch (SQLException ex) {
            return null;
        }
        return names;
    }
    
    public ArrayList<Tile> getTilesByWorldName(String name) {
        try {
            PreparedStatement ps = db.prepareStatement("SELECT x, y, height, humidity, vegetation, passable, transparent FROM tile WHERE name=?");
            ps.setString(1, name);
            ArrayList<Tile> tiles = new ArrayList();
            ResultSet results = ps.executeQuery();
            while (results.next()) {
                Tile t = new Tile(results.getDouble("height"), results.getDouble("humidity")
                        , results.getDouble("vegetation"), results.getBoolean("passable"), results.getBoolean("transparent"));
                t.setX(results.getInt("x"));
                t.setY(results.getInt("y"));
                tiles.add(t);
            }
            return tiles;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }
    
    public void saveTiles(Tile[][] tilemap, String worldName) {
        try {
            for (int i = 0; i < tilemap.length; i++) {
                for (int j = 0; j < tilemap.length; j++) {
                    PreparedStatement ps = db.prepareStatement("INSERT INTO tile(x, y, name, height, humidity, vegetation, passable, transparent) VALUES (?,?,?,?,?,?,?,?)");
                    ps.setInt(1, i);
                    ps.setInt(2, j);
                    ps.setString(3, worldName);
                    ps.setDouble(4, tilemap[i][j].getHeight());
                    ps.setDouble(5, tilemap[i][j].getHumidity());
                    ps.setDouble(6, tilemap[i][j].getVegetation());
                    ps.setBoolean(7, tilemap[i][j].isPassable());
                    ps.setBoolean(8, tilemap[i][j].isTransparent());
                    ps.executeUpdate();
                }
            }
            db.commit();
        } catch (SQLException ex) {
        }
    }
    
    public boolean saveWorld(World world, String name) {
        String type = "";
        if (world.getType() == WorldType.WORLD) {
            type = "WORLD";
        } else {
            type = "DUNGEON";
        }
        try {
            PreparedStatement ps = db.prepareStatement("INSERT INTO world(name, type, size) VALUES (?,?,?)");
            ps.setString(1, name);
            ps.setString(2, type);
            ps.setInt(3, world.getSize());           
            ps.executeUpdate();
            db.commit();
        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
}
