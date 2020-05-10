package pwg.domain;

import de.articdive.jnoise.JNoise;
import de.articdive.jnoise.interpolation.InterpolationType;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Hupijekku
 */
public class WorldGenerator {
    
    
    private int seed = 0;
    private Random rnd;
    
    /**
     * Empty constructor for the WorldGenerator.
     * Sets a random seed value for this world.
     */
    public WorldGenerator() {
        rnd = new Random();
        this.seed = rnd.nextInt();
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
        World world;
        if (type == WorldType.WORLD) {
            world = generateWorld(size, humidity, mountainousness, vegetation);
        } else {
            world = generateDungeon(size);
        }
        return world;
    }
    
    /**
     * Creates a dungeon-type world.
     * First the method creates rooms of random size at random locations.
     * If it can't find enough suitable locations, it skips the rooms.
     * Then from each of the rooms, it builds a path to one other room.
     * And finally, it creates doors for every room.
     * @param size      Size of the world to be generated.
     * @return          Returns a World object containing the dungeon layout.
     */
    public World generateDungeon(int size) {
        Tile[][] tilemap = new Tile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                tilemap[x][y] = new Tile(false, false);
                tilemap[x][y].setX(x);
                tilemap[x][y].setY(y);
            }
        }
        int roomCount = 10;
        int iter = 0;
        ArrayList<Room> rooms = new ArrayList();
        while (iter < roomCount) {
            int x = Math.abs(rnd.nextInt()) % (size - 10) + 5;
            int y = Math.abs(rnd.nextInt()) % (size - 10) + 5;
            int width = Math.abs(rnd.nextInt()) % 5 + 5;
            int height = Math.abs(rnd.nextInt()) % 5 + 5;
            Room room = new Room(x, y, width, height);
            if (rooms.isEmpty()) {
                rooms.add(room);
                generateRoom(tilemap, room);
            } else {
                boolean valid = true;
                for (Room r : rooms) {
                    if (r.roomCollision(room)) {
                        valid = false;
                    }
                }
                if (valid) {
                    rooms.add(room);
                    generateRoom(tilemap, room);
                } else {
                    roomCount++;
                    if (roomCount > 100) {
                        break;
                    }
                }
            }
            System.out.print(".");
            iter++;
        }
        for (int i = 0; i < rooms.size() - 1; i++) {
            Room r1 = rooms.get(i);
            Room r2 = rooms.get(i + 1);
            generatePath(tilemap, rooms, r1.pivotX, r1.pivotY, r2.pivotX, r2.pivotY);
        }
        generatePath(tilemap, rooms, rooms.get(0).pivotX, rooms.get(rooms.size() - 1).pivotX,
                     rooms.get(0).pivotY, rooms.get(rooms.size() - 1).pivotY);
        for (Room r : rooms) {
            r.generateDoors(tilemap);
        }
        World world = new World(WorldType.DUNGEON, size, tilemap);
        return world;
    }
    
    public void generateRoom(Tile[][] tilemap, Room r) {
        for (int i = r.pivotX - (r.width / 2); i < r.pivotX + (r.width / 2); i++) {
            for (int j = r.pivotY - (r.height / 2); j < r.pivotY + (r.height / 2); j++) {
                tilemap[i][j].setPassable(true);
                tilemap[i][j].setTransparent(true);
            }
        }
    }
    
    public void generatePath(Tile[][] tilemap, ArrayList<Room> rooms, int x1, int y1, int x2, int y2) {
        Pathfinder pf = new Pathfinder(tilemap);
        ArrayList<Tile> path = pf.path(x1, y1, x2, y2);
        for (Tile t : path) {
            if (this.rnd.nextInt(100) < 3) {
                boolean valid = true;
                for (Room r : rooms) {
                    if (r.isInRoom(t.getX(), t.getY())) {
                        valid = false;
                    }
                }
                if (valid) {
                    t.setTransparent(false);
                }
            } else {
                t.setTransparent(true);
            }
            t.setPassable(true);
        }
    }
    /**
    * Generates a World-type world of given size
    *
    * @param    size                length of a side for the generated square shaped world
    * @param    humidity            humidity/wetness of the world, amount and size of bodies of water
    * @param    mountainousness     amount of mountains and average height
    * @param    vegetation          amount of vegetation
    * 
    *@return    returns a World object that contains the generated world terrain.
    */
    public World generateWorld(int size, int humidity, int mountainousness, int vegetation) {
        Tile[][] tilemap = new Tile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                // WorldType = World -> Don't care about pathfinding/fow
                tilemap[x][y] = new Tile(true, true);
                tilemap[x][y].setX(x);
                tilemap[x][y].setY(y);
            }
        }
        World world = new World(WorldType.WORLD, size, tilemap);
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
                noise *= getHumidityNoise(x, y, world.getSize());
                
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
