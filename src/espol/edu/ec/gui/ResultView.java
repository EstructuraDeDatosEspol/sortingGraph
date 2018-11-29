/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import espol.edu.ec.tda.DataTable;
import espol.edu.ec.tda.Entry;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Kenny Camba
 */
public class ResultView {
    
    /**
     * Local Stage
     */
    private final Stage stage;
    /**
     * Owner Stage
     */
    private final Stage owner;
    /**
     * Contenedor principal
     */
    private final VBox root;
    /**
     * Tabla de resultados
     */
    private JFXTreeTableView<DataTable> results;
    /**
     * Boton de cerrar ventana
     */
    private final StackPane close;
    /**
     * Indicador de numero de elementos
     */
    private final Text title;
    /**
     * Condicion de haber mostrado la pantlla
     */
    private boolean isShow;
    
    
    Double x, y;
    
    /**
     * Constructor de la clase
     * @param owner Owner Stage
     */
    public ResultView(Stage owner){
        stage = new Stage();
        root = new VBox();
        close = closeButton();
        this.owner = owner;
        title = new Text("Número de Datos: ");
        isShow = false;
        init();
    }

    /**
     * Inicializacion de la pantalla
     */
    private void init() {
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initOwner(owner);
        stage.initModality(Modality.WINDOW_MODAL); 
        root.getStyleClass().add("result");
        Scene scene = new Scene(root);
        scene.getStylesheets().add("espol/edu/ec/styles/theme.css");
        stage.setScene(scene);
        close.setOnMouseClicked(e-> stage.close()); 
        root.setPadding(new Insets(5, 5, 5, 5)); 
        loadPane();
    }
    
    /**
     * Cargar elementos
     */
    private void loadPane() {
        HBox closePane = new HBox(close);
        closePane.setAlignment(Pos.BASELINE_RIGHT);
        HBox titlePane = new HBox(title);
        titlePane.setAlignment(Pos.BASELINE_LEFT);
        titlePane.setPadding(new Insets(5, 5, 5, 5)); 
        root.getChildren().addAll(closePane, titlePane);
       
        
        root.setOnMousePressed((event) -> {
            x = stage.getX() - event.getScreenX();
            y = stage.getY() - event.getScreenY();
        });
        
        root.setOnMouseDragged((event) -> {
            stage.setX(event.getScreenX() + x);
            stage.setY(event.getScreenY() + y);
        });
        
    }
    
    /**
     * Creador de boton de cerrar ventana
     * @return 
     */
    private StackPane closeButton(){
        StackPane btn = new StackPane();
        btn.setAlignment(Pos.CENTER); 
        Circle radius = new Circle(10);
        radius.setFill(Color.WHITE);
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(1);
        shadow.setOffsetY(1);
        shadow.setRadius(2); 
        radius.setEffect(shadow); 
        Text x = new Text("X");
        x.setFont(Font.font(x.getFont().getFamily(), FontWeight.SEMI_BOLD, 10));
        x.setFill(Color.DARKGRAY);
        btn.getChildren().addAll(radius, x);
        
        btn.setOnMouseEntered(e-> {
            radius.setFill(Color.RED);
            x.setFill(Color.WHITE); 
        });
        
        btn.setOnMouseExited(e-> {
            radius.setFill(Color.WHITE);
            x.setFill(Color.DARKGRAY); 
        });
        
        btn.setOnMousePressed(e-> radius.setFill(Color.TOMATO));
        btn.setOnMouseReleased(e -> radius.setFill(Color.RED)); 
        return btn;
    }
    
    /**
     * Creador de tabla de resultados
     * @param datos valores para ubicar
     * @param size total de datos
     */
    public void showTable(List<List<Entry>> datos, int size) {
        title.setText(title.getText() + size);
        String algoritmos[] = {"insert(ms)", "quick(ms)", "merge(ms)", "stooge(ms)"};
        ObservableList<DataTable> lis = DataTable.getData(datos);
        TreeItem<DataTable> raiz = new RecursiveTreeItem<>(lis, RecursiveTreeObject::getChildren);
        results = new JFXTreeTableView<>(raiz);
        results.setShowRoot(false);
        
        JFXTreeTableColumn<DataTable, String> n = new JFXTreeTableColumn<>("n");
        n.setPrefWidth(70);
        n.setCellValueFactory((TreeTableColumn.CellDataFeatures<DataTable, String> param) ->{
            if(n.validateValue(param)) return param.getValue().getValue().getN();
            else return n.getComputedValue(param);
        });

        results.getColumns().add(n);
        raiz.setExpanded(true);
        
        if(!datos.get(0).isEmpty()){
                JFXTreeTableColumn<DataTable, String> column = new JFXTreeTableColumn<>(algoritmos[0]);
                column.setPrefWidth(80);
                column.setCellValueFactory((TreeTableColumn.CellDataFeatures<DataTable, String> param) ->{
                    if(column.validateValue(param)) return param.getValue().getValue().getInsert();
                    else return column.getComputedValue(param);
                });    
                results.getColumns().add(column);
        }
        if(!datos.get(1).isEmpty()){
                JFXTreeTableColumn<DataTable, String> column = new JFXTreeTableColumn<>(algoritmos[1]);
                column.setPrefWidth(80);
                column.setCellValueFactory((TreeTableColumn.CellDataFeatures<DataTable, String> param) ->{
                    if(column.validateValue(param)) return param.getValue().getValue().getQuick();
                    else return column.getComputedValue(param);
                });    
                results.getColumns().add(column);
        }
        if(!datos.get(2).isEmpty()){
                JFXTreeTableColumn<DataTable, String> column = new JFXTreeTableColumn<>(algoritmos[2]);
                column.setPrefWidth(80);
                column.setCellValueFactory((TreeTableColumn.CellDataFeatures<DataTable, String> param) ->{
                    if(column.validateValue(param)) return param.getValue().getValue().getMerge();
                    else return column.getComputedValue(param);
                });    
                results.getColumns().add(column);
        }
        if(!datos.get(3).isEmpty()){
                JFXTreeTableColumn<DataTable, String> column = new JFXTreeTableColumn<>(algoritmos[3]);
                column.setPrefWidth(80);
                column.setCellValueFactory((TreeTableColumn.CellDataFeatures<DataTable, String> param) ->{
                    if(column.validateValue(param)) return param.getValue().getValue().getStooge();
                    else return column.getComputedValue(param);
                });    
                results.getColumns().add(column);
        }
        root.getChildren().add(results);
        isShow = true;
        //stage.show(); 
        //showIn();
    }
    
    /**
     * Mostrar pantalla
     */
    public void show(){
        stage.show();
        showIn();
    }
    
    /**
     * Condicion de poder mostrar
     * @return boolean
     */
    public boolean isShow() {
        return isShow;
    }
    
    /**
     * Posicion relativa de la pantalla
     */
    private void showIn() {
        double x = owner.getX() + (owner.getWidth() - stage.getWidth()) - 10;
        double y = owner.getY() + (owner.getHeight() - stage.getHeight()) - 10;
        stage.setX(x);
        stage.setY(y); 
    }
}
