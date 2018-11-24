package espol.edu.ec.gui;

import com.jfoenix.controls.*;
import espol.edu.ec.tda.MyChart;
import espol.edu.ec.utils.FileWorker;
import espol.edu.ec.utils.Processor;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.List;


public class GUIHelper {
    StackPane root;
    Stage owner;
    Processor processor;

    HBox all;

    //<---------------- Seción de Opciones --------------->
    VBox optionsSection;


    //<---Selección de método --->
    JFXTabPane tabPane;

    Tab fromFile;
    VBox loadFileSection;
    HBox loadFileSectionContent;

    JFXCheckBox readAll;
    HBox fromUntilSection;
    Label from;
    JFXTextField fromTexfield;
    Label until;
    JFXTextField untilTextfield;
    FileChooser fileChooser;
    JFXTextField textPath;
    JFXButton openFileButton;
    Label noFileErrorLabel;

    Tab fromRandom;
    VBox generateRandSection;
    HBox generateRandSectionContent;
    Label generateRandLabel;
    Label cantLabel;
    JFXTextField cantTextField;
    Label noCantErrorLabel;

    //<--- Selección de algoritmos --->
    Label toggleGrouptitle;
    GridPane toggleGroup;
    JFXToggleButton insertionSortSelector;
    JFXToggleButton quickSortSelector;
    JFXToggleButton mergeSortSelector;
    JFXToggleButton stoogeSortSelector;
    Label insertion;
    Label quick;
    Label merge;
    Label stooge;

    //<---- Run ----->
    JFXButton run;

    //<------------------ Sección Chart -------------->
    VBox chartShower;
    MyChart chart;
    JFXSpinner spinner;


    public GUIHelper() {
        this.root = new StackPane();
        processor = new Processor();
        loadComponents();
    }

    private void loadComponents(){

        loadTapPane();
        loadToggleGroupOpions();
        setStylesClass();

        run = new JFXButton("Comparar");
        fileChooser = new FileChooser();

        toggleGroup.add(run,1,5);
        optionsSection = new VBox(30);
        optionsSection.getChildren().addAll(tabPane,toggleGroup);
        optionsSection.setPadding(new Insets(10));


        chartShower = new VBox();
        chartShower.setAlignment(Pos.TOP_RIGHT);
        chartShower.setPadding(new Insets(10));
        chart = new MyChart();
        spinner  = new JFXSpinner();
        spinner.setVisible(false);
        spinner.setDisable(true);
        chartShower.getChildren().addAll(chart.getChart(), spinner);

        HBox.setHgrow(chartShower, Priority.ALWAYS);
        all = new HBox();
        all.getChildren().addAll(chartShower, optionsSection);

        loadHandlers();

        this.root.getChildren().add(all);
    }

    private void loadTapPane(){
        tabPane = new JFXTabPane();

        fromFile = new Tab("Cargar Archivo");
        textPath = new JFXTextField();
        textPath.setEditable(false);
        openFileButton = new JFXButton("Cargar Archivo");
        loadFileSection = new VBox(5);
        loadFileSectionContent = new HBox(5);
        loadFileSectionContent.setAlignment(Pos.CENTER);

        readAll = new JFXCheckBox("Leer todo el archivo");
        readAll.setSelected(true);
        fromUntilSection = new HBox(5);
        fromUntilSection.setDisable(true);
        from = new Label("Desde:");
        until = new Label("Hasta:");
        fromTexfield = new JFXTextField();
        untilTextfield = new JFXTextField();
        fromUntilSection.getChildren().addAll(from, fromTexfield, until, untilTextfield);

        noFileErrorLabel = new Label();
        noFileErrorLabel.setTextFill(Color.RED);
        loadFileSectionContent.getChildren().addAll(textPath, openFileButton);
        loadFileSection.getChildren().addAll(loadFileSectionContent, noFileErrorLabel,readAll,fromUntilSection);
        loadFileSection.setPadding( new Insets(10));
        fromFile.setContent(loadFileSection);

        fromRandom = new Tab("Generar números");
        generateRandSection  = new VBox(10);
        generateRandSectionContent = new HBox(5);
        generateRandSectionContent.setAlignment(Pos.CENTER);
        generateRandLabel    = new Label("Generar números aleatorios");
        cantLabel            = new Label("Cantidad de elementos a generar:");
        cantTextField = new JFXTextField();
        generateRandSectionContent.getChildren().addAll(cantLabel,cantTextField);
        noCantErrorLabel = new Label();
        noCantErrorLabel.setTextFill(Color.RED);
        generateRandSection.getChildren().addAll(generateRandLabel,generateRandSectionContent, noCantErrorLabel);
        generateRandSection.setPadding(new Insets(10));
        fromRandom.setContent(generateRandSection);

        tabPane.getTabs().addAll(fromFile, fromRandom);

    }

    private void loadToggleGroupOpions(){

        insertionSortSelector = new JFXToggleButton();
        quickSortSelector     = new JFXToggleButton();
        mergeSortSelector     = new JFXToggleButton();
        stoogeSortSelector    = new JFXToggleButton();

        insertionSortSelector.setSelected(true);
        quickSortSelector.setSelected(true);
        mergeSortSelector.setSelected(true);
        stoogeSortSelector.setSelected(true);

        toggleGrouptitle = new Label("Seleccionar los algoritmos a comparar");
        insertion = new Label("Insertion Sort");
        quick     = new Label("Quick Sort");
        merge     = new Label("Merge Sort");
        stooge    = new Label("Stooge Sort");

        toggleGroup = new GridPane();
        toggleGroup.add(toggleGrouptitle,1,0);
        toggleGroup.add(insertionSortSelector,0,1);
        toggleGroup.add(quickSortSelector, 0,2);
        toggleGroup.add(mergeSortSelector,0,3);
        toggleGroup.add(stoogeSortSelector,0,4);
        toggleGroup.add(insertion,1,1);
        toggleGroup.add(quick, 1,2);
        toggleGroup.add(merge,1,3);
        toggleGroup.add(stooge,1,4);
    }

    private void setStylesClass(){

        root.getStyleClass().add("root");
        loadFileSection.getStyleClass().add("tab-file");
        generateRandSection.getStyleClass().add("tab-rand");

        insertionSortSelector.getStyleClass().add("jfx-toggle-button1");
        quickSortSelector.getStyleClass().add("jfx-toggle-button2");
        mergeSortSelector.getStyleClass().add("jfx-toggle-button3");
        stoogeSortSelector.getStyleClass().add("jfx-toggle-button4");
    }

    private void loadHandlers(){
        run.setOnAction(event -> {

            Platform.runLater(()-> startSpinner());
            chart.clearLines();

            if(fromFile.isSelected()){
                if(!checkFileOptions() || !checkToggleOptions() ){
                    return;
                }
                List<Integer> data;
                Platform.runLater(()-> startSpinner());
                if(readAll.isSelected())
                    data = FileWorker.loadNumbersFromFile(textPath.getText());
                else{
                    data = FileWorker.loadNumbersFromFile(textPath.getText(), Integer.parseInt(fromTexfield.getText()),Integer.parseInt(untilTextfield.getText()));
                }
                Platform.runLater(()-> stopSpinner());
                Platform.runLater(()-> plotLines(data));


            }

            if(fromRandom.isSelected()){
                if(!checkRandomOptions() || !checkToggleOptions()){
                    return;
                }

                Platform.runLater(()-> startSpinner());
                List<Integer> data = FileWorker.loadRandomNumbers(Integer.parseInt(cantTextField.getText()));
                Platform.runLater(()-> stopSpinner());
                Platform.runLater(()-> plotLines(data));

            }
        });

        openFileButton.setOnAction(event -> {
            File f = fileChooser.showOpenDialog(owner);
            if(f == null)
                return;

            textPath.setText(f.getAbsolutePath());
        });

        readAll.setOnAction(event -> {

            if(readAll.isSelected()){
                fromUntilSection.setDisable(true);
            }
            else {
                fromUntilSection.setDisable(false);
            }
        });
    }

    private void plotLines(List<Integer> data){

        if (insertionSortSelector.isSelected())
            chart.plotInsertionSortTimes(processor.getInsertionSortTimes(data));
        if (quickSortSelector.isSelected())
            chart.plotQuickSortTimes(processor.getQuickSortTimes(data));
        if (mergeSortSelector.isSelected())
            chart.plotMergeSortTimes(processor.getMergeSortTimes(data));
        if (stoogeSortSelector.isSelected())
            chart.plotStoogeSortTimes(processor.getStoogeSortTimes(data));
    }

    private boolean checkFileOptions(){
        if(textPath.getText().isEmpty()){
            noFileErrorLabel.setText("** debe selecionar un archivo.");
            return false;
        }
        noFileErrorLabel.setText("");
        return true;
    }

    private boolean checkRandomOptions(){
        if(cantTextField.getText().isEmpty()){
            noCantErrorLabel.setText("** debe ingresar la cantidad de numeros a generar.");
            return false;
        }
        if(!FileWorker.checkValidNumber(cantTextField.getText())){
            noCantErrorLabel.setText("** debe ingresar un número entero.");
            return false;
        }

        noCantErrorLabel.setText("");
        return true;
    }

    private boolean checkToggleOptions(){
        return insertionSortSelector.isSelected() ||
                quickSortSelector.isSelected() ||
                mergeSortSelector.isSelected() ||
                stoogeSortSelector.isSelected();
    }
    private void startSpinner(){
        spinner.setDisable(false);
        spinner.setVisible(true);

    }
    private void stopSpinner(){
        spinner.setDisable(true);
        spinner.setVisible(false);
    }

    public void setOwnerStage(Stage s){
        this.owner = s;
    }
}
