package pwg.domain;

/**
 * Contains the data and structure of a world to be generated.
 * @author Hupijekku
 */
public class World {
    private WorldType type;
    private int size;
    private Tile[][] tilemap;
    private int humidity = 0;
    private int mountainousness = 0;
    private int vegetation = 0;
    
    
    /**
     * Empty constructor for a World-object. 
     * Sets the default world type "World" with size 0
     * and an empty tile-map
     */
    public World() {
        this.type = WorldType.WORLD;
        this.size = 0;
        this.tilemap = new Tile[0][0];
    }
    
    /**
     * Constructor that creates a World-object with given type and size.
     * @param type  Type of the world (Enum WorldType)
     * @param size  Size of the world (Length of a side of square)
     */
    public World(WorldType type, int size) {
        this.type = type;
        this.size = size;
        this.tilemap = new Tile[size][size];
    }
    /**
     * Constructor that creates a World-object with a pre-initialized tile-map
     * @param type  Type of the world (Enum WorldType)
     * @param size  Size of the world (Length of a side of square)
     * @param tilemap   2 dimensional square array of Tile-objects
     */
    public World(WorldType type, int size, Tile[][] tilemap) {
        this.type = type;
        this.size = size;
        this.tilemap = tilemap;
    }
    /**
     * Returns the 2 dimensional tile-map array of this world.
     * @return 2 dimensional square array of tiles.
     */
    public Tile[][] getTiles() {
        return this.tilemap;
    }
    
    public void setTiles(Tile[][] tilemap) {
        this.tilemap = tilemap;
    }
    
    /**
     * Returns the Tile-object with the given coordinates from the World's tile-map.
     * @param x The x-coordinate of the tile.
     * @param y The y-coordinate of the tile.
     * @return Tile from the given coordinates.
     */
    public Tile getTileAt(int x, int y) {
        if (x < this.size && y < this.size && x > 0 && y > 0) {
            return this.tilemap[x][y];
        } else {
            return null;
        }
    }
    /**
     * Returns the size of the world. Side length of the square shaped world.
     * @return World size
     */
    public int getSize() {
        return this.size;
    }
    /**
     * Returns the type of the world. WorldType enum.
     * @return WorldType enum
     */
    public WorldType getType() {
        return this.type;
    }
    /**
     * Setter for the private field humidity. Changes the amount of water
     * present in a World when generated.
     * @param hum Humidity of the world.
     */
    public void setHumidity(int hum) {
        this.humidity = hum;
    }
    /**
     * Setter for the private field mountainousness. Changes the average height
     * of the world and the amount of large mountains.
     * @param mount Average height and mountainousness of the world.
     */
    public void setMountainousness(int mount) {
        this.mountainousness = mount;
    }
    /**
     * Setter for the private field vegetation. Changes the amount of plants and 
     * flora in a world.
     * @param veg Vegetation and flora of the world.
     */
    public void setVegetation(int veg) {
        this.vegetation = veg;
    }
    /**
     * Getter for the private field humidity
     * @return The current world humidity.
     */
    public int getHumidity() {
        return this.humidity;
    }
    /**
     * Getter for the private field mountainousness
     * @return The height and mountainousness modifier.
     */
    public int getMountainousness() {
        return this.mountainousness;
    }
    /**
     * Getter for the private field vegetation.
     * @return The world vegetation modifier.
     */
    public int getVegetation() {
        return this.vegetation;
    }
}
