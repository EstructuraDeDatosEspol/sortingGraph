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
    private StringProperty n;
    private StringProperty insert;
    private StringProperty quick;
    private StringProperty merge;
    private StringProperty stooge;
    
    public DataTable(Integer n, Double insert, Double quick, Double merge, Double stooge) {
        this.n = new SimpleStringProperty(n+"");
        this.insert = new SimpleStringProperty(insert+"");
        this.quick = new SimpleStringProperty(quick+"");
        this.merge = new SimpleStringProperty(merge+"");
        this.stooge = new SimpleStringProperty(stooge+"");
    }

    public StringProperty getN() {
        return n;
    }

    public void setN(StringProperty n) {
        this.n = n;
    }

    public StringProperty getInsert() {
        return insert;
    }

    public void setInsert(StringProperty insert) {
        this.insert = insert;
    }

    public StringProperty getQuick() {
        return quick;
    }

    public void setQuick(StringProperty quick) {
        this.quick = quick;
    }

    public StringProperty getMerge() {
        return merge;
    }

    public void setMerge(StringProperty merge) {
        this.merge = merge;
    }

    public StringProperty getStooge() {
        return stooge;
    }

    public void setStooge(StringProperty stooge) {
        this.stooge = stooge;
    }
    
    public static ObservableList<DataTable> getData(List<List<Entry>> datos){
        ObservableList<DataTable> lista = FXCollections.observableArrayList();
        for(int i=0; i<datos.get(0).size(); i++){
            Double ins = null, q = null, m = null, s = null;
            if(!datos.get(0).isEmpty())
                ins = datos.get(0).get(i).getTime();
            if(!datos.get(1).isEmpty())
                q = datos.get(1).get(i).getTime();
            if(!datos.get(2).isEmpty())
                m = datos.get(2).get(i).getTime();
            if(!datos.get(3).isEmpty())
                s = datos.get(3).get(i).getTime();
            lista.add(new DataTable(datos.get(0).get(i).getN(), ins, q, m, s));
        }
        return lista;
    }
}
