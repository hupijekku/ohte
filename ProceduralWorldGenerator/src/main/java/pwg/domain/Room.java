/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwg.domain;

/**
 * Room struct.
 * Keeps data for rooms in dungeon-type worlds. 
 * Provides methods for creating doors and checking for collision.
 * @author emu
 */
public class Room {
    
    public int pivotX;
    public int pivotY;
    public int width;
    public int height;
    
    /**
     * Constructor for Room.
     * @param pX    Pivot X-coordinate
     * @param pY    Pivot Y-coordinate
     * @param w     Width of the room
     * @param h     Height of the room
     */
    public Room(int pX, int pY, int w, int h) {
        this.pivotX = pX;
        this.pivotY = pY;
        this.width = w;
        this.height = h;
    }
    
    /**
     * Checks whether two rooms are on top of each other
     * @param r     A room to check with.
     * @return      True if the rooms collide, False if not.
     */
    public boolean roomCollision(Room r) {
        if ((this.pivotX - (this.width / 2) - r.width < r.pivotX 
                && r.pivotX < this.pivotX + (this.width / 2) + r.width)
                && (this.pivotY - (this.height / 2) - r.height < r.pivotY 
                && r.pivotY < this.pivotY + (this.height / 2) + r.height)) {
            return true;
        }
        return false;
    }
    
    /**
     * Checks if a given point is within the room.
     * @param x     X-coordinate of a point.
     * @param y     Y-coordinate of a point.
     * @return      True if the given point is in this room, False if not.
     */
    public boolean isInRoom(int x, int y) {
        if (x < this.pivotX + (this.width / 2) && x > this.pivotX - (this.width / 2)
                && y < this.pivotY + (this.height / 2) && y > this.pivotY - (this.height / 2)) {
            return true;
        }
        return false;
    }
    
    /**
     * Creates doors at the entrances of the room, if a valid path is found.
     * @param tilemap   The tilemap of the dungeon.
     */
    public void generateDoors(Tile[][] tilemap) {
        for (int i = this.pivotX - (this.width / 2); i <= this.pivotX + (this.width / 2); i++) {
            if (tilemap[i][this.pivotY].isPassable()
                    && !tilemap[i][this.pivotY + 1].isPassable()
                    && !tilemap[i][this.pivotY - 1].isPassable()) {
                tilemap[i][this.pivotY].setTransparent(false);
            }
        }
        
        for (int j = this.pivotY - (this.height / 2); j <= this.pivotY + (this.height / 2); j++) {
            if (tilemap[this.pivotX][j].isPassable()
                    && !tilemap[this.pivotX + 1][j].isPassable()
                    && !tilemap[this.pivotX - 1][j].isPassable()) {
                tilemap[this.pivotX][j].setTransparent(false);
            }
        }
    }
}
