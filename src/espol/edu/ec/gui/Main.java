package espol.edu.ec.gui;

import espol.edu.ec.tda.MyChart;
import espol.edu.ec.utils.Algorithms;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import javafx.scene.control.Button;


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
        
        root.getFlwTop().getChildren().addAll(new Button("adsadd"), new Button("Asdsadasd"), new Button("dsasdadsd"), new Button("asdasdsadsad"), 
                new Button("adsadd"), new Button("Asdsadasd"), new Button("dsasdadsd"), new Button("asdasdsadsad"));
        root.getFlwTop().setHgap(20); 
        root.getVRight().getChildren().addAll(new Button("adsadd"), new Button("Asdsadasd"), new Button("dsasdadsd"), new Button("asdasdsadsad"), 
                new Button("adsadd"), new Button("Asdsadasd"), new Button("dsasdadsd"), new Button("asdasdsadsad"));
        root.getVRight().setSpacing(20);
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

        root.setCenter(chart.getChart());

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
