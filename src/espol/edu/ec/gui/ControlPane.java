/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import espol.edu.ec.tda.MyChart;
import espol.edu.ec.utils.Algorithms;
import espol.edu.ec.utils.ReadWrite;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 *
 * @author Kenny Camba
 */
public class ControlPane {
    
    /**
     * Contenrdor principal
     */
    private final ContentPane root;
    /**
     * Objeto para buscar un archivo
     */
    private final FileChooser chooser;
    /**
     * Stage principal de la applicación
     */
    private final Stage stage;
    
    /**
     * Ruta del archivo
     */
    private final TextField  inputText;
    /**
     * Abrir explorador de archivos
     */
    private final Button open;
    /**
     * Sleccionar todas las lineas del archivo
     */
    private final CheckBox all;
    /**
     * Donde comenzar a ejecutar el algoritmo
     */
    private final TextField start;
    /**
     * Donde terminar de ejecutar el algoritmo
     */
    private final TextField end;
    
    /**
     * Genear aleatorio desde
     */
    private final TextField desde;
    /**
     * Generar aleatorio hasta
     */
    private final TextField hasta;
    
    /**
     * Cantidad de numeros a ser producidos
     */
    private final TextField cantidad;
    
    /**
     * Lista de enteros
     */
    private ArrayList<Integer> list;
    
    private final ProgressIndicator progress;
    MyChart chart;
    
    /**
     * Constructor de la clase
     * @param cp Pane principal
     * @param stage Stage principal
     */
    public ControlPane(ContentPane cp, Stage stage, MyChart chart) {
        root = cp;
        this.chart = chart;
        chooser = new FileChooser();
        this.stage = stage;
        inputText = new TextField();
        open = new Button("...");
        all = new CheckBox("Todo");
        start = new TextField();
        end = new TextField();
        desde = new TextField();
        hasta = new TextField();
        cantidad = new TextField();
        list = new ArrayList<>();
        progress = new ProgressIndicator();
        init();
    }

    /**
     * Configuracion de objetos
     */
    private void init() {
        root.getFlowTop().setPadding(new Insets(10, 10, 10, 10)); 
        chooser.setTitle("Abrir");
        chooser.getExtensionFilters().add(new ExtensionFilter("Archivo de texto", "*.txt"));
        ToggleGroup tg = new ToggleGroup(); 
        root.getFlowTop().getTabs().addAll(selectFile(), selectRandom()); 
        formato();
        evtOpen();
        evtAll();
    }
    
    /**
     * Formato de solo numero para los text fields
     */
    private void formato() {
         UnaryOperator<TextFormatter.Change> filter = change -> {
            if (change.getText().matches("[0-9]*"))
                return change;
            return null;
        };
        TextFormatter<String> textFormatter1 = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter3 = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter4 = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter5 = new TextFormatter<>(filter);
        start.setTextFormatter(textFormatter1);
        end.setTextFormatter(textFormatter2);
        desde.setTextFormatter(textFormatter4);
        hasta.setTextFormatter(textFormatter5);
        cantidad.setTextFormatter(textFormatter3); 
    }
    
    /**
     * Opciones que aparecen al selccionar el RadioButton file
     * @return Tab
     */
    private Tab selectFile() {
        clear();
        action(true);
        Tab tab = new Tab("Archivo");
        tab.setStyle("-fx-background-color: darkgray"); 
        tab.setClosable(false); 
        Separator sp = new Separator();
        sp.setOrientation(Orientation.VERTICAL);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);  
        grid.setPadding(new Insets(10, 10, 10, 10));
        StackPane empezar = crearBoton("");
        evtFileEmpezar(empezar);
        progress.setVisible(false); 
        HBox b = new HBox(new HBox(), progress);
        b.setAlignment(Pos.CENTER_RIGHT); 
        HBox hb = new HBox(empezar, sp, grid, b);
        tab.setContent(hb);
        grid.add(new Label("Archivo"), 0, 0); 
        grid.add(inputText, 1, 0, 14, 1);
        grid.add(open, 15, 0);
        inputText.setMaxWidth(root.getMAX_WIDTH() * 0.25); 
        grid.addRow(1, new Label("Líneas"), all, start, new Label("hasta"), end); 
        start.setMaxWidth(50);
        end.setMaxWidth(50);
        hb.setSpacing(10);
        return tab;
    }
    
    /**
     * Opciones que aparecen al selccionar el RadioButton generate
     * @return Tab
     */
    private Tab selectRandom() {
        clear();
        action(false);
        Tab tab = new Tab("Aleatorio");
        tab.setStyle("-fx-background-color: darkgray"); 
        tab.setClosable(false); 
        Separator sp = new Separator();
        sp.setOrientation(Orientation.VERTICAL);
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);  
        grid.setPadding(new Insets(10, 10, 10, 10));
        StackPane empezar = crearBoton("");
        evtRandEmpezar(empezar);
        HBox hb = new HBox(empezar, sp, grid);
        tab.setContent(hb);
        grid.add(new Label("Cantidad"), 0, 0);
        grid.add(cantidad, 1, 0, 3, 1);
        grid.addRow(1, new Label("Rango"), desde, new Label("hasta"), hasta);
        hb.setSpacing(10);
        desde.setMaxWidth(50);
        hasta.setMaxWidth(50);
        return tab;
    }
    
    /**
     * Limpear text fields
     */
    private void clear() {
        inputText.setText("");
        start.setText("");
        end.setText(""); 
    }
    
    /**
     * Evento de CheckBox all
     */
    private void evtAll() {
        all.setOnAction(ev -> {
            if(all.isSelected())
                action(true);
            else
                action(false);
        }); 
    }
    
    /**
     * Evento boton open
     */
    private void evtOpen() {
        open.setOnAction(e-> {
            progress.setVisible(true); 
            File f = chooser.showOpenDialog(stage);
            if (f != null){
                inputText.setText(f.getAbsolutePath());
                action(true);
                try {
                    list.clear(); 
                    list = ReadWrite.readFile(f.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(ControlPane.class.getName()).log(Level.SEVERE, null, ex);
                } 
                start.setText("1");
                end.setText(String.valueOf(list.size())); 
            }
            progress.setVisible(false);
            
        });
    }
    
    /**
     * Activar o desactivar controles
     * @param b activar: true, desactivar: false
     */
    private void action(boolean b) {
        all.setSelected(b);
        start.setDisable(b);
        end.setDisable(b); 
    }

    /**
     * Evento del boton empezar
     */
    private void evtFileEmpezar(StackPane empezar) {
        empezar.setOnMouseClicked(e -> {
        Alert alert = new Alert(AlertType.ERROR);
            if(inputText.getText().isEmpty()) {
                alert.setContentText("Debe selecionar un archivo"); 
                alert.setTitle("Error!");
                alert.show();
            }else if(!all.isSelected()) {
                String a = start.getText();
                String b = end.getText();
                if(a.isEmpty() || b.isEmpty()) {
                    alert.setContentText("Ingrese un rango válido"); 
                    alert.setTitle("Error!");
                    alert.show();
                }else { //falta condicionar que por lo menos un algoritmo este seleccionado
                    int inicio = Integer.parseInt(a);
                    int fin = Integer.parseInt(b);
                    if(inicio <= fin) {
                        System.out.println("Entro");
                        chart.plotInsertionSortTimes(new Algorithms().getInsertionSortTimes(list)); 
                    }else if(fin > inicio){
                        //graficar
                    }
                }
            }else{
                String a = start.getText();
                String b = end.getText();
                int inicio = Integer.parseInt(a);
                int fin = Integer.parseInt(b);
                chart.plotInsertionSortTimes(new Algorithms().getInsertionSortTimes(list));
            }
            
        }); 
    }

    private void evtRandEmpezar(StackPane empezar) {
        empezar.setOnMouseClicked(e-> {
            Alert alert = new Alert(AlertType.ERROR);
            String num = cantidad.getText();
            if(num.isEmpty()) {
                alert.setContentText("Debe ingresar la cantidad de números a generar"); 
                alert.setTitle("Error!");
                alert.show();
            }else{
                String a = start.getText();
                String b = end.getText();
                if(a.isEmpty() || b.isEmpty()) {
                    alert.setAlertType(AlertType.INFORMATION);
                    alert.setContentText("Se usará el rango por defecto: 0 - 500"); 
                    alert.setTitle("Información");
                    alert.show(); 
                }else{
                    int inicio = Integer.parseInt(a);
                    int fin = Integer.parseInt(b);
                    if(inicio <= fin) {
                        //comenzar a graficar
                    }else if(fin > inicio){
                        //graficar
                    }
                }
            }
        }); 
    }
    private StackPane crearBoton(String tipo) {
        StackPane sp = new StackPane();
        Rectangle r = new Rectangle(root.getMAX_WIDTH()*0.05, root.getMAX_HEIGHT()*0.1);
        r.setArcHeight(5);
        r.setArcWidth(5);
        DropShadow ds = new DropShadow();
        ds.setOffsetX(2);
        ds.setOffsetY(2);
        ds.setRadius(5);
        ds.setColor(Color.DARKGRAY);
        r.setEffect(ds); 
        r.setFill(Color.CORNFLOWERBLUE);
        ImageView iv = new ImageView(new Image(getClass().getResourceAsStream("/espol/edu/ec/resources/play.png"), 
                                root.getMAX_HEIGHT()*0.07, root.getMAX_HEIGHT()*0.07, true, true));
        sp.getChildren().addAll(r, iv);
        sp.setOnMouseEntered(e-> {
            r.setFill(Color.ROYALBLUE);
        }); 
        
        sp.setOnMouseExited(e->{
            r.setFill(Color.CORNFLOWERBLUE);
        });
        
        sp.setOnMousePressed(e->{
            r.setFill(Color.ROYALBLUE);
        }); 
        
        sp.setOnMouseReleased(e-> {
            r.setFill(Color.CORNFLOWERBLUE);
        }); 
        
        return sp;
    }
}
