/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.tda;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Kenny Camba
 */
public class DataTable extends RecursiveTreeObject<DataTable>{
    /**
     * n elemento
     */
    private StringProperty n;
    /**
     * insert proerty
     */
    private StringProperty insert;
    /**
     * quick property
     */
    private StringProperty quick;
    /**
     * merge property
     */
    private StringProperty merge;
    /**
     * stooge property
     */
    private StringProperty stooge;
    
    /**
     * Constructor de clase para generacion de tabla
     * @param n elemento
     * @param insert duracion por cada n
     * @param quick duracion por cada n
     * @param merge duracion por cada n
     * @param stooge duracion por cada n
     */
    public DataTable(Integer n, Double insert, Double quick, Double merge, Double stooge) {
        this.n = new SimpleStringProperty(n+"");
        this.insert = new SimpleStringProperty(insert+"");
        this.quick = new SimpleStringProperty(quick+"");
        this.merge = new SimpleStringProperty(merge+"");
        this.stooge = new SimpleStringProperty(stooge+"");
    }

    /**
     * N elemento
     * @return StringProperty
     */
    public StringProperty getN() {
        return n;
    }

    /**
     * N elemento
     * @param n String property
     */
    public void setN(StringProperty n) {
        this.n = n;
    }

    /**
     * Insert time
     * @return StringProperty
     */
    public StringProperty getInsert() {
        return insert;
    }

    /**
     * Isert time
     * @param insert StringProperty
     */
    public void setInsert(StringProperty insert) {
        this.insert = insert;
    }

    /**
     * Quick time
     * @return StringProperty
     */
    public StringProperty getQuick() {
        return quick;
    }

    /**
     * Quick time
     * @param quick StringProperty
     */
    public void setQuick(StringProperty quick) {
        this.quick = quick;
    }

    /**
     * Merge time
     * @return StringProperty
     */
    public StringProperty getMerge() {
        return merge;
    }

    /**
     * Merge time
     * @param merge StringProperty
     */
    public void setMerge(StringProperty merge) {
        this.merge = merge;
    }

    /**
     * Stooge time
     * @return StringProperty
     */
    public StringProperty getStooge() {
        return stooge;
    }

    /**
     * Isert time
     * @param stooge StringProperty
     */
    public void setStooge(StringProperty stooge) {
        this.stooge = stooge;
    }
    
    /**
     * Lista ordenada de columnas
     * @param datos List de List de Entry
     * @return ObservableList
     */
    public static ObservableList<DataTable> getData(List<List<Entry>> datos){
        ObservableList<DataTable> lista = FXCollections.observableArrayList();
        int size = 0;
        if(!datos.get(0).isEmpty()){
            size = datos.get(0).size();
        }
        if(!datos.get(1).isEmpty()){
            if(size == 0 || datos.get(1).size() < size)
                size = datos.get(1).size();
        }
        if(!datos.get(2).isEmpty()){
            if(size == 0 || datos.get(2).size() < size)
                size = datos.get(2).size();
        }
        
        if(!datos.get(3).isEmpty()){
            if(size == 0 || datos.get(3).size() < size)
                size = datos.get(3).size();
        }
        for(int i=0; i<size; i ++){
            Double ins = null, q = null, m = null, s = null;
            Integer n = null;
            if(!datos.get(0).isEmpty()){
                ins = datos.get(0).get(i).getTime();
                n = datos.get(0).get(i).getN();
            }
            if(!datos.get(1).isEmpty()){
                q = datos.get(1).get(i).getTime();
                n = datos.get(1).get(i).getN();
            }
            if(!datos.get(2).isEmpty()){
                m = datos.get(2).get(i).getTime();
                n = datos.get(2).get(i).getN();
            }
            if(!datos.get(3).isEmpty()){
                s = datos.get(3).get(i).getTime();
                n = datos.get(3).get(i).getN();
            }
            lista.add(new DataTable(n, ins, q, m, s));
        }
        return lista;
    }
}
