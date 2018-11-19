package espol.edu.ec.gui;

import espol.edu.ec.tda.MyChart;
import espol.edu.ec.utils.Algorithms;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 *
 * @author MiguelPS
 */
public class Main extends Application{


    @Override
    public void start(Stage stage){

        ContentPane root = new ContentPane();
        stage.setScene(new Scene(root));
        stage.setMaximized(true);
        stage.setMaxHeight(root.getMAX_HEIGHT());
        stage.setMaxWidth(root.getMAX_WIDTH());
        stage.setMinHeight(root.getMAX_HEIGHT() * 0.5);
        stage.setMinWidth(root.getMAX_WIDTH() * 0.5); 
        stage.getScene().getStylesheets().add(getClass().getResource("/espol/edu/ec/resources/Style.css").toExternalForm());
        MyChart chart = new MyChart();
        ControlPane tp = new ControlPane(root, stage, chart);
        
        

        Algorithms<Integer> algorithms = new Algorithms<>(Integer:: compareTo);
        ArrayList<Integer> datos = new ArrayList<>();
        datos.add(1);
        datos.add(8);
        datos.add(5);
        datos.add(6);
        datos.add(3);
        datos.add(90);
        datos.add(11);
        datos.add(13);
        datos.add(15);
        datos.add(4);
        datos.add(16);
        datos.add(21);
        datos.add(70);

        ArrayList<Integer> datos2 = new ArrayList<>();
        datos2.add(1);
        datos2.add(8);
        datos2.add(5);
        datos2.add(6);
        datos2.add(3);
        datos2.add(90);
        datos2.add(11);
        datos2.add(13);
        datos2.add(15);
        datos2.add(4);
        datos2.add(16);
        datos2.add(21);
        datos2.add(70);
        
        chart.plotInsertionSortTimes( algorithms.getInsertionSortTimes(datos));
        chart.plotQuickSortTimes( algorithms.getQuickSortTimes(datos2));

        root.setCenter(chart.getChart());

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
