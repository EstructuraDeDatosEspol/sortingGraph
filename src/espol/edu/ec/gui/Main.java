package espol.edu.ec.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author SSAM
 */
public class Main extends Application{
    public static void main(String[] args) {
        
    }

    @Override
    public void start(Stage stage){
        Pane root = new Pane();
        stage.setScene(new Scene(root));
        
    }
}
