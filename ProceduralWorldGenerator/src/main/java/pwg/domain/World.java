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
}
