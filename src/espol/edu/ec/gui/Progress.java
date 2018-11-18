/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kenny Camba
 */
public class Progress {
    private final Stage stage;
    private final ImageView load;
    private final StackPane root;
    private final ProgressIndicator indicator;
    
    public Progress(Stage owner) {
        load = new ImageView(new Image(getClass().getResourceAsStream("/espol/edu/ec/resources/load.gif"), 75, 75, true, true));
        root = new StackPane();
        indicator = new ProgressIndicator();
        stage = new Stage(); 
        root.getChildren().add(indicator);
        stage.initStyle(StageStyle.TRANSPARENT); 
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.initOwner(owner);
        stage.initModality(Modality.APPLICATION_MODAL);
        root.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY))); 
    }
    
    public void show() {
        stage.show();
    }
    
    public void close() {
        stage.close();
    }
    public void setProgress(double i) {
        indicator.setProgress(i); 
    }
}
