/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.gui;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.TabPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;

/**
 *
 * @author Kenny Camba
 */
public class ContentPane extends BorderPane{
    
    /**
     * Ancho máximo de la pantalla
     */
    private final double MAX_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    
    /**
     * Alto máximo de la pantalla
     */
    private final double MAX_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    
    /**
     * Panel alto de la pantalla
     */
    private final TabPane top;
    
    /**
     * Panel derecho de la pantalla
     */
    private final VBox right;
    
    /**
     * Panel izquierdo de la pantalla
     */
    private final VBox left;
    
    /**
     * Panel bajo de la pantalla
     */
    private final FlowPane bottom;
    
    /**
     * Constructor de la clase
     */
    public ContentPane() {
        top = new TabPane();
        right = new VBox();
        left = new VBox();
        bottom = new FlowPane();
        init();
    }
    
    /**
     * Configuración de propiedades
     */
    private void init() {
        super.setTop(top);
        super.setLeft(left);
        super.setRight(right);
        super.setBottom(bottom); 
        super.setMaxWidth(MAX_WIDTH);
        super.setMaxHeight(MAX_HEIGHT);
        super.setMinWidth(MAX_WIDTH * 0.5); 
        super.setMinHeight(MAX_HEIGHT * 0.5);
        bottom.setOrientation(Orientation.HORIZONTAL); 
        right.setAlignment(Pos.BOTTOM_CENTER); 
        flowTop();
    }
    
    /**
     * Panel alto
     * @return FlowPane
     */
    public TabPane getFlowTop() {
        return top;
    }
    
    /**
     * Panel izquierdo
     * @return VBox
     */
    public VBox getVLeft() {
        return left;
    }
    
    /**
     * Panel bajo
     * @return FlowPane
     */
    public FlowPane getFlowBottom() {
        return bottom;
    }
    
    /**
     * Panel derecho
     * @return VBox
     */
    public VBox getVRight() {
        return right;
    }

    /**
     * Ancho maximo de la pantalla
     * @return double
     */
    public double getMAX_WIDTH() {
        return MAX_WIDTH;
    }

    /**
     * Alto maximo de la pantalla
     * @return double
     */
    public double getMAX_HEIGHT() {
        return MAX_HEIGHT;
    }

    private void flowTop() {
        top.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(8), Insets.EMPTY))); 
        DropShadow shadow = new DropShadow();
        shadow.setOffsetX(10);
        shadow.setOffsetY(20); 
        shadow.setRadius(10);
        shadow.setWidth(30); 
        top.setEffect(shadow); 
    }
}
