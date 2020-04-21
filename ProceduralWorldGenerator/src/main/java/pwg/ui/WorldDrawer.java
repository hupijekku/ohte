package pwg.ui;

/**
 *
 * @author emu
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pwg.domain.Tile;
import pwg.domain.World;

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
        if (size <= 0) return;
        int tileSize = canvasSize / size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double height = tilemap[x][y].getHeight();
                height = Math.min(1, Math.max(height, 0));
                double humidity = tilemap[x][y].getHumidity();
                Color c;
                if (height < 0 && humidity > 0.4) {
                    // Water
                    c = Color.BLUE;
                } else {
                    // Land
                    if (height < 0.3) {
                        c = Color.GREEN;
                    } else if (height < 0.6) {
                        c = Color.DARKGREEN;
                    } else if (height < 0.9) {
                        c = Color.DARKGRAY;
                    } else if (height < 1) {
                        c = Color.GRAY;
                    } else {
                        c = new Color(0.3d, 0.3d, 0.3d, 1);
                    }
                }
                gc.setFill(c);
                gc.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
            }
        }
    }
    
    public void drawWaters(World world) {
        Tile[][] tilemap = world.getTiles();
        int size = world.getSize();
        if (size <= 0) return;
        int tileSize = canvasSize / size;
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                double height = tilemap[x][y].getHeight();
                double humidity = tilemap[x][y].getHumidity();
                Color c = Color.BLUE;
                System.out.println(height + " : " + humidity);
                if (height - humidity < 0 && height < 0.4 && humidity > 0.05) {
                    gc.setFill(c);
                    gc.fillRect(x * tileSize, y * tileSize, tileSize, tileSize);
                }
            }
        }
    }
    
}
