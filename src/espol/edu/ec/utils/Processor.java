package espol.edu.ec.utils;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import espol.edu.ec.tda.Entry;
import java.util.ArrayList;

import java.util.List;

/**
 * Clase que será usada por la GUI para obtener los resultados de ejecucion de los algoritmos.
 */
public class Processor {
    
    
    List<List<Entry>> datos;
    List<Entry> insertion;
    List<Entry> quick;
    List<Entry> merge;
    List<Entry> stooge;

    public Processor() {
        datos = new ArrayList<>();
        datos.add(insertion = new ArrayList<>());
        datos.add(quick = new ArrayList<>());
        datos.add(merge = new ArrayList<>());
        datos.add(stooge = new ArrayList<>());
    }
    
    

    final Algorithms<Integer> algorithms = new Algorithms<>(Integer::compareTo);

    public List<Entry> getInsertionSortTimes(List<Integer> elements){
        insertion = algorithms.getInsertionSortTimes(elements);
        return insertion;
    }

    public List<Entry> getQuickSortTimes(List<Integer> elements) {
        quick =algorithms.getQuickSortTimes(elements); 
        return quick;
    }

    public List<Entry> getMergeSortTimes(List<Integer> elements){
        merge = algorithms.getMergeSortTimes(elements);
        return merge;
    }

    public List<Entry> getStoogeSortTimes(List<Integer> elements){
        stooge = algorithms.getStoogeSortTimes(elements); 
        return stooge;
    }

    
    
    public List<List<Entry>> getDatos() {
        return datos;
    }
    
    

}
