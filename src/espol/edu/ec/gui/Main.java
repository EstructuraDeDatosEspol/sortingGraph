package espol.edu.ec.gui;

import espol.edu.ec.tda.MyChart;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.image.Image;


/**
 *
 * @author MiguelPS
 */
public class Main extends Application{

    @Override
    public void start(Stage stage){
        stage.setTitle("Comparación de Algoritmos de Ordenamiento.");
        GUIHelper gui = new GUIHelper();
        gui.setOwnerStage(stage);
        Scene scene = new Scene(gui.root);
        scene.getStylesheets().add("espol/edu/ec/styles/theme.css");
        stage.setScene(scene);
        Image i = new Image("espol/edu/ec/styles/diagram.png");
        stage.getIcons().add(i);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
