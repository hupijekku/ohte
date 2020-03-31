package pwg.ui;

/**
 *
 * @author emu
 */

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class WorldDrawer {
    
    private GraphicsContext gc;
    
    public WorldDrawer(GraphicsContext gc) {
        this.gc = gc;
    }
    
    public void drawGrid(int worldSize, int canvasSize) {
        gc.clearRect(0, 0, canvasSize, canvasSize);
        if (worldSize > 0) {
            int tileSize = canvasSize/worldSize;
            Color fill = Color.GRAY;
            for(int i = 0; i < worldSize; i++) {
                for (int j = 0; j < worldSize; j++) {
                    gc.setFill(fill);
                    gc.fillRect(i*tileSize, j*tileSize, tileSize, tileSize);
                    fill = fill == Color.GRAY ? Color.DARKGRAY : Color.GRAY;
                }
                if (worldSize % 2 == 0) {
                    fill = fill == Color.GRAY ? Color.DARKGRAY : Color.GRAY;
                }
            }
        }
    }
    
}
