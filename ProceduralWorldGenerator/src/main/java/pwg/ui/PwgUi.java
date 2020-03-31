package pwg.ui;

/**
 *
 * @author Hupijekku
 */
import javafx.application.Application;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;

import pwg.domain.WorldType;
import pwg.domain.WorldGenerator;

public class PwgUi extends Application {
        
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Procedural World Generator");
        
        // Local variables
        int canvasSize = 500;
        
        // Initializing UI elements
        //  Layout
        StackPane root = new StackPane();
        BorderPane menu = new BorderPane();
        VBox menuLayout = new VBox(1);
        HBox menuItem_worldSize = new HBox(2);
        HBox menuItem_worldType = new HBox(2);
        HBox menuItem_generateButton = new HBox(1);
        Canvas canvas = new Canvas(canvasSize, canvasSize);
        
        //  Control
        Label lbl_worldSize = new Label("World size:");
        Label lbl_worldType = new Label("World type:");
        ComboBox<WorldType> cb_worldType = new ComboBox();
        cb_worldType.getItems().setAll(WorldType.values());
        cb_worldType.setValue(WorldType.WORLD);
        TextField tf_worldSize = new TextField();
        Button btn_generateWorld = new Button("Generate World");
        
        
        // Tweaking alignment
        Insets padding = new Insets(10, 10, 10, 10);
        menuLayout.setPadding(padding);
        menuLayout.setSpacing(10);
        menuItem_worldSize.setPadding(padding);
        menuItem_worldSize.setSpacing(10);
        menuItem_worldType.setPadding(padding);
        menuItem_worldType.setSpacing(10);
        menu.setCenter(canvas);
        menu.setRight(menuLayout);
        menuItem_generateButton.setAlignment(Pos.CENTER);
        
        
        // Setting hierarchy
        menuItem_worldSize.getChildren().add(lbl_worldSize);
        menuItem_worldSize.getChildren().add(tf_worldSize);
        menuItem_worldType.getChildren().add(lbl_worldType);
        menuItem_worldType.getChildren().add(cb_worldType);
        menuItem_generateButton.getChildren().add(btn_generateWorld);
        menuLayout.getChildren().add(menuItem_worldSize);
        menuLayout.getChildren().add(menuItem_worldType);
        menuLayout.getChildren().add(menuItem_generateButton);
        root.getChildren().add(menu);
        
        
        // Drawing a base grid (Temporary)
        WorldDrawer drawer = new WorldDrawer(canvas.getGraphicsContext2D());
        btn_generateWorld.setOnAction(event->{
            int worldSize = 0;
            try {
                worldSize = Integer.parseInt(tf_worldSize.getText());
            }
            catch (Exception ex) {
                errorBox("Error parsing integer", "World size given is not an integer.");
            }
            if (worldSize > 100 || worldSize < 0) {
                errorBox("Invalid size", "World size must be between 0 and 100");
            }
            else {
                WorldGenerator generator = new WorldGenerator(cb_worldType.getValue(), worldSize);
                drawer.drawGrid(worldSize, canvasSize);
            }
        });
        
        
        // Initialize and show the scene
        Scene scn_main = new Scene(root, 1024, 576);
        
        stage.setScene(scn_main);
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
