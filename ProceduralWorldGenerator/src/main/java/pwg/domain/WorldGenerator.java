package pwg.domain;

/**
 *
 * @author Hupijekku
 */
public class WorldGenerator {
    
    private WorldType type;
    private int size;
    
    public WorldGenerator(WorldType type, int size) {
        this.type = type;
        this.size = size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setType(WorldType type) {
        this.type = type;
    }
    
    public WorldType getType() {
        return this.type;
    }
}
