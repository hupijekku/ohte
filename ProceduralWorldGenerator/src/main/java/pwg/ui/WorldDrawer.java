package pwg.ui;

/**
 *
 * @author emu
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pwg.domain.Tile;
import pwg.domain.World;
import pwg.domain.WorldType;

public class WorldDrawer {
    
    private GraphicsContext gc;
    private int canvasSize = 0;
    
    public WorldDrawer(GraphicsContext gc, int canvasSize) {
        this.gc = gc;
        this.canvasSize = canvasSize;
    }
    
    public void drawGrid(int worldSize) {
        gc.clearRect(0, 0, canvasSize, canvasSize);
        if (worldSize > 0) {
            int tileSize = canvasSize / worldSize;
            Color fill = Color.GRAY;
            for (int i = 0; i < worldSize; i++) {
                for (int j = 0; j < worldSize; j++) {
                    gc.setFill(fill);
                    gc.fillRect(i * tileSize, j * tileSize, tileSize, tileSize);
                    fill = fill == Color.GRAY ? Color.DARKGRAY : Color.GRAY;
                }
                if (worldSize % 2 == 0) {
                    fill = fill == Color.GRAY ? Color.DARKGRAY : Color.GRAY;
                }
            }
        }
    }
    
    public void drawHeightMap(World world) {
        Tile[][] tilemap = world.getTiles();
        int size = world.getSize();
        int tileSize = canvasSize / size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double height = Math.min(1, Math.max(tilemap[x][y].getHeight(), 0));
                //height = Math.min(1, Math.max(height, 0));
                double humidity = tilemap[x][y].getHumidity();
                if (height < 0 && humidity > 0.4) {
                    // Water
                    drawRect(x, y, tileSize, Color.BLUE);
                } else {
                    // Land
                    if (height < 0.3) {
                        drawRect(x, y, tileSize, Color.GREEN);
                    } else if (height < 0.6) {
                        drawRect(x, y, tileSize, Color.DARKGREEN);
                    } else if (height < 0.8) {
                        drawRect(x, y, tileSize, Color.DARKGRAY);
                    } else if (height < 1) {
                        drawRect(x, y, tileSize, Color.GRAY);
                    } else {
                        drawRect(x, y, tileSize, new Color(0.3d, 0.3d, 0.3d, 1));
                    }
                }
            }
        }
    }
    
    public void drawWaters(World world) {
        Tile[][] tilemap = world.getTiles();
        int size = world.getSize();
        int tileSize = canvasSize / size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double height = tilemap[x][y].getHeight();
                double humidity = tilemap[x][y].getHumidity();
                Color c = Color.BLUE;
                if (height - humidity < 0 && height < 0.4 && humidity > 0.05) {
                    gc.setFill(c);
                    gc.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }
    
    public void drawDungeon(World world) {
        Tile[][] tilemap = world.getTiles();
        int size = world.getSize();
        int tileSize = canvasSize / size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                Color c;
                if (tilemap[x][y].isPassable() && tilemap[x][y].isTransparent()) {
                    drawRect(x, y, tileSize, Color.GRAY);
                } else if (tilemap[x][y].isPassable()) {
                    drawRect(x, y, tileSize, Color.PURPLE);
                } else {
                    drawRect(x, y, tileSize, Color.BLACK);
                }
            }
        }
    }
    
    public void drawRect(int x, int y, int size, Color c) {
        gc.setFill(c);
        gc.fillRect(x*size, y*size, size, size);
    }
    
}
