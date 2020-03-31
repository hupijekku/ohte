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
public enum WorldType {
    
    WORLD(0), TERRAIN(1), DUNGEON(2);
    
    private int id;
    
    WorldType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
}
