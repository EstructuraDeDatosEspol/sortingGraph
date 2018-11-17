package espol.edu.ec.tda;

import javafx.scene.chart.*;
import java.util.List;


/**
 *
 * @author MiguelPS
 */
public class MyChart{


    final NumberAxis xAxis;
    final NumberAxis yAxis;

    private LineChart<Number, Number> chart;


    XYChart.Series insertionLine;
    XYChart.Series quickLine;
    XYChart.Series mergeLine;
    XYChart.Series stoogeLine;

    public MyChart() {

        xAxis = new NumberAxis();
        yAxis = new NumberAxis();
        chart = new LineChart<>(xAxis,yAxis);

        insertionLine = new LineChart.Series();
        quickLine     = new LineChart.Series();
        mergeLine     = new LineChart.Series();
        stoogeLine    = new LineChart.Series();

        insertionLine.setName("Insetion Sort");
        quickLine.setName("QuickSort");
        mergeLine.setName("Merge Sort");
        stoogeLine.setName("Stooge Sort");

    }


    public void plotInsertionSortTimes(List<Entry> data){

        chart.getData().add(insertionLine);
        for(Entry entry: data){
            insertionLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }


    public void plotQuickSortTimes(List<Entry> data){

        chart.getData().add(quickLine);
        for(Entry entry: data){
            quickLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }


    public void plotMergeSortTimes(List<Entry> data){
        for(Entry entry: data){
            mergeLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }


    public void plotStoogeSortTimes(List<Entry> data){
        for(Entry entry: data){
            stoogeLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }


    public void clear() {
        chart.getData().clear();
    }

    public LineChart<Number, Number> getChart(){
        return this.chart;
    }
}
