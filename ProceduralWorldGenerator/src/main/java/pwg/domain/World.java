package pwg.domain;

/**
 *
 * @author Hupijekku
 */
public class World {
    private WorldType type;
    private int size;
    private Tile[][] tilemap;
    
    public World() {
        this.type = WorldType.WORLD;
        this.size = 0;
        this.tilemap = new Tile[0][0];
    }
    
    public World(WorldType type, int size) {
        this.type = type;
        this.size = size;
        this.tilemap = new Tile[size][size];
    }
    
    public World(WorldType type, int size, Tile[][] tilemap) {
        this.type = type;
        this.size = size;
        this.tilemap = tilemap;
    }
    
    public Tile[][] getTiles() {
        return this.tilemap;
    }
    
    public Tile getTileAt(int x, int y) {
        if(x < this.size && y < this.size) {
            return this.tilemap[x][y];
        } else {
            return null;
        }
    }
    
    public int getSize() {
        return this.size;
    }
    
    public WorldType getType() {
        return this.type;
    }
}
