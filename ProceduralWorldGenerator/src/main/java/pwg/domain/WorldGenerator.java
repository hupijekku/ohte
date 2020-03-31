package pwg.domain;

/**
 *
 * @author Hupijekku
 */
public class WorldGenerator {
    
    
    /**
    * Generates a world of the given type and size
    *
    *@param    type    type of the world
    *@param    size    length of a side for the generated square shaped world
    * 
    *@return    returns a World object that contains the generated world data.
    */
    public World generate(WorldType type, int size) {
        Tile[][] tilemap = new Tile[size][size];
        for(int x = 0; x < size; x++) {
            for(int y = 0; y < size; y++) {
                /*
                TODO: 
                    Procedural generation based on WorldType
                    and other variables that will be added to the
                    UI for the user to change.
                CURRENT:
                    Array of same type of Tiles, not based on any
                    user-set variable.
                */
                tilemap[x][y] = new Tile();
            }
        }
        return new World();
    }
}
