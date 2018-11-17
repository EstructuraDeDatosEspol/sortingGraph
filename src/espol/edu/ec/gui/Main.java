package espol.edu.ec.gui;

import espol.edu.ec.tda.MyChart;
import espol.edu.ec.utils.Algorithms;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;


/**
 *
 * @author MiguelPS
 */
public class Main extends Application{


    @Override
    public void start(Stage stage){

        StackPane root = new StackPane();

        MyChart chart = new MyChart();

        Algorithms<Integer> algorithms = new Algorithms<>();
        ArrayList<Integer> datos = new ArrayList<>();
        datos.add(1);
        datos.add(3);
        datos.add(5);
        datos.add(6);
        datos.add(7);
        datos.add(9);
        datos.add(11);
        datos.add(13);
        datos.add(15);
        datos.add(17);
        datos.add(16);
        datos.add(21);
        datos.add(24);

        ArrayList<Integer> datos2 = new ArrayList<>();
        datos2.add(2);
        datos2.add(4);
        datos2.add(6);
        datos2.add(7);
        datos2.add(8);
        datos2.add(10);
        datos2.add(12);
        datos2.add(14);
        datos2.add(16);
        datos2.add(18);
        datos2.add(17);
        datos2.add(22);
        datos2.add(27);

        chart.plotInsertionSortTimes( algorithms.getInsertionSortTimes(datos));
        chart.plotQuickSortTimes( algorithms.getQuickSortTimes(datos2));


        root.getChildren().add(chart.getChart());

        stage.setScene(new Scene(root, 800,600));

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
