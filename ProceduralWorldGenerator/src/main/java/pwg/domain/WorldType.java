package pwg.domain;

/**
 *
 * @author emu
 */
public enum WorldType {
    
    WORLD(0), DUNGEON(2);
    
    private int id;
    
    WorldType(int id) {
        this.id = id;
    }
    
    public int getId() {
        return this.id;
    }
    
}
