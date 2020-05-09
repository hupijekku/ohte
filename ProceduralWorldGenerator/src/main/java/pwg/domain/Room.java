/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pwg.domain;

/**
 *
 * @author emu
 */
public class Room {
    public int pivotX;
    public int pivotY;
    public int width;
    public int height;
    public boolean pathY = false;
    public boolean pathX = false;
    
    public Room(int pX, int pY, int w, int h) {
        this.pivotX = pX;
        this.pivotY = pY;
        this.width = w;
        this.height = h;
    }
    
    public boolean roomCollision(Room r) {
        if ((this.pivotX - (this.width / 2) - r.width < r.pivotX 
                && r.pivotX < this.pivotX + (this.width / 2) + r.width)
                && (this.pivotY - (this.height / 2) - r.height < r.pivotY 
                && r.pivotY < this.pivotY + (this.height / 2) + r.height)) {
            return true;
        }
        return false;
    }
}
