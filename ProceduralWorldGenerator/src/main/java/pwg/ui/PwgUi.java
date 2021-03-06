package pwg.ui;

/**
 *
 * @author Hupijekku
 */
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import pwg.dao.Database;
import pwg.domain.Tile;

import pwg.domain.WorldType;
import pwg.domain.WorldGenerator;
import pwg.domain.World;

public class PwgUi extends Application {
    
    private Database db;
        
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        
        db = new Database("jdbc:sqlite:pwg.db");
        stage.setTitle("Procedural World Generator");
        
        // Local variables
        int canvasSize = 500;
        
        // Initializing UI elements
        //  Layout
        StackPane root = new StackPane();
        BorderPane menu = new BorderPane();
        VBox menuLayout = new VBox(1);
        HBox menuItemWorldSize = new HBox(2);
        HBox menuItemWorldType = new HBox(2);
        HBox menuItemGenerateButton = new HBox(1);
        HBox menuItemHumiditySlider = new HBox(2);
        HBox menuItemMountainousnessSlider = new HBox(2);
        HBox menuItemVegetationSlider = new HBox(2);
        HBox menuItemRoomCountSlider = new HBox(2);
        HBox menuItemSave = new HBox(2);
        HBox menuItemSaveButton = new HBox(1);
        HBox menuItemLoadButton = new HBox(1);
        HBox menuItemLoad = new HBox(2);
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        
        //  Control
        Label lblWorldSize = new Label("World size:");
        Label lblWorldType = new Label("World type:");
        Label lblWorldHumidity = new Label("Humidity:");
        Label lblWorldMountainousness = new Label("Mountainousness:");
        Label lblWorldVegetation = new Label("Vegetation:");
        Label lblRoomCount = new Label("Room count:");
        Label lblSave = new Label("Save name:");
        Label lblLoad = new Label("Load:");
        ComboBox<WorldType> cbWorldType = new ComboBox();
        cbWorldType.getItems().setAll(WorldType.values());
        cbWorldType.setValue(WorldType.WORLD);
        TextField tfWorldSize = new TextField();
        tfWorldSize.setText("50");
        TextField tfSave = new TextField();
        ComboBox<String> cbLoad = new ComboBox();
        Button btnGenerateWorld = new Button("Generate World");
        Button btnSave = new Button("Save");
        Button btnLoad = new Button("Load");
        Slider sldHumidity = new Slider();
        Slider sldMountainousness = new Slider();
        Slider sldVegetation = new Slider();
        Slider sldRoomCount = new Slider();
        
        // Sliders
        sldHumidity.setMin(0);
        sldHumidity.setMax(100);
        sldHumidity.setValue(40);
        sldHumidity.setShowTickLabels(true);
        sldHumidity.setShowTickMarks(true);
        sldHumidity.setMinorTickCount(5);
        sldHumidity.setMajorTickUnit(50);
        
        sldMountainousness.setMin(0);
        sldMountainousness.setMax(100);
        sldMountainousness.setValue(20);
        sldMountainousness.setShowTickLabels(true);
        sldMountainousness.setShowTickMarks(true);
        sldMountainousness.setMinorTickCount(5);
        sldMountainousness.setMajorTickUnit(50);
        
        sldVegetation.setMin(0);
        sldVegetation.setMax(100);
        sldVegetation.setValue(50);
        sldVegetation.setShowTickLabels(true);
        sldVegetation.setShowTickMarks(true);
        sldVegetation.setMinorTickCount(5);
        sldVegetation.setMajorTickUnit(50);
        
        sldRoomCount.setMin(1);
        sldRoomCount.setMax(20);
        sldRoomCount.setValue(10);
        
        
        // Setting hierarchy
        menuItemWorldSize.getChildren().add(lblWorldSize);
        menuItemWorldSize.getChildren().add(tfWorldSize);
        menuItemWorldType.getChildren().add(lblWorldType);
        menuItemWorldType.getChildren().add(cbWorldType);
        menuItemSave.getChildren().add(lblSave);
        menuItemSave.getChildren().add(tfSave);
        menuItemLoad.getChildren().add(lblLoad);
        menuItemLoad.getChildren().add(cbLoad);
        menuItemSaveButton.getChildren().add(btnSave);
        menuItemLoadButton.getChildren().add(btnLoad);
        menuItemRoomCountSlider.getChildren().add(lblRoomCount);
        menuItemRoomCountSlider.getChildren().add(sldRoomCount);
        menuItemHumiditySlider.getChildren().add(lblWorldHumidity);
        menuItemMountainousnessSlider.getChildren().add(lblWorldMountainousness);
        menuItemVegetationSlider.getChildren().add(lblWorldVegetation);
        menuItemHumiditySlider.getChildren().add(sldHumidity);
        menuItemMountainousnessSlider.getChildren().add(sldMountainousness);
        menuItemVegetationSlider.getChildren().add(sldVegetation);
        menuItemGenerateButton.getChildren().add(btnGenerateWorld);
        menuLayout.getChildren().add(menuItemWorldSize);
        menuLayout.getChildren().add(menuItemWorldType);
        menuLayout.getChildren().add(menuItemHumiditySlider);
        menuLayout.getChildren().add(menuItemMountainousnessSlider);
        menuLayout.getChildren().add(menuItemVegetationSlider);
        menuLayout.getChildren().add(menuItemRoomCountSlider);
        menuLayout.getChildren().add(menuItemGenerateButton);
        menuLayout.getChildren().add(menuItemSave);
        menuLayout.getChildren().add(menuItemSaveButton);
        menuLayout.getChildren().add(menuItemLoad);
        menuLayout.getChildren().add(menuItemLoadButton);

        root.getChildren().add(menu);
        
                
        // Tweaking alignment
        Insets padding = new Insets(10, 10, 10, 10);
        menuLayout.setPadding(padding);
        menuItemWorldSize.setPadding(padding);
        menuItemWorldType.setPadding(padding);
        sldHumidity.setPadding(padding);
        sldMountainousness.setPadding(padding);
        sldVegetation.setPadding(padding);
        menuItemWorldType.setSpacing(10);
        menuItemWorldSize.setSpacing(10);
        menuLayout.setSpacing(10);
        menu.setCenter(canvas);
        menu.setRight(menuLayout);
        menuItemGenerateButton.setAlignment(Pos.CENTER);
        HBox.setHgrow(sldHumidity, Priority.ALWAYS);
        HBox.setHgrow(sldMountainousness, Priority.ALWAYS);
        HBox.setHgrow(sldVegetation, Priority.ALWAYS);
        menuItemRoomCountSlider.setVisible(false);
        
        ArrayList<String> names = db.getWorldNames();
        if(names != null && names.size() > 0) {
            ObservableList<String> list = FXCollections.observableArrayList(names);
            cbLoad.setItems(list);
        }
        
        
        // Change controls based on world type
        cbWorldType.setOnAction(event -> {
            WorldType type = cbWorldType.getValue();
            if (type == WorldType.WORLD) {
                menuItemRoomCountSlider.setVisible(false);
                menuItemHumiditySlider.setVisible(true);
                menuItemMountainousnessSlider.setVisible(true);
                menuItemVegetationSlider.setVisible(true);
            } else {
                menuItemRoomCountSlider.setVisible(true);
                menuItemHumiditySlider.setVisible(false);
                menuItemMountainousnessSlider.setVisible(false);
                menuItemVegetationSlider.setVisible(false);
            }
        });
        
        // Drawing the world
        ArrayList<World> worlds = new ArrayList();
        btnGenerateWorld.setOnAction(event-> {
            int humidity = (int) sldHumidity.getValue();
            int mountainousness = (int) sldMountainousness.getValue();
            int vegetation = (int) sldVegetation.getValue();
            int roomCount = (int) sldRoomCount.getValue();
            int worldSize = 0;
            try {
                worldSize = Integer.parseInt(tfWorldSize.getText());
            } catch (Exception ex) {
                errorBox("Error parsing integer", "World size given is not an integer.");
            }
            WorldDrawer drawer = new WorldDrawer(canvas.getGraphicsContext2D(), canvasSize);
            if (worldSize > 100 || worldSize < 10) {
                errorBox("Invalid size", "World size must be between 10 and 100");
            } else {
                WorldGenerator generator = new WorldGenerator();
                WorldType type = cbWorldType.getValue();
                World world = generator.generate(type, worldSize, humidity, mountainousness, vegetation, roomCount);
                worlds.add(world);
                drawer.drawGrid(worldSize);
                if(type == WorldType.WORLD) {
                    drawer.drawHeightMap(world);
                    drawer.drawWaters(world);
                    drawer.drawVegetation(world);
                } else if (type == WorldType.DUNGEON) {
                    drawer.drawDungeon(world);
                }
            }
        });
        
        
        // Save world
        btnSave.setOnAction(event -> {
            String name = tfSave.getText();
            if (name.length() > 3) {
                if (db.getWorldByName(name) == null) {
                    World world = worlds.get(worlds.size() - 1);
                    db.saveWorld(world, name);
                    db.saveTiles(world.getTiles(), name);
                    cbLoad.getItems().add(name);
                } else {
                    errorBox("Invalid name", "Name must be unique");
                }
            } else {
                errorBox("Invalid name", "Name must be at least 4 characters long.");
            }
        });
        
        // Load world
        btnLoad.setOnAction(event -> {
            String name = cbLoad.getValue();
            World world = db.getWorldByName(name);
            if (world != null) {
                ArrayList<Tile> tiles = db.getTilesByWorldName(name);
                int size = world.getSize();
                Tile[][] tilemap = new Tile[size][size];
                for (Tile t : tiles) {
                    tilemap[t.getX()][t.getY()] = t;
                }
                world.setTiles(tilemap);
                WorldDrawer drawer = new WorldDrawer(canvas.getGraphicsContext2D(), canvasSize);
                drawer.drawGrid(size);
                if(world.getType() == WorldType.WORLD) {
                    drawer.drawHeightMap(world);
                    drawer.drawWaters(world);
                    drawer.drawVegetation(world);
                } else if (world.getType() == WorldType.DUNGEON) {
                    drawer.drawDungeon(world);
                }
            } else {
                errorBox("Corrupted world", "Not sure how you managed this, try to delete database file.");
            }
        });
        
        
        // Initialize and show the scene
        Scene scnMain = new Scene(root, 1024, 576);
        
        stage.setScene(scnMain);
        stage.show();
    }
    
    
    /**
    * Error message box
    * 
    * @param    header   type of error
    * @param    content  description of what caused the error   
    */  
    public void errorBox(String header, String content) {
        Alert errorBox = new Alert(AlertType.ERROR);
        errorBox.setTitle("Error!");
        errorBox.setHeaderText(header);
        errorBox.setContentText(content);
        errorBox.showAndWait();
    }
}
