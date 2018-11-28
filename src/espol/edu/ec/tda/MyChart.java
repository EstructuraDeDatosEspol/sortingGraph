package espol.edu.ec.tda;

import javafx.scene.chart.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;


/**
 *
 * @author MiguelPS
 */
public class MyChart{


    final NumberAxis xAxis;
    final NumberAxis yAxis;

    private LineChart<Number, Number> chart;


    LineChart.Series<Number, Number> insertionLine;
    LineChart.Series<Number, Number> quickLine;
    LineChart.Series<Number, Number> mergeLine;
    LineChart.Series<Number, Number> stoogeLine;

    public MyChart() {

        xAxis = new NumberAxis();
        xAxis.setLabel("Numero de Elementos (n)");
        yAxis = new NumberAxis();
        yAxis.setLabel("Tiempo (ms)");

        chart = new LineChart<>(xAxis,yAxis);
        VBox.setVgrow(chart, Priority.ALWAYS);

        insertionLine = new LineChart.Series();
        quickLine     = new LineChart.Series();
        mergeLine     = new LineChart.Series();
        stoogeLine    = new LineChart.Series();

        insertionLine.setName("Insetion Sort");
        quickLine.setName("QuickSort");
        mergeLine.setName("Merge Sort");
        stoogeLine.setName("Stooge Sort");

        chart.getData().addAll(insertionLine, quickLine, mergeLine, stoogeLine);

    }


    public void setVisiblesInsertionLine(boolean visible){
    }

    public void plotInsertionSortTimes(List<Entry> data){
        if (!insertionLine.getData().isEmpty())
            insertionLine.getData().clear();
        for(Entry entry: data){
            insertionLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }

    
    public void plotQuickSortTimes(List<Entry> data){
        if (!quickLine.getData().isEmpty())
            quickLine.getData().clear();
        for(Entry entry: data){
            quickLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }


    
    public void plotMergeSortTimes(List<Entry> data){
        if (!mergeLine.getData().isEmpty())
            mergeLine.getData().clear();
        for(Entry entry: data){
            mergeLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }

    
    public void plotStoogeSortTimes(List<Entry> data){
        if (!stoogeLine.getData().isEmpty())
            stoogeLine.getData().clear();
        for(Entry entry: data){
            stoogeLine.getData().add(new LineChart.Data<>(entry.getN(), entry.getTime()));
        }
    }

    
    public void clearLines(){
        insertionLine.getData().clear();
        quickLine.getData().clear();
        mergeLine.getData().clear();
        stoogeLine.getData().clear();
    }

    
    public void clear() {
        chart.getData().clear();
    }

    
    public LineChart<Number, Number> getChart(){
        return this.chart;
    }
}
