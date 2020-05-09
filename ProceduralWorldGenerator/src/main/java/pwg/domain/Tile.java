
package pwg.domain;

/**
 *
 * @author Hupijekku
 */
public class Tile {
    private int x;
    private int y;
    private double height;
    private double humidity;
    private double vegetation;
    
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
    
    public Tile(double height, double humidity, double vegetation) {
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
    
    public Tile(double height, double humidity, double vegetation,
            boolean passable, boolean transparent) {
        this.height = height;
        this.humidity = humidity;
        this.vegetation = vegetation;
        this.passable = passable;
        this.transparent = transparent;
    }
    
    public double getHeight() {
        return this.height;
    }
    
    public double getHumidity() {
        return this.humidity;
    }
    
    public double getVegetation() {
        return this.vegetation;
    }
    
    public boolean isPassable() {
        return this.passable;
    }
    
    public void setPassable(boolean pass) {
        this.passable = pass;
    }
    
    public boolean isTransparent() {
        return this.transparent;
    }
    
    public void setTransparent(boolean trans) {
        this.transparent = trans;
    }
    
    public void setHeight(double height) {
        this.height = height;
    }
    
    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }
    
    public void setVegetation(double vegetation) {
        this.vegetation = vegetation;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
}
