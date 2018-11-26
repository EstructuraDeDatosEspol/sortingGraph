package espol.edu.ec.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.stage.Screen;


/**
 *
 * @author MiguelPS
 */
public class Main extends Application{
    
    /**
     * Ancho máximo de la pantalla
     */
    private final double MAX_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    
    /**
     * Alto máximo de la pantalla
     */
    private final double MAX_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    @Override
    public void start(Stage stage){
        stage.setTitle("Comparación de Algoritmos de Ordenamiento.");
        GUIHelper gui = new GUIHelper();
        gui.setOwnerStage(stage);
        Scene scene = new Scene(gui.root);
        scene.getStylesheets().add("espol/edu/ec/styles/theme.css");
        stage.setScene(scene);
        stage.setMaxHeight(MAX_HEIGHT);
        stage.setMaxWidth(MAX_WIDTH);
        stage.setMinHeight(MAX_HEIGHT * 0.72);
        stage.setMinWidth(MAX_WIDTH * 0.6);
        stage.getIcons().add(new Image("espol/edu/ec/styles/diagram.png"));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
