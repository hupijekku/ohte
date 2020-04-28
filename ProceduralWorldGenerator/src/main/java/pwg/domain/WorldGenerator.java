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
    
    /**
     * Empty constructor for the WorldGenerator.
     * Sets a random seed value for this world.
     */
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
    
    /**
     * Creates a 2 dimensional height-map from noise and sets the given World's
     * tile heights based on that and the given mountainousness modifier.
     * @param world A world to be generated
     * @param mount Modifier for the height-map generation
     */
    private void setMountainousness(World world, int mount) {    
        Tile[][] tilemap = world.getTiles();
        for (int x = 0; x < tilemap.length; x++) {
            for (int y = 0; y < tilemap.length; y++) {
                double noise = getMountainNoise(x, y, world.getSize());
                noise += Math.abs(noise) * (mount / 100f) + (mount / 100f);
                
                noise = Math.min(1, noise);
                tilemap[x][y].setHeight(noise);
            }
        }
    }
    
    /**
     * Creates a 2 dimensional height-map from noise and sets the given World's
     * tile humidity based on that and the given humidity modifier.
     * @param world A world to be generated
     * @param humidity Modifier for the humidity height-map
     */
    private void setHumidity(World world, int humidity) {
        Tile[][] tilemap = world.getTiles();
        for (int x = 0; x < tilemap.length; x++) {
            for (int y = 0; y < tilemap.length; y++) {
                double noise = getHumidityNoise(x, y, world.getSize());
                noise += (humidity / 100f);
                noise *= (humidity / 100f);
                noise = Math.min(1, Math.max(0, noise));
                tilemap[x][y].setHumidity(noise);
            }
        }
    }
    /**
     * Creates a 2 dimensional height-map from noise and sets the given World's
     * tile vegetation based on that and the given vegetation modifier.
     * @param world A world to be generated
     * @param vegetation Modifier for the vegetation height-map
     */
    private void setVegetation(World world, int vegetation) {
        Tile[][] tilemap = world.getTiles();
        for (int x = 0; x < tilemap.length; x++) {
            for (int y = 0; y < tilemap.length; y++) {
                double noise = getVegetationNoise(x, y, world.getSize());
                noise += Math.abs(noise) * (vegetation / 100f);
                noise = Math.min(1, noise);
                tilemap[x][y].setVegetation(noise);
            }
        }
    }
    
    //TODO: These 3 methods can probably still be merged to one noise function.
    // They are currently separate as you have to use different seed for each or
    // the height map for each variable will be the same. 
    // I had them as one method with a type given as parameter but I separated them
    // as I wanted to use an entirely different noise algorithm for some of them
    // Have to decide what to do.
    
    
    /**
     * Creates a Perlin noise value for given 2 dimensional coordinates
     * and scales that based on the world size.
     * @param x     X coordinate for the noise.
     * @param y     Y coordinate for the noise.
     * @param worldSize     Size of the world to be generated.
     * @return 
     */
    private double getMountainNoise(int x, int y, int worldSize) {
        float scale = (float) (30 * worldSize) / (1 + Math.abs(worldSize + 20));
        JNoise perlinNoise = JNoise.newBuilder()
                .perlin().setInterpolationType(InterpolationType.COSINE).setSeed(this.seed + 1).build();
        return perlinNoise.getNoise(x / scale, y / scale);
    }
    
    /**
     * Creates a Perlin noise value for given 2 dimensional coordinates
     * and scales that based on the world size.
     * @param x     X coordinate for the noise.
     * @param y     Y coordinate for the noise.
     * @param worldSize     Size of the world to be generated.
     * @return 
     */
    private double getHumidityNoise(int x, int y, int worldSize) {
        //float scale = (float) (20-Math.sqrt(worldSize * 0.1));
        float scale = (float) (30 * worldSize) / (1 + Math.abs(worldSize + 20));
        JNoise perlinNoise = JNoise.newBuilder()
                .perlin().setInterpolationType(InterpolationType.COSINE).setSeed(this.seed + 2).build();
        
        return perlinNoise.getNoise(x / scale, y / scale);
    }
    
    /**
     * Creates a Perlin noise value for given 2 dimensional coordinates
     * and scales that based on the world size.
     * @param x     X coordinate for the noise.
     * @param y     Y coordinate for the noise.
     * @param worldSize     Size of the world to be generated.
     * @return 
     */
    private double getVegetationNoise(int x, int y, int worldSize) {
        float scale = (float) Math.sqrt(worldSize);
        JNoise perlinNoise = JNoise.newBuilder()
                .perlin().setInterpolationType(InterpolationType.COSINE).setSeed(this.seed + 3).build();
        
        return perlinNoise.getNoise(x / 10, y / 10);
    }
}
