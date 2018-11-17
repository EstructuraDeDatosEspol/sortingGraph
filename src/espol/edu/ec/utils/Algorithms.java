/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package espol.edu.ec.utils;

import espol.edu.ec.tda.Entry;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SSAM
 */
public class Algorithms<E> {


    /**
     *
     * @param elements lista completa de elementos.
     * @return  Lista con entries de clave/valor; clave: numero de elementos "n", valor: tiempo de ejecucion respectivo
     */
    public List<Entry> getInsertionSortTimes(List<E> elements){
        List<Entry> results = new ArrayList<>();
        List<E> tempList;

        double start;
        double end;
        for (int n = 0; n < elements.size(); n++){
            tempList = elements.subList(0, n);

            start = System.currentTimeMillis();
            insertionSort(tempList);
            end = System.currentTimeMillis();

            results.add(new Entry(n, end-start));
        }
        return results;
    }


    /**
     *
     * @param elements
     * @return
     */
    public List<Entry> getQuickSortTimes(List<E> elements){
        List<Entry> results = new ArrayList<>();
        List<E> tempList;

        double start;
        double end;

        for (int n = 0; n < elements.size(); n++){
            tempList = elements.subList(0, n);

            start = System.currentTimeMillis();
            quickSort(tempList);
            end = System.currentTimeMillis();

            results.add(new Entry(n, end-start));

        }
        return results;
    }


    /**
     *
     * @param elements
     * @return
     */
    public List<Entry> getMergeSortTimes(List<E> elements){
        List<Entry> results = new ArrayList<>();
        List<E> tempList;

        for (int n = 0; n < elements.size(); n++){
            tempList = elements.subList(0, n);


        }
        return results;
    }


    /**
     *
     * @param elements
     * @return
     */
    public List<Entry> getStoogeSortTimes(List<E> elements){
        List<Entry> results = new ArrayList<>();
        List<E> tempList;

        for (int n = 0; n < elements.size(); n++){
            tempList = elements.subList(0, n);


        }
        return results;
    }



    // <--------------- borrar las instrucciones dentro del método e implementar el algoritmo correspondiente ------------------>


    /**
     *
     * @param elements
     * @return
     */
    private void insertionSort(List<E> elements){

        int n = elements.size();
        try {
            Thread.sleep(10*n*n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * @param elements
     * @return
     */
    private void quickSort(List<E> elements){
        int n = elements.size();
        try {
            Thread.sleep((long) (50*n*Math.log(n)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     *
     * @param elements
     * @return
     */
    private void mergeSort(List<E> elements){
    }


    /**
     *
     * @param elements
     * @return
     */
    private void stoogeSort(List<E> elements){
    }

    
}
