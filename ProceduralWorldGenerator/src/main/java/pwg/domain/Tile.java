
package pwg.domain;

/**
 *
 * @author Hupijekku
 */
public class Tile {
    private float height;
    private float humidity;
    private float vegetation;
    
    private boolean passable;
    /* Transparency has no effect within this application, but can be useful
       when exporting for something that has FOV/FOW-features. */
    private boolean transparent;
    
    public Tile() {
        this.height = 0;
        this.humidity = 0;
        this.vegetation = 0;
        this.passable = false;
        this.transparent = false;
    }
    
    public Tile(float height, float humidity, float vegetation) {
        this.height = height;
        this.humidity = humidity;
        this.vegetation = vegetation;
        this.passable = false;
        this.transparent = false;
    }
    
    public Tile(boolean passable, boolean transparent) {
        this.height = 0;
        this.humidity = 0;
        this.vegetation = 0;
        this.passable = passable;
        this.transparent = transparent;
    }
    
    public Tile(float height, float humidity, float vegetation,
            boolean passable, boolean transparent) {
        this.height = height;
        this.humidity = humidity;
        this.vegetation = vegetation;
        this.passable = passable;
        this.transparent = transparent;
    }
    
    public float getHeight() {
        return this.height;
    }
    
    public float getHumidity() {
        return this.humidity;
    }
    
    public float getVegetation() {
        return this.vegetation;
    }
    
    public boolean isPassable() {
        return this.passable;
    }
    
    public boolean isTransparent() {
        return this.transparent;
    }
}
