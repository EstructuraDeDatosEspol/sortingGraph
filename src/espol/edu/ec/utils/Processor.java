package espol.edu.ec.utils;

import espol.edu.ec.tda.Entry;
import java.util.ArrayList;

import java.util.List;

/**
 * Clase que será usada por la GUI para obtener los resultados de ejecucion de los algoritmos.
 */
public class Processor {
    
    
    List<List<Entry>> datos;

    public Processor() {
        datos = new ArrayList<>();
        datos.add(new ArrayList<>());
        datos.add(new ArrayList<>());
        datos.add(new ArrayList<>());
        datos.add(new ArrayList<>());
    }
    
    

    final Algorithms<Integer> algorithms = new Algorithms<>(Integer::compareTo);

    public List<Entry> getInsertionSortTimes(List<Integer> elements){
        datos.set(0, algorithms.getInsertionSortTimes(elements));
        return datos.get(0);
    }

    public List<Entry> getQuickSortTimes(List<Integer> elements) {
        datos.set(1, algorithms.getQuickSortTimes(elements)); 
        return datos.get(1);
    }

    public List<Entry> getMergeSortTimes(List<Integer> elements){
        datos.set(2, algorithms.getMergeSortTimes(elements));
        return datos.get(2);
    }

    public List<Entry> getStoogeSortTimes(List<Integer> elements){
        datos.set(3, algorithms.getStoogeSortTimes(elements)); 
        return datos.get(3);
    }

    public List<List<Entry>> getDatos() {
        return datos;
    }
    
    public void limpiaDatos(){
        for(int i = 0; i<datos.size(); i++){
            datos.set(i, new ArrayList());
        }
    }
}