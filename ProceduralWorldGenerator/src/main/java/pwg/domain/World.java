package pwg.domain;

/**
 *
 * @author Hupijekku
 */
public class World {
    private WorldType type;
    private int size;
    private Tile[][] tilemap;
    private int humidity = 0;
    private int mountainousness = 0;
    private int vegetation = 0;
    
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
        if (x < this.size && y < this.size && x > 0 && y > 0) {
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
    
    public void setHumidity(int hum) {
        this.humidity = hum;
    }
    
    public void setMountainousness(int mount) {
        this.mountainousness = mount;
    }
    
    public void setVegetation(int veg) {
        this.vegetation = veg;
    }
    
    public int getHumidity() {
        return this.humidity;
    }
    
    public int getMountainousness() {
        return this.mountainousness;
    }
    
    public int getVegetation() {
        return this.vegetation;
    }
}
