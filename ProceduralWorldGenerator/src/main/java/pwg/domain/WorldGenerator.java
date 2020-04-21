package pwg.domain;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.interpolation.InterpolationType;
import java.util.Random;

/**
 *
 * @author Hupijekku
 */
public class WorldGenerator {
    
    
    private int seed = 0;
    
    
    public WorldGenerator() {
        Random random = new Random();
        this.seed = random.nextInt();
    }
    
    /**
    * Generates a world of the given type and size
    *
    * @param    type                type of the world
    * @param    size                length of a side for the generated square shaped world
    * @param    humidity            humidity/wetness of the world, amount and size of bodies of water
    * @param    mountainousness     amount of mountains and average height
    * @param    vegetation          amount of vegetation
    * 
    *@return    returns a World object that contains the generated world data.
    */
  
    public World generate(WorldType type, int size, int humidity, int mountainousness, int vegetation) {
        Tile[][] tilemap = new Tile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // WorldType = World -> Don't care about pathfinding/fow
                tilemap[x][y] = new Tile(true, true);
            }
        }
        World world = new World(type, size, tilemap);
        setMountainousness(world, mountainousness);
        setHumidity(world, humidity);
        setVegetation(world, vegetation);
        world.setHumidity(humidity);
        world.setMountainousness(mountainousness);
        world.setVegetation(vegetation);
        return world;
    }
    
    private void setMountainousness(World world, int mount) {    
        Tile[][] tilemap = world.getTiles();
        for (int x = 0; x < tilemap.length; x++) {
            for (int y = 0; y < tilemap.length; y++) {
                double noise = getMountainNoise(x, y);
                noise += Math.abs(noise)*(mount/100f) + (mount/100f);
                
                noise = Math.min(1, noise);
                tilemap[x][y].setHeight(noise);
            }
        }
    }
    
    private void setHumidity(World world, int humidity) {
        Tile[][] tilemap = world.getTiles();
        for (int x = 0; x < tilemap.length; x++) {
            for (int y = 0; y < tilemap.length; y++) {
                double noise = getHumidityNoise(x, y);
                noise += (humidity/100f);
                noise *= (humidity/100f);
                noise = Math.min(1, Math.max(0, noise));
                tilemap[x][y].setHumidity(noise);
            }
        }
    }
    
    private void setVegetation(World world, int vegetation) {
        Tile[][] tilemap = world.getTiles();
        for (int x = 0; x < tilemap.length; x++) {
            for (int y = 0; y < tilemap.length; y++) {
                double noise = getVegetationNoise(x, y);
                noise += Math.abs(noise)*(vegetation/100f);
                noise = Math.min(1, noise);
                tilemap[x][y].setVegetation(noise);
            }
        }
    }
    
    
    
    private double getMountainNoise(int x, int y) {
       int scale = 20;
        JNoise perlinNoise = JNoise.newBuilder()
                .perlin().setInterpolationType(InterpolationType.COSINE).setSeed(this.seed + 1).build();
        
        return perlinNoise.getNoise((x*1f)/scale, (y*1f)/scale);
    }
    
    private double getHumidityNoise(int x, int y) { 
       JNoise perlinNoise = JNoise.newBuilder()
                .perlin().setInterpolationType(InterpolationType.COSINE).setSeed(this.seed + 2).build();
        
        return perlinNoise.getNoise(x/30f, y/30f);
    }
    
    private double getVegetationNoise(int x, int y) { 
       JNoise perlinNoise = JNoise.newBuilder()
                .perlin().setInterpolationType(InterpolationType.COSINE).setSeed(this.seed + 3).build();
        
        return perlinNoise.getNoise(x/10, y/10);
    }
}
